package ar.edu.itba.ppc.server.service;

import ar.edu.itba.tp1g5.AddDoctorResponse;
import ar.edu.itba.tp1g5.StartEmergencyCareRequest;
import ar.edu.itba.tp1g5.StartEmergencyCareResponse;
import ar.edu.itba.ppc.server.model.EmeregencyCare;
import ar.edu.itba.ppc.server.repository.EmergencyAdminRepository;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import io.grpc.stub.StreamObserver;

public class EmergencyCareService extends EmergencyCareServiceGrpc.EmergencyCareServiceImplBase{
    private final EmergencyAdminRepository repository;

    public EmergencyCareService(EmergencyAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startEmergencyCare(StartEmergencyCareRequest request, StreamObserver<StartEmergencyCareResponse> responseObserver) {
        EmeregencyCare room = repository.assignRoom(request.getRoom());
        StartEmergencyCareResponse reply = StartEmergencyCareResponse.newBuilder()
                .setRoom(room.getRoom())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }
}
