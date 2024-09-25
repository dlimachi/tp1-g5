package ar.edu.itba.ppc.client;

import ar.edu.itba.ppc.client.utilsConsole.ClientArgs;
import ar.edu.itba.ppc.client.utilsConsole.ClientUtils;
import ar.edu.itba.ppc.client.utilsConsole.CreateQuerys;
import ar.edu.itba.tp1g5.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static ar.edu.itba.ppc.client.utilsConsole.ClientUtils.parseArgs;

public class QueryClient {
    private static Logger logger = LoggerFactory.getLogger(EmergencyAdminClient.class);
    private static CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {
        logger.info("tp1-g5 Client Starting ...");
        logger.info("grpc-com-patterns Client Starting ...");
        Map<String,String> argMap = parseArgs(args);
        final String serverAddress = argMap.get(ClientArgs.SERVER_ADDRESS.getValue());
        final String action = argMap.get(ClientArgs.ACTION.getValue());
        final String outPath = argMap.get(ClientArgs.OUT_PATH.getValue());

        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext()
                .build();

        QueryServiceGrpc.QueryServiceBlockingStub blockingStub = QueryServiceGrpc.newBlockingStub(channel);

        try {
        switch (action) {
            case "queryRooms" -> {
                latch = new CountDownLatch(1);
                QueryRequest request = QueryRequest.newBuilder().setPath(outPath).build();
                QueryRoomResponse response = blockingStub.queryRooms(request);

                CreateQuerys.queryRoomStatusFile(response.getRoomsList(), outPath);
                ClientUtils.getCSVData(outPath);
            }
            case "queryWaitingRoom" -> {
                latch = new CountDownLatch(1);
                QueryRequest request = QueryRequest.newBuilder().setPath(outPath).build();
                QueryWaitingRoomResponse response = blockingStub.queryWaitingRoom(request);
            }
            case "queryCares" -> {
                latch = new CountDownLatch(1);
                QueryRequest request = QueryRequest.newBuilder().setPath(outPath).build();
                QueryCareCompletedResponse response = blockingStub.queryCares(request);
            }
        }
            logger.info("Waiting for response...");
            latch.await();
        }
        catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        finally {
            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
        }
    }
}
