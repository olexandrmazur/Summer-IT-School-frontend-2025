import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    public static void logInfo(String message) {
        logger.info(message);
    }
    public static void logErr(String message) {
        logger.error(message);
    }
}