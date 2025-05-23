package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.ppc.client.utilsConsole.ClientActionHandler;
import ar.edu.itba.tp1g5.DoctorRequest;
import ar.edu.itba.tp1g5.DoctorResponse;
import ar.edu.itba.tp1g5.RoomResponse;
import ar.edu.itba.tp1g5.emergencyAdminServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientParserHelper.parseArgs;

public class EmergencyAdminClient {
    private static Logger logger = LoggerFactory.getLogger(EmergencyAdminClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Emergency Administration Client Starting ...");
        logger.info("grpc-com-patterns Emergency Administration Client Starting ...");
        Map<String,String> argMap = parseArgs(args);
        final String serverAddress = argMap.get(ClientArgs.SERVER_ADDRESS.getValue());
        final String action = argMap.get(ClientArgs.ACTION.getValue());
        final String doctorName = argMap.get(ClientArgs.DOCTOR.getValue());
        final String level = argMap.get(ClientArgs.LEVEL.getValue());
        final String availability = argMap.get(ClientArgs.AVAILABILITY.getValue());

        if (serverAddress == null || action == null) {
            logger.error("Missing required arguments. Usage: -DserverAddress=<address> -Daction=<action> -Ddoctor=<name> [-Dlevel=<level>]");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();
        try {
            emergencyAdminServiceGrpc.emergencyAdminServiceBlockingStub blockingStub =
                emergencyAdminServiceGrpc.newBlockingStub(channel);

            switch (action) {
                case "addRoom":
                    RoomResponse response = ClientActionHandler.executeHandling(() -> blockingStub.addRoom(Empty.newBuilder().build()));
                    if(Objects.nonNull(response))
                        logger.info("Room {} added successfully", response.getRoom());
                    break;
                case "addDoctor":
                    if (level == null || doctorName == null) {
                        logger.error("Doctor name and level are required for addDoctor action");
                        return;
                    }
                    DoctorRequest addRequest = DoctorRequest.newBuilder()
                            .setDoctorName(doctorName)
                            .setLevel(Integer.parseInt(level))
                            .build();
                    DoctorResponse addResponse = ClientActionHandler.executeHandling(() -> blockingStub.addDoctor(addRequest));
                    if(Objects.nonNull(addResponse))
                        logger.info("Doctor {} ({}) added successfully", addResponse.getDoctorName(), addResponse.getLevel());
                    break;
                case "setDoctor":
                    if (doctorName == null || availability == null) {
                        logger.error("Doctor name and level are required for setDoctor action");
                        return;
                    }
                    DoctorRequest setRequest = DoctorRequest.newBuilder()
                            .setDoctorName(doctorName)
                            .setAvailability(availability)
                            .build();
                    DoctorResponse setResponse = ClientActionHandler.executeHandling(() -> blockingStub.setDoctor(setRequest));
                    if(Objects.nonNull(setResponse))
                        logger.info("Doctor {} ({}) is {}", setResponse.getDoctorName(), setResponse.getLevel(), setResponse.getAvailability());
                    break;
                case "checkDoctor":
                    if (doctorName == null) {
                        logger.error("Doctor name is required for checkDoctor action");
                        return;
                    }
                    DoctorRequest checkRequest = DoctorRequest.newBuilder()
                            .setDoctorName(doctorName)
                            .build();
                    DoctorResponse checkResponse = ClientActionHandler.executeHandling(() -> blockingStub.checkDoctor(checkRequest));
                    if(Objects.nonNull(checkResponse))
                        logger.info("Doctor {} ({}) is {}", checkResponse.getDoctorName(), checkResponse.getLevel(), checkResponse.getAvailability());
                    break;
                default:
                    logger.error("Unknown action: " + action);
            }
    } catch (StatusRuntimeException e) {
        logger.error("gRPC failed: {}", e.getStatus());
    } catch (Exception e) {
        logger.error("Unexpected error: ", e);
    } finally {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    }
}

