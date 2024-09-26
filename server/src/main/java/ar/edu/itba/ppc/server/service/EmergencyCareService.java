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
        EmergencyCareResponse reply = synchronizedStartEmergencyCare(request, responseObserver);
        if(Objects.nonNull(reply)) {
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void startAllEmergencyCare(Empty request, StreamObserver<EmergencyCareListResponse> responseObserver) {
        List<EmergencyCareResponse> replies = synchronizedStartAllEmergencyCare(responseObserver);
        responseObserver.onNext(EmergencyCareListResponse.newBuilder().addAllEmergencyCareList(replies).build());
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = syncronizedEndEmergencyCare(request, responseObserver);
        if (Objects.nonNull(reply)) {
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            completedCares.add(reply);
        }
    }

    private synchronized List<EmergencyCareResponse> synchronizedStartAllEmergencyCare(StreamObserver<EmergencyCareListResponse> responseObserver) {
        List<Room> freeRooms = roomRepository.getFreeRooms();

        Patient patient = patientRepository.getUrgentPatient();

        Doctor doctor = doctorRepository.getAvailableDoctor(patient.getEmergencyLevel());

        return freeRooms.stream()
                .map(room -> updateMedicalAttention(patient, room, doctor))
                .collect(Collectors.toList());
    }

    private synchronized EmergencyCareResponse synchronizedStartEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        Integer roomId = request.getRoom();
        Room room = roomRepository.getRoom(roomId);

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

        return updateMedicalAttention(patient, room, availableDoctor);
    }

    private synchronized EmergencyCareResponse syncronizedEndEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        String doctorName = request.getDoctorName();
        Integer room = request.getRoom();

        if (!doctorRepository.getDoctors().containsKey(doctorName)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Doctor " + request.getDoctorName() + " doesn't exist")
                    .asRuntimeException());
            return null;
        }

        Doctor attendingDoctor = doctorRepository.getDoctor(doctorName);

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(room)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Doctor " + request.getDoctorName() + " not assigned to room #" + request.getRoom())
                    .asRuntimeException());
            return null;
        }

        Room attendingRoom = roomRepository.getRoom(room);
        Patient attendingPatient = patientRepository.getPatient(request.getPatientName());

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

    private EmergencyCareResponse updateMedicalAttention(Patient patient, Room room, Doctor doctor) {
        room.setStatus(EmergencyRoomStatus.OCCUPIED.getValue());
        doctor.setRoom(room.getRoom());
        doctor.setAvailability(AvailabilityDoctor.ATTENDING.getValue());
        patient.setStatus(StatusPatient.ATTENDING.getValue());
        patient.setCurrentRoom(room.getRoom());

        return EmergencyCareResponse.newBuilder()
                .setRoom(doctor.getRoom())
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
