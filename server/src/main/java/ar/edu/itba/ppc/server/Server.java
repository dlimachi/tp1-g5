package ar.edu.itba.ppc.server;

import ar.edu.itba.ppc.server.exceptions.GlobalExceptionHandlerInterceptor;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.ppc.server.service.EmergencyAdminService;
import ar.edu.itba.ppc.server.service.EmergencyCareService;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        logger.info(" Server Starting ...");

        int port = 50051;
        DoctorRepository repository = new DoctorRepository();
        RoomRepository roomRepository = new RoomRepository();
        io.grpc.Server server = ServerBuilder.forPort(port)
                .addService(new EmergencyAdminService(repository, roomRepository))
                .addService(new EmergencyCareService(repository, roomRepository))
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
