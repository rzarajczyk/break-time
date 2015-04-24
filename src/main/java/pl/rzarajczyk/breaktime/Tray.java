package pl.rzarajczyk.breaktime;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.utils.application.Application;
import pl.rzarajczyk.utils.log.LazyLogFactory;
import pl.rzarajczyk.utils.swing.ExtendedMouseAdapter;

/**
 * Spring bean responsible for tray icon support.
 * <p>
 * Responsibilities:
 * <ul>
 * <li>Creating tray icon (empty image) and placing it next to the clock</li>
 * <li>Registering appropriate listeners and triggering  {@link Menu}</li>
 * <li>Providing interface for changing the tray icon</li>
 * <li>Providing interface for acquiring the dimensions of the tray icon</li>
 * </ul>
 */
public class Tray {
    
    private Log log = LazyLogFactory.getLog(getClass());
    
    @Autowired
    private Menu menu;
    
    @Autowired
    private Application application;
    
    private TrayIcon tray;
 
    @PostConstruct
    public void open() throws InterruptedException, IOException, AWTException {
        int count = BreakTimeSettings.TRAY_INITIALIZATION_COUNT;
        while ( !SystemTray.isSupported() && count > 0 ) {
            log.warn("SystemTray is not available. Retrying in " + BreakTimeSettings.TRAY_INITIALIZATION_INTERVAL + " ms (" + count + ")");
            Thread.sleep(BreakTimeSettings.TRAY_INITIALIZATION_INTERVAL);
            count--;
        }
        
        if ( ! SystemTray.isSupported() ) {
            throw new UnsupportedOperationException("SystemTray is not available in your system");
        }

        String name = application.getConfiguration().getApplicationName();
        tray = new TrayIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB), name, null);
        tray.setToolTip(name);
        tray.addMouseListener(new ExtendedMouseAdapter(BreakTimeSettings.DOUBLE_CLICK_TIME) {

            @Override
            public void mouseSingleClicked(MouseEvent e) {
                menu.invokeSingleClickAction(e);
            }

            @Override
            public void mouseDoubleClicked(MouseEvent e) {
                menu.invokeDoubleClickAction(e);
            }

            @Override
            public void mouseRightClicked(MouseEvent e) {
                menu.setLocation(e.getX(), e.getY());
                menu.setVisible(true);
            }
        });
        
        SystemTray.getSystemTray().add(tray);
    }
    
    void setTrayIconImage(Image image) {
        tray.setImage(image);
    }
    
    Dimension getTrayIconDimension() {
        return tray.getSize();
    }
}
