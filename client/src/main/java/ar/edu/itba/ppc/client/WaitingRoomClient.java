package ar.edu.itba.ppc.client;

import ar.edu.itba.tp1g5.PatientRequest;
import ar.edu.itba.tp1g5.PatientResponse;
import ar.edu.itba.tp1g5.WaitingRoomServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WaitingRoomClient {
    private static final Logger logger = LoggerFactory.getLogger(WaitingRoomClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("WaitingRoom Client Starting ...");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        try {
            WaitingRoomServiceGrpc.WaitingRoomServiceBlockingStub blockingStub =
                    WaitingRoomServiceGrpc.newBlockingStub(channel);

            // Test registerPatient
            PatientRequest registerRequest = PatientRequest.newBuilder()
                    .setPatientName("Foo")
                    .setLevel(3)
                    .build();
            PatientResponse registerResponse = blockingStub.registerPatient(registerRequest);
            System.out.printf("Patient %s (%d) is in the waiting room%n",
                    registerResponse.getPatientName(), registerResponse.getLevel());

            // Test updateEmergencyLevel
            PatientRequest updateRequest = PatientRequest.newBuilder()
                    .setPatientName("Foo")
                    .setLevel(4)
                    .build();
            PatientResponse updateResponse = blockingStub.updateEmergencyLevel(updateRequest);
            System.out.printf("Patient %s (%d) is in the waiting room%n",
                    updateResponse.getPatientName(), updateResponse.getLevel());

            // Test checkWaitingList
            PatientRequest checkRequest = PatientRequest.newBuilder()
                    .setPatientName("Foo")
                    .build();
            PatientResponse checkResponse = blockingStub.checkWaitingList(checkRequest);
            System.out.printf("Patient %s (%d) is in the waiting room with %d patients ahead%n",
                    checkResponse.getPatientName(), checkResponse.getLevel(), checkResponse.getWaitingPatient());

        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        } finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}