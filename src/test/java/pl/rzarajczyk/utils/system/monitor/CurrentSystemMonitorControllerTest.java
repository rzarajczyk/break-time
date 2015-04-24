package pl.rzarajczyk.utils.system.monitor;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rafalz
 */
public class CurrentSystemMonitorControllerTest {
    
    private MonitorController controller;
    private static String osName;
    
    @BeforeClass
    public static void beforeClass() {
        osName = System.getProperty("os.name");
    }
    
    @Before
    public void setUp() {
        controller = MonitorControllerFactory.getMonitorController();
    }
    
    @After
    public void tearDown() {
        setOs(osName);
    }
    
    private void setOs(String osString) {
        System.setProperty("os.name", osString);
    }
    
    @Test
    public void shouldChooseApropriateMonitorController() {
        setOs("Linux");
        controller = MonitorControllerFactory.getMonitorController();
        Assert.assertTrue(controller.getClass().getCanonicalName(), controller instanceof UnixMonitorController);
        setOs("fnjgui");
        controller = MonitorControllerFactory.getMonitorController();
        Assert.assertTrue(controller.getClass().getCanonicalName(), controller instanceof EmptyMonitorController);
    }
    
    @Test
    public void shouldTurnOffAndOnMonitor() throws IOException, InterruptedException {
        controller.turnOff();
        final AtomicInteger choice = new AtomicInteger(1);
        Thread t = new Thread() {
            @Override
            public void run() {
                int result = JOptionPane.showConfirmDialog(null, "Can you see the screen", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                choice.set(result);
            }
        };
        t.start();
        t.join(2000);
        t.interrupt();
        Thread.sleep(200);
        controller.turnOn();
        
        Assert.assertTrue("Result was: " + choice.get(), choice.get() != JOptionPane.NO_OPTION);
    }
    
}
