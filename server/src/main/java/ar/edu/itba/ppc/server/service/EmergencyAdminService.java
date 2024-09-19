package ar.edu.itba.ppc.server.service;

import ar.edu.itba.tp1g5.DoctorRequest;
import ar.edu.itba.tp1g5.DoctorResponse;
import ar.edu.itba.tp1g5.RoomResponse;
import ar.edu.itba.tp1g5.emergencyAdminServiceGrpc;
import ar.edu.itba.ppc.server.dto.AddDoctorResponse;
import ar.edu.itba.ppc.server.dto.AddRoomResponse;
import ar.edu.itba.ppc.server.dto.InfoDoctorResponse;
import ar.edu.itba.ppc.server.repository.EmergencyAdminRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class EmergencyAdminService extends emergencyAdminServiceGrpc.emergencyAdminServiceImplBase {
    private final EmergencyAdminRepository repository;

    public EmergencyAdminService(EmergencyAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addRoom(Empty request, StreamObserver<RoomResponse> responseObserver) {
        AddRoomResponse response = repository.addRoom();
        RoomResponse reply = RoomResponse.newBuilder()
                .setRoom(response.room())
                .setStatus(response.status())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void addDoctor(DoctorRequest request, StreamObserver<DoctorResponse> responseObserver) {
        AddDoctorResponse response = repository.addDoctor(request.getDoctorName(), request.getLevel());
        DoctorResponse reply = DoctorResponse.newBuilder()
                .setDoctorName(response.doctorName())
                .setLevel(response.level())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void setDoctor(DoctorRequest request, StreamObserver<DoctorResponse> responseObserver) {
        InfoDoctorResponse response = repository.setDoctor(request.getDoctorName(), request.getAvailability());
        DoctorResponse reply = DoctorResponse.newBuilder()
                .setDoctorName(response.doctorName())
                .setLevel(response.level())
                .setAvailability(response.availability())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void checkDoctor(DoctorRequest request, StreamObserver<DoctorResponse> responseObserver) {
        InfoDoctorResponse response = repository.checkDoctor(request.getDoctorName());
        DoctorResponse reply = DoctorResponse.newBuilder()
                .setDoctorName(response.doctorName())
                .setLevel(response.level())
                .setAvailability(response.availability())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
