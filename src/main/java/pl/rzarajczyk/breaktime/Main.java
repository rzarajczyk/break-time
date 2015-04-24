package pl.rzarajczyk.breaktime;

import org.apache.commons.logging.LogFactory;
import pl.rzarajczyk.breaktime.utils.Utils;
import pl.rzarajczyk.utils.application.Application;

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
            Application.getInstance().start();
        } catch (Throwable e) {
            Utils.error(null, LogFactory.getLog(Main.class), "Error during running the applicartion", e);
        }
    }
}
