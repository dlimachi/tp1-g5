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
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
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
        EmergencyCareResponse reply = updateStatusForDoctor(request, responseObserver);
        if (reply != null) {
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void startAllEmergencyCare(Empty request, StreamObserver<EmergencyCareResponse> responseObserver) {
        List<Room> freeRooms = roomRepository.getRooms().values().stream()
                .filter(room -> room.getStatus().equals(EmergencyRoomStatus.FREE.getValue()))
                .sorted(Comparator.comparing(Room::getRoom))
                .toList();

        for (Room room : freeRooms) {
            EmergencyCareRequest careRequest = EmergencyCareRequest.newBuilder()
                    .setRoom(room.getRoom())
                    .build();
            EmergencyCareResponse response = updateStatusForDoctor(careRequest, responseObserver);
            if (response != null) {
                responseObserver.onNext(response);
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = updateStatusForEmergencyCare(request, responseObserver);
        if (reply != null) {
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            completedCares.add(reply);
        }
    }

    private synchronized EmergencyCareResponse updateStatusForDoctor(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        Integer roomId = request.getRoom();
        Room room = roomRepository.getRooms().get(roomId);

        if (Objects.isNull(room)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Room " + request.getRoom() + " doesn't exist")
                    .asRuntimeException());
            return null;
        }
        if (room.getStatus().equals(EmergencyRoomStatus.OCCUPIED.getValue())) {
            responseObserver.onError(Status.ALREADY_EXISTS
                    .withDescription("Room " + request.getRoom() + " already occupied")
                    .asRuntimeException());
            return null;
        }

        Patient patient = patientRepository.getPatients().values().stream()
                .filter(p -> p.getStatus().equals(StatusPatient.WAITING.getValue()))
                .sorted(Comparator.comparing(Patient::getEmergencyLevel).reversed()
                        .thenComparing(Patient::getArrivalTime))
                .findFirst()
                .orElse(null);

        if (patient == null) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("No waiting patients found")
                    .asRuntimeException());
            return null;
        }

        Doctor availableDoctor = doctorRepository.getDoctors().values().stream()
                .filter(doctor -> doctor.getAvailability().equals(AvailabilityDoctor.AVAILABLE.getValue()))
                .min(Comparator.comparing(Doctor::getLevel)
                        .thenComparing(Doctor::getDoctorName))
                .orElse(null);

        if (availableDoctor == null) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("No available doctors found")
                    .asRuntimeException());
            return null;
        }

        room.setStatus(EmergencyRoomStatus.OCCUPIED.getValue());
        availableDoctor.setRoom(roomId.toString());
        availableDoctor.setAvailability(AvailabilityDoctor.ATTENDING.getValue());
        patient.setStatus(StatusPatient.ATTENDING.getValue());

        return EmergencyCareResponse.newBuilder()
                .setRoom(room.getRoom())
                .setDoctorName(availableDoctor.getDoctorName())
                .setDoctorLevel(availableDoctor.getLevel())
                .setPatientName(patient.getPatientName())
                .setPatientLevel(patient.getEmergencyLevel())
                .build();
    }

    private synchronized EmergencyCareResponse updateStatusForEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        String doctorName = request.getDoctorName();
        Integer room = request.getRoom();

        if (!doctorRepository.getDoctors().containsKey(doctorName)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Doctor " + request.getDoctorName() + " doesn't exist")
                    .asRuntimeException());
            return null;
        }

        Doctor attendingDoctor = doctorRepository.getDoctors().get(doctorName);
        Room attendingRoom = roomRepository.getRooms().get(room);
        Patient attendingPatient = patientRepository.getPatients().get(request.getPatientName());

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(room.toString())) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Doctor " + request.getDoctorName() + " not assigned to room #" + request.getRoom())
                    .asRuntimeException());
            return null;
        }

        attendingRoom.setStatus(EmergencyRoomStatus.FREE.getValue());
        attendingDoctor.setAvailability(AvailabilityDoctor.AVAILABLE.getValue());
        attendingDoctor.setRoom(null);
        attendingPatient.setStatus(StatusPatient.COMPLETED.getValue());

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