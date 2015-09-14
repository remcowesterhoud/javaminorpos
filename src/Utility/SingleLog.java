package Utility;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Remco on 14-9-2015.
 */
public class SingleLog {

    private static SingleLog log;
    private static boolean logIsAvailable = true;
    private static Logger logger;
    private static FileHandler fh;

    private SingleLog(){
        logger = Logger.getLogger("posLog");
        try{
            fh = new FileHandler("posLog", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info("Log started");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SingleLog getLog() {
        if (logIsAvailable) {
            if (log == null) {
                log = new SingleLog();
            }
            logIsAvailable = false;
            return log;
        } else {
            return waitForLog();
        }
    }

    public static void closeLog(){
        logIsAvailable = true;
    }

    private static SingleLog waitForLog(){
        while (!logIsAvailable){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return getLog();
    }

    public void addInfo(String info){
        logger.info(info);
        closeLog();
    }

    public void addWarning(String warning){
        logger.warning(warning);
        closeLog();
    }
}
