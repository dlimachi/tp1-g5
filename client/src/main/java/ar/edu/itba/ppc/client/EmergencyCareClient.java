package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.ppc.client.utilsConsole.ClientCallback;
import ar.edu.itba.tp1g5.EmergencyCareListResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientUtils.parseArgs;

public class EmergencyCareClient {
    private static Logger logger = LoggerFactory.getLogger(EmergencyAdminClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Emergency Care Client Starting ...");
        logger.info("grpc-com-patterns Emergency Care Client Starting ...");
        Map<String, String> argMap = parseArgs(args);
        final String serverAddress = argMap.get(ClientArgs.SERVER_ADDRESS.getValue());
        final String action = argMap.get(ClientArgs.ACTION.getValue());

        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();

        try {
            EmergencyCareServiceGrpc.EmergencyCareServiceBlockingStub blockingStub =
                    EmergencyCareServiceGrpc.newBlockingStub(channel);

            switch (action) {
                case "carePatient" -> {
                    final String room = argMap.get(ClientArgs.ROOM.getValue());
                    if (room == null) {
                        logger.error("Room is required for carePatient action");
                        return;
                    }
                    EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                            .setRoom(Integer.parseInt(room))
                            .build();
                    EmergencyCareResponse response = ClientCallback.executeHandling(() -> blockingStub.startEmergencyCare(request));
                    if (Objects.nonNull(response)) {
                        logger.info("Patient {} ({}) and Doctor {} ({}) are now in Room #{}",
                                response.getPatientName(), response.getPatientLevel(), response.getDoctorName(), response.getDoctorLevel(), response.getRoom());
                    }
                }
                case "careAllPatients" -> {
                    EmergencyCareListResponse response = ClientCallback.executeHandling(() -> blockingStub.startAllEmergencyCare(Empty.newBuilder().build()));
                    if (Objects.nonNull(response)) {
                        for (EmergencyCareResponse careResponse : response.getEmergencyCareListList()) {
                            logger.info("Patient {} ({}) and Doctor {} ({}) are now in Room #{}",
                                    careResponse.getPatientName(), careResponse.getPatientLevel(), careResponse.getDoctorName(), careResponse.getDoctorLevel(), careResponse.getRoom());
                        }
                    }
                }
                case "dischargePatient" -> {
                    final String room = argMap.get(ClientArgs.ROOM.getValue());
                    final String doctorName = argMap.get(ClientArgs.DOCTOR.getValue());
                    final String patientName = argMap.get(ClientArgs.PATIENT.getValue());

                    if (room == null || doctorName == null || patientName == null) {
                        logger.error("Room, doctorName and patientName are required for dischargePatient action");
                        return;
                    }
                    EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                            .setRoom(Integer.parseInt(room))
                            .setDoctorName(doctorName)
                            .setPatientName(patientName)
                            .build();
                    EmergencyCareResponse response = ClientCallback.executeHandling(() -> blockingStub.endEmergencyCare(request));
                    if(Objects.nonNull(response)) {
                        logger.info("Patient {} ({}) has been discharged from Doctor {} ({}) and the Room #{} is now {}",
                                response.getPatientName(), response.getPatientLevel(), response.getDoctorName(), response.getDoctorLevel(), response.getRoom(), response.getRoomStatus());
                    }
                }
                default -> logger.error("Unknown action: " + action);
            }
        } catch (StatusRuntimeException e) {
            logger.error("gRPC failed: {}", e.getStatus().getDescription());
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

}

