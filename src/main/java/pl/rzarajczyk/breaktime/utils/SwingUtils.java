package pl.rzarajczyk.breaktime.utils;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

/**
 *
 * @author rzarajczyk
 */
public abstract class SwingUtils {
    
    private static GraphicsDevice gd;
    
    private static void initializeGraphicsDevice() {
        if ( gd == null ) {
            gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        }
    }
    
    public static GraphicsDevice getGraphicsDevice() {
        initializeGraphicsDevice();
        return gd;
    }
    
    public static void setGraphicsDevice(GraphicsDevice device) {
        gd = device;
    }
    
    // =========================================================================
   
    public static int getScreenWidth() {
        initializeGraphicsDevice();
        return getGraphicsDevice().getDisplayMode().getWidth();
    }

    public static int getScreenHeight() {
        initializeGraphicsDevice();
        return getGraphicsDevice().getDisplayMode().getHeight();
    }

    public static void center(JFrame window) {
        int x = ( getScreenWidth() / 2 ) - ( window.getWidth() / 2 );
        int y = ( getScreenHeight() / 2 ) - ( window.getHeight() / 2 );
        window.setLocation(x, y);
    }
}
