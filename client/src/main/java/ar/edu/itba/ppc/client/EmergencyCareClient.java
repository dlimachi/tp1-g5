package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientUtils.parseArgs;

public class EmergencyCareClient {
    private static Logger logger = LoggerFactory.getLogger(EmergencyAdminClient.class);
    private static CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Client Starting ...");
        logger.info("grpc-com-patterns Client Starting ...");
        Map<String,String> argMap = parseArgs(args);
        final String serverAddress = argMap.get(ClientArgs.SERVER_ADDRESS.getValue());
        final String action = argMap.get(ClientArgs.ACTION.getValue());

        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();

        EmergencyCareServiceGrpc.EmergencyCareServiceBlockingStub blockingStub =
                EmergencyCareServiceGrpc.newBlockingStub(channel);

        switch (action) {
            case "carePatient" -> {
                final String room = argMap.get(ClientArgs.ROOM.getValue());
                latch = new CountDownLatch(1);
                EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                        .setRoom(Integer.parseInt(room))
                        .build();
                EmergencyCareResponse response = blockingStub.startEmergencyCare(request);
                System.out.println(String.format("Patient %s (%d) and Doctor %s (%d) are now in Room #$d",
                        response.getPatientName(), response.getPatientLevel(), response.getDoctorName(), response.getDoctorLevel(), response.getRoom()));
            }
            case "dischargePatient" -> {
                final String room = argMap.get(ClientArgs.ROOM.getValue());
                final String doctorName = argMap.get(ClientArgs.DOCTOR.getValue());
                final String patientName = argMap.get(ClientArgs.PATIENT.getValue());
                latch = new CountDownLatch(1);
                EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                        .setRoom(Integer.parseInt(room))
                        .setDoctorName(doctorName)
                        .setPatientName(patientName)
                        .build();
                EmergencyCareResponse response = blockingStub.endEmergencyCare(request);
                System.out.println(String.format("Patient %d (%d) has been discharged from Doctor %s (%d) and the Room #$d is now %d",
                        response.getPatientName(), response.getPatientLevel(), response.getDoctorName(), response.getDoctorLevel(), response.getRoom(), response.getRoomStatus()));
            }
        }

        try {
            /*EmergencyCareRequest requested = EmergencyCareRequest.newBuilder()
                    .setRoom(1)
                    .build();

            EmergencyCareResponse reply = blockingStub.startEmergencyCare(requested);
            System.out.println(String.format("Start emergency care in %s with %s", reply.getRoom(), reply.getDoctorName()));

             */
            logger.info("Waiting for response...");
            latch.await();
        }
        catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}

