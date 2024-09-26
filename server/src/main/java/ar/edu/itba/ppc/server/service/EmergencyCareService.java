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
import ar.edu.itba.tp1g5.EmergencyCareListResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

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
        EmergencyCareResponse reply = updateMedicalAttention(request, responseObserver);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void startAllEmergencyCare(Empty request, StreamObserver<EmergencyCareListResponse> responseObserver) {
        List<EmergencyCareResponse> replies = startEmergencyCareInFreeRooms(responseObserver);
        responseObserver.onNext(EmergencyCareListResponse.newBuilder().addAllEmergencyCareList(replies).build());
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

    private synchronized List<EmergencyCareResponse> startEmergencyCareInFreeRooms(StreamObserver<EmergencyCareListResponse> responseObserver) {
        List<Room> freeRooms = roomRepository.getFreeRooms();

        Patient patient = patientRepository.getUrgentPatient();

        Doctor doctor = doctorRepository.getAvailableDoctor(patient.getEmergencyLevel());

        return freeRooms.stream()
                .map(room -> createResponse(patient, room, doctor))
                .collect(Collectors.toList());
    }

    private synchronized EmergencyCareResponse updateMedicalAttention(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
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

        Patient patient = patientRepository.getUrgentPatient();

        if(Objects.isNull(patient)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Room #" + request.getRoom() + " remains " + room.getStatus())
                    .asRuntimeException());
            return null;
        }

        Doctor availableDoctor = doctorRepository.getAvailableDoctor(patient.getEmergencyLevel());

        if(Objects.isNull(availableDoctor)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Room #" + request.getRoom() + " remains " + room.getStatus())
                    .asRuntimeException());
            return null;
        }

        return createResponse(patient, room, availableDoctor);
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

    private EmergencyCareResponse createResponse(Patient patient, Room room, Doctor doctor) {
        room.setStatus(EmergencyRoomStatus.OCCUPIED.getValue());
        doctor.setRoom(room.getRoom());
        doctor.setAvailability(AvailabilityDoctor.ATTENDING.getValue());
        patient.setStatus(StatusPatient.ATTENDING.getValue());

        return EmergencyCareResponse.newBuilder()
                .setRoom(room.getRoom())
                .setDoctorName(doctor.getDoctorName())
                .setDoctorLevel(doctor.getLevel())
                .setPatientName(patient.getPatientName())
                .setPatientLevel(patient.getEmergencyLevel())
                .build();
    }

    public List<EmergencyCareResponse> getCompletedCares() {
        return new ArrayList<>(completedCares);
    }
}
