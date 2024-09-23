package ar.edu.itba.ppc.client;

import ar.edu.itba.tp1g5.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class EmergencyCareClient {
    private static Logger logger = LoggerFactory.getLogger(EmergencyAdminClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Client Starting ...");
        logger.info("grpc-com-patterns Client Starting ...");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        try {
            EmergencyCareServiceGrpc.EmergencyCareServiceBlockingStub blockingStub =
                    EmergencyCareServiceGrpc.newBlockingStub(channel);

            EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                    .setRoom(1)
                    .build();

            EmergencyCareResponse reply = blockingStub.startEmergencyCare(request);
            System.out.println(String.format("Start emergency care in %s with %s", reply.getRoom(), reply.getDoctorName()));




        }
        catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}

