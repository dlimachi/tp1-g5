package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.constants.AvailabilityDoctor;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.tp1g5.DoctorRequest;
import ar.edu.itba.tp1g5.DoctorResponse;
import ar.edu.itba.tp1g5.RoomResponse;
import ar.edu.itba.tp1g5.emergencyAdminServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.EnumSet;
import java.util.Objects;

public class EmergencyAdminService extends emergencyAdminServiceGrpc.emergencyAdminServiceImplBase {
    private final DoctorRepository repository;
    private final RoomRepository roomRepository;

    public EmergencyAdminService(DoctorRepository repository, RoomRepository roomRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void addRoom(Empty request, StreamObserver<RoomResponse> responseObserver) {
        Room response = roomRepository.addRoom();
        RoomResponse reply = RoomResponse.newBuilder()
                .setRoom(response.getRoom())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void addDoctor(DoctorRequest request, StreamObserver<DoctorResponse> responseObserver) {
        if (Objects.nonNull(repository.getDoctor(request.getDoctorName()))) {
            responseObserver.onError(Status.ALREADY_EXISTS
                    .withDescription("Doctor " + request.getDoctorName() + " already exists")
                    .asRuntimeException());
            return;
        }

        if (request.getLevel() < 1 || request.getLevel() > 5) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid emergency level. Must be between 1 and 5.")
                    .asRuntimeException());
            return;
        }

        Doctor response = repository.addDoctor(request.getDoctorName(), request.getLevel());
        DoctorResponse reply = DoctorResponse.newBuilder()
                .setDoctorName(response.getDoctorName())
                .setLevel(response.getLevel())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void setDoctor(DoctorRequest request, StreamObserver<DoctorResponse> responseObserver) {
        if (Objects.isNull(repository.getDoctor(request.getDoctorName()))) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Doctor " + request.getDoctorName() + " doesn't exists")
                    .asRuntimeException());
            return;
        }
        if(!isAvailabilityValid(request.getAvailability())) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("The availability " + request.getAvailability() +" is invalid.")
                    .asRuntimeException());
            return;
        }

        Doctor response = repository.setAvailabilityDoctor(request.getDoctorName(), request.getAvailability());
        DoctorResponse reply = DoctorResponse.newBuilder()
                .setDoctorName(response.getDoctorName())
                .setLevel(response.getLevel())
                .setAvailability(response.getAvailability())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void checkDoctor(DoctorRequest request, StreamObserver<DoctorResponse> responseObserver) {
        if (Objects.isNull(repository.getDoctor(request.getDoctorName()))) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Doctor " + request.getDoctorName() + " doesn't exists")
                    .asRuntimeException());
            return;
        }

        Doctor response = repository.getDoctor(request.getDoctorName());
        DoctorResponse reply = DoctorResponse.newBuilder()
                .setDoctorName(response.getDoctorName())
                .setLevel(response.getLevel())
                .setAvailability(response.getAvailability())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private boolean isAvailabilityValid(String availability) {
        return EnumSet.allOf(AvailabilityDoctor.class)
                .stream()
                .map(Enum::name)
                .anyMatch(name -> name.equalsIgnoreCase(availability));
    }
}
