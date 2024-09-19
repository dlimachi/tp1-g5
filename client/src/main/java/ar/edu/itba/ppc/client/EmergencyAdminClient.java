package ar.edu.itba.ppc.client;

import ar.edu.itba.tp1g5.DoctorRequest;
import ar.edu.itba.tp1g5.DoctorResponse;
import ar.edu.itba.tp1g5.RoomResponse;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import ar.edu.itba.tp1g5.emergencyAdminServiceGrpc;

public class EmergencyAdminClient {
    private static Logger logger = LoggerFactory.getLogger(EmergencyAdminClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Client Starting ...");
        logger.info("grpc-com-patterns Client Starting ...");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        try {
            emergencyAdminServiceGrpc.emergencyAdminServiceBlockingStub blockingStub =
                    emergencyAdminServiceGrpc.newBlockingStub(channel);
            RoomResponse reply = blockingStub.addRoom(Empty.newBuilder().build());

            DoctorRequest request = DoctorRequest.newBuilder()
                    .setDoctorName("Dr. House")
                    .setLevel(1)
                    .build();
            DoctorResponse reply2 = blockingStub.addDoctor(request);

            System.out.println(reply.getRoom());
            System.out.println(reply.getStatus());
            System.out.println(reply2.getDoctorName());
            System.out.println(reply2.getLevel());

        }
        catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}
