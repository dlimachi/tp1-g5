package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.constants.AvailabilityDoctor;
import ar.edu.itba.ppc.server.constants.EmergencyRoomStatus;
import ar.edu.itba.ppc.server.constants.StatusPatient;
import ar.edu.itba.ppc.server.exceptions.*;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.Comparator;
import java.util.List;

public class EmergencyCareService extends EmergencyCareServiceGrpc.EmergencyCareServiceImplBase {
    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;

    public EmergencyCareService(DoctorRepository doctorRepository, RoomRepository roomRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void startEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        List<EmergencyCareResponse> replies = startEmergencyCareInFreeRooms();
        for (EmergencyCareResponse reply : replies) {
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = updateStatusForEmergencyCare(request);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private synchronized List<EmergencyCareResponse> startEmergencyCareInFreeRooms() {
        List<EmergencyCareResponse> responses = new java.util.ArrayList<>();
        List<Room> freeRooms = roomRepository.getRooms().values().stream()
                .filter(room -> room.getStatus().equals(EmergencyRoomStatus.FREE.getValue()))
                .sorted(Comparator.comparing(Room::getRoom))
                .toList();

        for (Room room : freeRooms) {
            try {
                EmergencyCareResponse response = startEmergencyCareInRoom(room);
                if (response != null) {
                    responses.add(response);
                }
            } catch (Exception e) {
                // Log the exception or handle it as needed
                System.err.println("Error starting emergency care in room " + room.getRoom() + ": " + e.getMessage());
            }
        }

        return responses;
    }

    private EmergencyCareResponse startEmergencyCareInRoom(Room room) {
        Patient patient = patientRepository.getWaitingPatientsInOrder().stream()
                .findFirst()
                .orElse(null);

        if (patient == null) {
            return null; // No patients waiting
        }

        Doctor availableDoctor = doctorRepository.getDoctors().values().stream()
                .filter(doctor -> doctor.getAvailability().equals(AvailabilityDoctor.AVAILABLE.getValue()))
                .min(Comparator.comparing(Doctor::getLevel)
                        .thenComparing(Doctor::getDoctorName))
                .orElse(null);

        if (availableDoctor == null) {
            return null; // No available doctors
        }

        room.setStatus(EmergencyRoomStatus.OCCUPIED.getValue());
        availableDoctor.setRoom(String.valueOf(room.getRoom()));
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

    private synchronized EmergencyCareResponse updateStatusForEmergencyCare(EmergencyCareRequest request) {
        String doctorName = request.getDoctorName();
        Integer room = request.getRoom();

        if (!doctorRepository.getDoctors().containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }

        Doctor attendingDoctor = doctorRepository.getDoctors().get(doctorName);
        Room attendingRoom = roomRepository.getRooms().get(room);
        Patient attendingPatient = patientRepository.getPatients().get(request.getPatientName());

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(room.toString())) {
            throw new DoctorNotAssignedToRoomException(doctorName, room);
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
}