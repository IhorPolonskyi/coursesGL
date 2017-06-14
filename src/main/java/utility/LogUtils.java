package utility;

import org.testng.log4testng.Logger;

/**
 * Created by user on 09.06.17.
 */
public class LogUtils {

    public static Logger logger = Logger.getLogger(LogUtils.class);

    public static void testStep(String message){
        logger.info("Test step: " + message);
    }

    public static void info(String message){
        logger.info(message);
    }

}

