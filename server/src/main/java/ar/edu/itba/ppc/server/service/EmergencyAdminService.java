package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.tp1g5.DoctorRequest;
import ar.edu.itba.tp1g5.DoctorResponse;
import ar.edu.itba.tp1g5.RoomResponse;
import ar.edu.itba.tp1g5.emergencyAdminServiceGrpc;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

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
                .setStatus(response.getStatus())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void addDoctor(DoctorRequest request, StreamObserver<DoctorResponse> responseObserver) {
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
        Doctor response = repository.getDoctor(request.getDoctorName());
        DoctorResponse reply = DoctorResponse.newBuilder()
                .setDoctorName(response.getDoctorName())
                .setLevel(response.getLevel())
                .setAvailability(response.getAvailability())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
