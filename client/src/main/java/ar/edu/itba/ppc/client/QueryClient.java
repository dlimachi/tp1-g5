package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.ppc.client.utilsConsole.ClientActionHandler;
import ar.edu.itba.ppc.client.utilsConsole.ClientParserHelper;
import ar.edu.itba.ppc.client.utilsConsole.CreateQuerysHelper;
import ar.edu.itba.tp1g5.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientParserHelper.parseArgs;

public class QueryClient {
    private static Logger logger = LoggerFactory.getLogger(EmergencyAdminClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Query Client Starting ...");
        logger.info("grpc-com-patterns Query Client Starting ...");
        Map<String,String> argMap = parseArgs(args);
        final String serverAddress = argMap.get(ClientArgs.SERVER_ADDRESS.getValue());
        final String action = argMap.get(ClientArgs.ACTION.getValue());
        final String outPath = argMap.get(ClientArgs.OUT_PATH.getValue());
        final String room = argMap.get(ClientArgs.ROOM.getValue());

        if (serverAddress == null || action == null || outPath == null ) {
            logger.error("Missing required arguments. Usage: -DserverAddress=<address> -Daction=<action> -DoutPath=<outPath>");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();

        QueryServiceGrpc.QueryServiceBlockingStub blockingStub = QueryServiceGrpc.newBlockingStub(channel);

        try {
            switch (action) {
                case "queryRooms" -> {
                    QueryRequest request = QueryRequest.newBuilder().setPath(outPath).build();
                    QueryRoomResponse response = ClientActionHandler.executeHandling(() -> blockingStub.queryRooms(request));
                    if (Objects.nonNull(response) && !response.getRoomsList().isEmpty()) {
                        CreateQuerysHelper.queryRoomStatusFile(response.getRoomsList(), outPath);
                        ClientParserHelper.getCSVData(outPath);
                    }
                }
                case "queryWaitingRoom" -> {
                    QueryRequest request = QueryRequest.newBuilder().setPath(outPath).build();
                    QueryWaitingRoomResponse response = ClientActionHandler.executeHandling(() -> blockingStub.queryWaitingRoom(request));

                    if (Objects.nonNull(response) && !response.getWaitingRoomsList().isEmpty()) {
                        CreateQuerysHelper.queryWaitingRoomFile(response.getWaitingRoomsList(), outPath);
                        ClientParserHelper.getCSVData(outPath);
                    }
                }
                case "queryCares" -> {
                    QueryRequest.Builder requestBuilder = QueryRequest.newBuilder().setPath(outPath);
                    if (Objects.nonNull(room)) {
                        requestBuilder.setRoom(Integer.parseInt(room));
                        logger.info("Filtering cares for room: {}", room);
                    } else {
                        logger.info("Querying all completed cares");
                    }
                    QueryRequest request = requestBuilder.build();
                    QueryCareCompletedResponse response = ClientActionHandler.executeHandling(() -> blockingStub.queryCares(request));

                    if (Objects.nonNull(response) && !response.getCareCompletedList().isEmpty()) {
                        CreateQuerysHelper.queryCaresFile(response.getCareCompletedList(), outPath);
                        ClientParserHelper.getCSVData(outPath);
                    }
                }
                default -> logger.error("Unknown action: " + action);
            }
        }
        catch (StatusRuntimeException e) {
            logger.error("gRPC failed: {}", e.getStatus());
        }
        catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}
