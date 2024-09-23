package ar.edu.itba.ppc.server.service;

import ar.edu.itba.tp1g5.QueryRequest;
import ar.edu.itba.tp1g5.QueryResponse;
import ar.edu.itba.tp1g5.QueryServiceGrpc;
import io.grpc.stub.StreamObserver;

public class QueryService extends QueryServiceGrpc.QueryServiceImplBase {
    @Override
    public void queryRooms(QueryRequest request, StreamObserver<QueryResponse> responseObserver) {
    }

    @Override
    public void queryWaitingRoom(QueryRequest request, StreamObserver<QueryResponse> responseObserver) {
    }

    @Override
    public void queryCares(QueryRequest request, StreamObserver<QueryResponse> responseObserver) {
    }
}
