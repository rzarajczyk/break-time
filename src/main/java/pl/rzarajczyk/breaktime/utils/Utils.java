package pl.rzarajczyk.breaktime.utils;

import com.google.common.base.Throwables;
import java.awt.Component;
import java.awt.Point;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import pl.rzarajczyk.breaktime.ScreenCorner;

/**
 *
 * @author rzarajczyk
 */
public class Utils {

    public static int SECOND = 1000;

    public static String toTimeString(int ms) {
        boolean negative = ms < 0;
        if ( negative ) {
            ms = -ms;
        }
        int sec = ms / Utils.SECOND;
        int min = sec / 60;
        sec = sec % 60;
        StringBuilder result = new StringBuilder();
        if ( negative ) {
            result.append("-");
        }
        result.append(min < 10 ? "0" + min : min);
        result.append(":");
        result.append(sec < 10 ? "0" + sec : sec);
        return result.toString();
    }

    public static int parseTimeString(String str) {
        int multiplier = 1;
        if ( str.trim().startsWith("-") ) {
            multiplier = -1;
        }
        String [] parts = str.split(":");
        if ( parts.length != 2 ) {
            throw new NumberFormatException("Unable to parse time: " + str);
        }
        int minutes = Math.abs( Integer.parseInt(parts[0]) );
        int seconds = Integer.parseInt(parts[1]);
        if ( seconds < 0 )
            throw new NumberFormatException("Unable to parse time: " + str);
        return ( minutes * 60 + seconds ) * Utils.SECOND * multiplier;
    }



    public static void error(Component parent, Log log, String message, Throwable e) {
        log.error(message, e);
        String rootMessage = Throwables.getRootCause(e).getMessage();
        JOptionPane.showMessageDialog(parent, message + ":\n" + rootMessage, "Error occured", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    public static ScreenCorner getScreenCorner(Point point) {
        boolean left = point.getX() < SwingUtils.getScreenWidth() / 2;
        boolean top = point.getY() < SwingUtils.getScreenHeight() / 2;
        ScreenCorner corner;
        if ( top && left )
            corner = ScreenCorner.TOP_LEFT;
        else if ( top && !left )
            corner = ScreenCorner.TOP_RIGHT;
        else if ( !top && left )
            corner = ScreenCorner.BOTTOM_LEFT;
        else
            corner = ScreenCorner.BOTTOM_RIGHT;
        return corner;
    }
}