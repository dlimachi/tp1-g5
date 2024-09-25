package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.tp1g5.DoctorRequest;
import ar.edu.itba.tp1g5.DoctorResponse;
import ar.edu.itba.tp1g5.RoomResponse;
import ar.edu.itba.tp1g5.emergencyAdminServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientUtils.parseArgs;

public class EmergencyAdminClient {
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

        emergencyAdminServiceGrpc.emergencyAdminServiceBlockingStub blockingStub =
                emergencyAdminServiceGrpc.newBlockingStub(channel);

        switch (action) {
            case "addRoom" -> {
                latch = new CountDownLatch(1);
                RoomResponse response = blockingStub.addRoom(Empty.newBuilder().build());
                System.out.println(String.format("Room %d added successfully",
                        response.getRoom()));
            }
            case "addDoctor" -> {
                final String doctorName = argMap.get(ClientArgs.DOCTOR.getValue());
                final String level = argMap.get(ClientArgs.LEVEL.getValue());
                latch = new CountDownLatch(1);
                DoctorRequest request = DoctorRequest.newBuilder()
                        .setDoctorName(doctorName)
                        .setLevel(Integer.parseInt(level))
                        .build();
                DoctorResponse response = blockingStub.addDoctor(request);
                System.out.println(String.format("Doctor %s (%d) added successfully",
                        response.getDoctorName(), response.getLevel()));
            }
            case "setDoctor" -> {
                final String doctorName = argMap.get(ClientArgs.DOCTOR.getValue());
                final String availability = argMap.get(ClientArgs.AVAILABILITY.getValue());
                latch = new CountDownLatch(1);
                DoctorRequest request = DoctorRequest.newBuilder()
                        .setDoctorName(doctorName)
                        .setAvailability(availability)
                        .build();
                DoctorResponse response = blockingStub.setDoctor(request);
                System.out.println(String.format("Doctor %s (%d) is %s",
                        response.getDoctorName(), response.getLevel(), response.getAvailability()));
            }
            case "checkDoctor" -> {
                final String doctorName = argMap.get(ClientArgs.DOCTOR.getValue());
                latch = new CountDownLatch(1);
                DoctorRequest request = DoctorRequest.newBuilder()
                        .setDoctorName(doctorName)
                        .build();
                DoctorResponse response = blockingStub.checkDoctor(request);
                System.out.println(String.format("Doctor %s (%d) is %s",
                        response.getDoctorName(), response.getLevel(), response.getAvailability()));
            }

        }

        try {
            DoctorRequest request = DoctorRequest.newBuilder()
                    .setDoctorName("John")
                    .setLevel(1)
                    .build();
            DoctorResponse reply2 = blockingStub.addDoctor(request);

            System.out.println(reply2.getDoctorName());
            System.out.println(reply2.getLevel());
            logger.info("Waiting for response...");
            latch.await();
        }
        catch (RuntimeException e) {
            logger.error("Error: " + e.getMessage());
        }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}

