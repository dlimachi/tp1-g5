package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.tp1g5.PatientRequest;
import ar.edu.itba.tp1g5.PatientResponse;
import ar.edu.itba.tp1g5.WaitingRoomServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
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

        if (serverAddress == null || action == null) {
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
                    if (patient == null || levelStr == null) {
                        logger.error("Patient name and level are required for addPatient action");
                        return;
                    }
                    addPatient(blockingStub, patient, Integer.parseInt(levelStr));
                    break;
                case "updateLevel":
                    if (patient == null || levelStr == null) {
                        logger.error("Patient name and new level are required for updateLevel action");
                        return;
                    }
                    updateLevel(blockingStub, patient, Integer.parseInt(levelStr));
                    break;
                case "checkPatient":
                    if (patient == null) {
                        logger.error("Patient name is required for checkPatient action");
                        return;
                    }
                    if (levelStr != null) {
                        logger.error("Level should not be provided for checkPatient action");
                        return;
                    }
                    checkPatient(blockingStub, patient);
                    break;
                default:
                    logger.error("Unknown action: " + action);
            }
        } catch (StatusRuntimeException e) {
            logger.error("RPC failed: {}", e.getStatus());
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private static void addPatient(WaitingRoomServiceGrpc.WaitingRoomServiceBlockingStub stub, String patient, int level) {
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient)
                .setLevel(level)
                .build();
        try {
            PatientResponse response = stub.registerPatient(request);
            logger.info("Patient {} added with emergency level {}", response.getPatientName(), response.getLevel());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.ALREADY_EXISTS) {
                logger.error("Patient {} already exists in the waiting room", patient);
            } else {
                logger.error("Failed to add patient: {}", e.getStatus().getDescription());
            }
        }
    }

    private static void updateLevel(WaitingRoomServiceGrpc.WaitingRoomServiceBlockingStub stub, String patient, int newLevel) {
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient)
                .setLevel(newLevel)
                .build();
        try {
            PatientResponse response = stub.updateEmergencyLevel(request);
            logger.info("Updated emergency level for patient {} to {}", response.getPatientName(), response.getLevel());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                logger.error("Patient {} not found in the waiting room", patient);
            } else {
                logger.error("Failed to update emergency level: {}", e.getStatus().getDescription());
            }
        }
    }

    private static void checkPatient(WaitingRoomServiceGrpc.WaitingRoomServiceBlockingStub stub, String patient) {
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient)
                .build();
        try {
            PatientResponse response = stub.checkWaitingList(request);
            System.out.printf("Patient %s (%d) is in the waiting room with %d patients ahead%n",
                    response.getPatientName(), response.getLevel(), response.getWaitingPatient());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                logger.error("Patient {} not found in the waiting room", patient);
            } else {
                logger.error("Failed to check patient: {}", e.getStatus().getDescription());
            }
        }
    }
}