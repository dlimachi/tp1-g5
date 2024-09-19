package ar.edu.itba.ppc.server;

import ar.edu.itba.ppc.server.exceptions.GlobalExceptionHandlerInterceptor;
import ar.edu.itba.ppc.server.repository.EmergencyAdminRepository;
import ar.edu.itba.ppc.server.service.EmergencyAdminService;
import ar.edu.itba.ppc.server.service.EmergencyCareService;
import io.grpc.BindableService;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Function;

public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        logger.info(" Server Starting ...");

        int port = 50051;
        EmergencyAdminRepository repository = new EmergencyAdminRepository();
        io.grpc.Server server = ServerBuilder.forPort(port)
                .addService(new EmergencyAdminService(repository))
                .addService(new EmergencyCareService(repository))
                .intercept(new GlobalExceptionHandlerInterceptor())
                .build();
        server.start();
        logger.info("Server started, listening on " + port);
        server.awaitTermination();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down gRPC server since JVM is shutting down");
            server.shutdown();
            logger.info("Server shut down");
        }));
    }}
