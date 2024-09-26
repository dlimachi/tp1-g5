package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.constants.AvailabilityDoctor;
import ar.edu.itba.ppc.server.constants.EmergencyRoomStatus;
import ar.edu.itba.ppc.server.constants.StatusPatient;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.tp1g5.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import com.google.protobuf.Empty;

import java.util.Comparator;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EmergencyCareService extends EmergencyCareServiceGrpc.EmergencyCareServiceImplBase {
    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;
    private final ConcurrentLinkedQueue<EmergencyCareResponse> completedCares;

    public EmergencyCareService(DoctorRepository doctorRepository, RoomRepository roomRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
        this.completedCares = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void startEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = updateStatusForDoctor(request);
        if (reply != null) {
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void startAllEmergencyCare(Empty request, StreamObserver<EmergencyCareListResponse> responseObserver) {
        List<Room> freeRooms = roomRepository.getRooms().values().stream()
                .filter(room -> room.getStatus().equals(EmergencyRoomStatus.FREE.getValue()))
                .sorted(Comparator.comparing(Room::getRoom))
                .toList();

        List<EmergencyCareResponse> responses = new ArrayList<>();

        for (Room room : freeRooms) {
            EmergencyCareRequest careRequest = EmergencyCareRequest.newBuilder()
                    .setRoom(room.getRoom())
                    .build();
            EmergencyCareResponse response = updateStatusForDoctor(careRequest);
            if (response != null) {
                responses.add(response);
            }
        }

        EmergencyCareListResponse listResponse = EmergencyCareListResponse.newBuilder()
                .addAllEmergencyCareList(responses)
                .build();

        responseObserver.onNext(listResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = updateStatusForEmergencyCare(request);
        if (reply != null) {
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            completedCares.add(reply);
        }
    }

    private synchronized EmergencyCareResponse updateStatusForDoctor(EmergencyCareRequest request) {
        Integer roomId = request.getRoom();
        Room room = roomRepository.getRooms().get(roomId);

        if (Objects.isNull(room)) {
            return null;
        }
        if (room.getStatus().equals(EmergencyRoomStatus.OCCUPIED.getValue())) {
            return null;
        }

        Patient patient = patientRepository.getPatients().values().stream()
                .filter(p -> p.getStatus().equals(StatusPatient.WAITING.getValue()) && p.getCurrentRoom() == null)
                .sorted(Comparator.comparing(Patient::getEmergencyLevel).reversed()
                        .thenComparing(Patient::getArrivalTime))
                .findFirst()
                .orElse(null);

        if (patient == null) {
            return null;
        }

        Doctor availableDoctor = doctorRepository.getDoctors().values().stream()
                .filter(doctor -> doctor.getAvailability().equals(AvailabilityDoctor.AVAILABLE.getValue()) && doctor.getRoom() == null)
                .min(Comparator.comparing(Doctor::getLevel)
                        .thenComparing(Doctor::getDoctorName))
                .orElse(null);

        if (availableDoctor == null) {
            return null;
        }

        room.setStatus(EmergencyRoomStatus.OCCUPIED.getValue());
        availableDoctor.setRoom(roomId.toString());
        availableDoctor.setAvailability(AvailabilityDoctor.ATTENDING.getValue());
        patient.setStatus(StatusPatient.ATTENDING.getValue());
        patient.setCurrentRoom(roomId);

        return EmergencyCareResponse.newBuilder()
                .setRoom(room.getRoom())
                .setDoctorName(availableDoctor.getDoctorName())
                .setDoctorLevel(availableDoctor.getLevel())
                .setPatientName(patient.getPatientName())
                .setPatientLevel(patient.getEmergencyLevel())
                .setRoomStatus(EmergencyRoomStatus.OCCUPIED.getValue())
                .build();
    }

    private synchronized EmergencyCareResponse updateStatusForEmergencyCare(EmergencyCareRequest request) {
        String doctorName = request.getDoctorName();
        Integer room = request.getRoom();

        if (!doctorRepository.getDoctors().containsKey(doctorName)) {
            return null;
        }

        Doctor attendingDoctor = doctorRepository.getDoctors().get(doctorName);
        Room attendingRoom = roomRepository.getRooms().get(room);
        Patient attendingPatient = patientRepository.getPatients().get(request.getPatientName());

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(room.toString())) {
            return null;
        }

        attendingRoom.setStatus(EmergencyRoomStatus.FREE.getValue());
        attendingDoctor.setAvailability(AvailabilityDoctor.AVAILABLE.getValue());
        attendingDoctor.setRoom(null);
        attendingPatient.setStatus(StatusPatient.COMPLETED.getValue());
        attendingPatient.setCurrentRoom(null);

        return EmergencyCareResponse.newBuilder()
                .setDoctorName(attendingDoctor.getDoctorName())
                .setDoctorLevel(attendingDoctor.getLevel())
                .setRoom(attendingRoom.getRoom())
                .setPatientName(attendingPatient.getPatientName())
                .setPatientLevel(attendingPatient.getEmergencyLevel())
                .setRoomStatus(attendingRoom.getStatus())
                .build();
    }

    public List<EmergencyCareResponse> getCompletedCares() {
        return new ArrayList<>(completedCares);
    }
}