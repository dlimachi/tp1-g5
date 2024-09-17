package ar.edu.itba.ppc.client;

import ar.edu.itba.tp1g5.AddDoctorRequest;
import ar.edu.itba.tp1g5.AddDoctorResponse;
import ar.edu.itba.tp1g5.AddRoomResponse;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import ar.edu.itba.tp1g5.EmergencyAdminServiceGrpc;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Client Starting ...");
        logger.info("grpc-com-patterns Client Starting ...");
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        try {
            AddDoctorRequest request = AddDoctorRequest.newBuilder()
                    .setDoctorName("Dr. House")
                    .setLevel(1)
                    .build();

            EmergencyAdminServiceGrpc.emergencyAdminServiceBlockingStub blockingStub =
                    EmergencyAdminServiceGrpc.newBlockingStub(channel);
            AddRoomResponse reply = blockingStub.addRoom(Empty.newBuilder().build());
            AddDoctorResponse reply2 = blockingStub.addDoctor(request);
            System.out.println(reply.getRoom());
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
