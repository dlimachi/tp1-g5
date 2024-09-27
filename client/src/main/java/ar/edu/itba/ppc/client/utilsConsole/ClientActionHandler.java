package ar.edu.itba.ppc.client.utilsConsole;

import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class ClientActionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ClientParserHelper.class);

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
