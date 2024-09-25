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
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.Comparator;
import java.util.Objects;

public class EmergencyCareService extends EmergencyCareServiceGrpc.EmergencyCareServiceImplBase{
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
        EmergencyCareResponse reply = updateStatusForDoctor(request, responseObserver);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = updateStatusForEmergencyCare(request, responseObserver);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private synchronized EmergencyCareResponse updateStatusForDoctor(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        Integer roomId = request.getRoom();
        Room room = roomRepository.getRooms().get(roomId);

        if (Objects.isNull(room)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Room " + request.getRoom() + " doesn't exists")
                    .asRuntimeException());
        }
        if (room.getStatus().equals(EmergencyRoomStatus.OCCUPIED.getValue())) {
            responseObserver.onError(Status.ALREADY_EXISTS
                    .withDescription("Room " + request.getRoom() + " already occupied")
                    .asRuntimeException());
        }

        Patient patient = patientRepository.getPatients().values().stream()
                .sorted(Comparator.comparing(Patient::getEmergencyLevel).reversed()
                        .thenComparing(Patient::getArrivalTime))
                .findFirst().orElse(null);

        Doctor availableDoctor = doctorRepository.getDoctors().values().stream()
                .filter(doctor -> doctor.getAvailability().equals(AvailabilityDoctor.AVAILABLE.getValue()))
                .sorted(Comparator.comparing(Doctor::getLevel)
                        .thenComparing(Doctor::getDoctorName))
                .findFirst().orElse(null);

        if(Objects.isNull(patient) || Objects.isNull(availableDoctor)){
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Room #" + request.getRoom() + " remains " + room.getStatus())
                    .asRuntimeException());
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
                    .withDescription("Doctor " + request.getDoctorName() + " doesn't exists")
                    .asRuntimeException());
        }

        Doctor attendingDoctor = doctorRepository.getDoctors().get(doctorName);
        Room attendingRoom = roomRepository.getRooms().get(room);
        Patient attendingPatient = patientRepository.getPatients().get(request.getPatientName());

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(room.toString())) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Doctor " + request.getDoctorName() + " not assigned to room #" + request.getRoom())
                    .asRuntimeException());
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
