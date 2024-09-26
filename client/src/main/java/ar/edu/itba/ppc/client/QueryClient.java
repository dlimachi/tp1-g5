package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.ppc.client.utilsConsole.ClientCallback;
import ar.edu.itba.ppc.client.utilsConsole.ClientUtils;
import ar.edu.itba.ppc.client.utilsConsole.CreateQuerys;
import ar.edu.itba.tp1g5.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientUtils.parseArgs;

public class QueryClient {
    private static Logger logger = LoggerFactory.getLogger(QueryClient.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Query Client Starting ...");
        logger.info("grpc-com-patterns Query Client Starting ...");
        Map<String,String> argMap = parseArgs(args);
        final String serverAddress = argMap.get(ClientArgs.SERVER_ADDRESS.getValue());
        final String action = argMap.get(ClientArgs.ACTION.getValue());
        final String outPath = argMap.get(ClientArgs.OUT_PATH.getValue());
        final String room = argMap.get(ClientArgs.ROOM.getValue());

        logger.info("Server Address: {}", serverAddress);
        logger.info("Action: {}", action);
        logger.info("Output Path: {}", outPath);
        logger.info("Room: {}", room);

        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();

        QueryServiceGrpc.QueryServiceBlockingStub blockingStub = QueryServiceGrpc.newBlockingStub(channel);

        try {
            switch (action) {
                case "queryRooms" -> {
                    QueryRequest request = QueryRequest.newBuilder().setPath(outPath).build();
                    QueryRoomResponse response = ClientCallback.executeHandling(() -> blockingStub.queryRooms(request));
                    if (Objects.nonNull(response)) {
                        logger.info("Received response for queryRooms");
                        CreateQuerys.queryRoomStatusFile(response.getRoomsList(), outPath);
                        ClientUtils.getCSVData(outPath);
                    }
                }
                case "queryWaitingRoom" -> {
                    QueryRequest request = QueryRequest.newBuilder().setPath(outPath).build();
                    QueryWaitingRoomResponse response = ClientCallback.executeHandling(() -> blockingStub.queryWaitingRoom(request));

                    if (Objects.nonNull(response)) {
                        logger.info("Received response for queryWaitingRoom");
                        CreateQuerys.queryWaitingRoomFile(response.getWaitingRoomsList(), outPath);
                        ClientUtils.getCSVData(outPath);
                    }
                }
                case "queryCares" -> {
                    QueryRequest.Builder requestBuilder = QueryRequest.newBuilder().setPath(outPath);
                    if (room != null && !room.isEmpty()) {
                        requestBuilder.setRoom(Integer.parseInt(room));
                        logger.info("Filtering cares for room: {}", room);
                    } else {
                        logger.info("Querying all completed cares");
                    }
                    QueryRequest request = requestBuilder.build();
                    QueryCareCompletedResponse response = ClientCallback.executeHandling(() -> blockingStub.queryCares(request));

                    if (Objects.nonNull(response)) {
                        logger.info("Received {} completed cares", response.getCareCompletedCount());
                        CreateQuerys.queryCaresFile(response.getCareCompletedList(), outPath);
                        logger.info("CSV file created at: {}", outPath);
                        ClientUtils.getCSVData(outPath);
                    } else {
                        logger.warn("No response received from queryCares");
                    }
                }
                default -> logger.error("Unknown action: " + action);
            }
        }
        catch (StatusRuntimeException e) {
            logger.error("gRPC failed: {}", e.getStatus());
        }
        catch (Exception e) {
            logger.error("Error: " + e.getMessage(), e);
        }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}