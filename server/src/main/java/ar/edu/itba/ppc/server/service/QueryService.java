package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.tp1g5.*;
import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.stream.Collectors;

public class QueryService extends QueryServiceGrpc.QueryServiceImplBase {
    private final PatientRepository patientRepository;

    public QueryService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void queryRooms(QueryRequest request, StreamObserver<QueryRoomResponse> responseObserver) {
        super.queryRooms(request, responseObserver);
    }

    @Override
    public void queryWaitingRoom(QueryRequest request, StreamObserver<QueryWaitingRoomResponse> responseObserver) {
        if(patientRepository.getPatients().isEmpty()) {
            throw new RuntimeException("no hay pacientes etc...");
        }

        List<WaitingRoom> patientsWaiting = patientRepository.getWaitingPatientsInOrder().stream()
                .map(patient -> WaitingRoom.newBuilder()
                        .setPatientName(patient.getPatientName())
                        .setPatientLevel(patient.getEmergencyLevel().toString())
                        .build())
                .collect(Collectors.toList());

        QueryWaitingRoomResponse reply = QueryWaitingRoomResponse.newBuilder()
                .addAllWaitingRooms(patientsWaiting)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void queryCares(QueryRequest request, StreamObserver<QueryCareCompletedResponse> responseObserver) {
        super.queryCares(request, responseObserver);
    }
}
