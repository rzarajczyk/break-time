package pl.rzarajczyk.breaktime;

import java.io.File;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import pl.rzarajczyk.breaktime.utils.Utils;
import pl.rzarajczyk.utils.application.Application;
import pl.rzarajczyk.utils.application.Configuration;

/**
 *
 * @author rzarajczyk
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            File logFile = new File(Configuration.instance().getStorageDir(), BreakTimeSettings.LOG_FILE_NAME);
            System.setProperty("LOG_FILE_PATH", logFile.getAbsolutePath());
            SpringApplication.run(AppConfig.class, args);
        } catch (Throwable e) {
            Utils.error(null, LogFactory.getLog(Main.class), "Error during running the applicartion", e);
        }
    }
}
