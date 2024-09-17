package ar.edu.itba.ppc.server.service;

import ar.edu.itba.tp1g5.AddDoctorRequest;
import ar.edu.itba.tp1g5.AddDoctorResponse;
import ar.edu.itba.tp1g5.AddRoomResponse;
import ar.edu.itba.tp1g5.EmergencyAdminServiceGrpc;
import ar.edu.itba.ppc.server.repository.EmergencyAdminRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;


public class EmergencyAdminService extends EmergencyAdminServiceGrpc.EmergencyAdminServiceImplBase {
    private final EmergencyAdminRepository repository;

    public EmergencyAdminService(EmergencyAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addRoom(Empty request, StreamObserver<AddRoomResponse> responseObserver) {
        Integer room = repository.addRoom();
        AddRoomResponse reply = AddRoomResponse.newBuilder().setRoom(room).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void addDoctor(AddDoctorRequest request, StreamObserver<AddDoctorResponse> responseObserver) {
        Integer level = repository.addDoctor(request.getDoctorName(), request.getLevel());
        AddDoctorResponse reply = AddDoctorResponse.newBuilder()
                .setDoctorName(request.getDoctorName())
                .setLevel(level)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void setDoctor(AddDoctorRequest request, StreamObserver<AddDoctorResponse> responseObserver) {
        String availability = repository.setDoctor(request.getDoctorName(), request.getAvailability());
        AddDoctorResponse reply = AddDoctorResponse.newBuilder()
                .setDoctorName(request.getDoctorName())
                .setAvailability(availability)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void checkDoctor(AddDoctorRequest request, StreamObserver<AddDoctorResponse> responseObserver) {
        String availability = repository.checkDoctor(request.getDoctorName());
        AddDoctorResponse reply = AddDoctorResponse.newBuilder()
                .setDoctorName(request.getDoctorName())
                .setAvailability(availability)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
