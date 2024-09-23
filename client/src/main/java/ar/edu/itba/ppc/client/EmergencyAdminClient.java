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

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        try {
            emergencyAdminServiceGrpc.emergencyAdminServiceBlockingStub blockingStub =
                    emergencyAdminServiceGrpc.newBlockingStub(channel);
            RoomResponse replyRoom = blockingStub.addRoom(Empty.newBuilder().build());

            DoctorRequest request = DoctorRequest.newBuilder()
                    .setDoctorName("Melisa")
                    .setLevel(2)
                    .build();
            DoctorResponse reply = blockingStub.addDoctor(request);

            DoctorRequest request2 = DoctorRequest.newBuilder()
                    .setDoctorName("Fede")
                    .setLevel(3)
                    .build();

            DoctorResponse reply2 = blockingStub.addDoctor(request2);


            System.out.println(String.format("The room %s is %s", replyRoom.getRoom(), replyRoom.getStatus()));

            System.out.println(String.format("Added doctor %s, is %s", reply.getDoctorName(), reply.getAvailability()));

            System.out.println(String.format("Added doctor %s, is %s", reply2.getDoctorName(), reply2.getAvailability()));


        }
        catch (Exception e) {
            System.out.println(e.toString());
            logger.error("Error: " + e.getMessage());
            }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}
