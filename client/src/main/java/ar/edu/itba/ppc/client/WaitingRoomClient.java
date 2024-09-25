package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.tp1g5.PatientRequest;
import ar.edu.itba.tp1g5.PatientResponse;
import ar.edu.itba.tp1g5.WaitingRoomServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientUtils.parseArgs;

public class WaitingRoomClient {
    private static final Logger logger = LoggerFactory.getLogger(WaitingRoomClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("WaitingRoom Client Starting ...");
        logger.info("grpc-com-patterns Client Starting ...");

        Map<String, String> argMap = parseArgs(args);
        final String serverAddress = argMap.get(ClientArgs.SERVER_ADDRESS.getValue());
        final String action = argMap.get(ClientArgs.ACTION.getValue());
        final String patient = argMap.get("patient");
        final String levelStr = argMap.get("level");

        if (serverAddress == null || action == null || patient == null ) {
            logger.error("Missing required arguments. Usage: -DserverAddress=<address> -Daction=<action> -Dpatient=<name> [-Dlevel=<level>]");
            return;
        }

        String[] addressParts = serverAddress.split(":");
        if (addressParts.length != 2) {
            logger.error("Invalid server address format. Use: host:port");
            return;
        }
        String host = addressParts[0];
        int port = Integer.parseInt(addressParts[1]);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            WaitingRoomServiceGrpc.WaitingRoomServiceBlockingStub blockingStub =
                    WaitingRoomServiceGrpc.newBlockingStub(channel);

            switch (action) {
                case "addPatient":
                    if (levelStr == null) {
                        logger.error("Patient name and level are required for addPatient action");
                        return;
                    }
                    PatientRequest addRequest = PatientRequest.newBuilder()
                            .setPatientName(patient)
                            .setLevel(Integer.parseInt(levelStr))
                            .build();
                    PatientResponse addResponse = executeHandling(() -> blockingStub.registerPatient(addRequest));
                    if(Objects.nonNull(addResponse))
                        logger.info("Patient {} added with emergency level {}", addResponse.getPatientName(), addResponse.getLevel());
                    break;
                case "updateLevel":
                    if (levelStr == null) {
                        logger.error("Patient name and level are required for addPatient action");
                        return;
                    }
                    PatientRequest updateRequest = PatientRequest.newBuilder()
                            .setPatientName(patient)
                            .setLevel(Integer.parseInt(levelStr))
                            .build();
                    PatientResponse updateResponse = executeHandling(() -> blockingStub.updateEmergencyLevel(updateRequest));
                    if(Objects.nonNull(updateResponse))
                        logger.info("Updated emergency level for patient {} to {}", updateResponse.getPatientName(), updateResponse.getLevel());
                    break;
                case "checkPatient":
                    PatientRequest checkRequest = PatientRequest.newBuilder()
                            .setPatientName(patient)
                            .build();

                    PatientResponse checkResponse = blockingStub.checkWaitingList(checkRequest);
                    if(Objects.nonNull(checkResponse))
                        logger.info("Patient {} ({}) is in the waiting room with {} patients ahead",
                            checkResponse.getPatientName(), checkResponse.getLevel(), checkResponse.getWaitingPatient());
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

    public static <T> T executeHandling(Callable<T> callable) {
        try {
            return callable.call();
        } catch (StatusRuntimeException e) {
            logger.error(e.getStatus().getDescription());
            return null;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
