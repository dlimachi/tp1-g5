package ar.edu.itba.ppc.server.service;

import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.ppc.server.dto.EmeregencyCare;
import ar.edu.itba.ppc.server.repository.EmergencyAdminRepository;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import io.grpc.stub.StreamObserver;

public class EmergencyCareService extends EmergencyCareServiceGrpc.EmergencyCareServiceImplBase{
    private final EmergencyAdminRepository repository;

    public EmergencyCareService(EmergencyAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmeregencyCare emeregencyCare = repository.assignRoomToEmergencyCare(request.getRoom());
        EmergencyCareResponse reply = EmergencyCareResponse.newBuilder()
                .setDoctorName(emeregencyCare.getDoctorName())
                .setRoom(emeregencyCare.getRoom())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmeregencyCare emeregencyCare = repository.endEmergencyCare(request.getRoom(), request.getDoctorName(), request.getPatientName());
        EmergencyCareResponse reply = EmergencyCareResponse.newBuilder()
                .setDoctorName(emeregencyCare.getDoctorName())
                .setRoom(emeregencyCare.getRoom())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
