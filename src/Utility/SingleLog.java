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
        if (log == null) {
            synchronized (SingleLog.class) {
                if (log == null) {
                    log = new SingleLog();
                }
            }
        }
        return log;
    }

    public void addInfo(String info){
        logger.info(info);
    }

    public void addWarning(String warning){
        logger.warning(warning);
    }
}
