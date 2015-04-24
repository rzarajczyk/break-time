package pl.rzarajczyk.utils.swing;

import com.google.common.base.Throwables;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;
import org.apache.commons.logging.Log;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author Rafal
 */
public class AggresiveFocusProtector {

    private AggresiveFocusProtectorThread thread = null;

    public AggresiveFocusProtector(JFrame frame) {
        try {
            thread = new AggresiveFocusProtectorThread(frame);
            thread.start();
        } catch (AWTException e) {
            throw Throwables.propagate(e);
        }
    }
    
    public boolean isProtecting() {
        return thread.isWorking();
    }
    
    public JFrame getWindow() {
        return thread.getFrame();
    }
    
    public void setProtecting(boolean enabled) {
        if ( enabled && !thread.isWorking() ) {
            thread.startWorking();
        } else if ( !enabled && thread.isWorking() ) {
            thread.stopWorking();
        }
    }
    
    class AggresiveFocusProtectorThread extends Thread {

        private JFrame frame;
        private Robot robot;
        private volatile boolean working;
        private Semaphore semaphore;
        private Log log = LazyLogFactory.getLog(getClass());
        
        public AggresiveFocusProtectorThread(JFrame frame) throws AWTException {
            super("Aggresive Focus Protector Thread");
            setDaemon(true);
            this.frame = frame;
            this.robot = new Robot();
            this.working = false;
            this.semaphore = new Semaphore(0);
        }

        public JFrame getFrame() {
            return frame;
        }
        
        public boolean isWorking() {
            return working;
        }
        
        public void startWorking() {
            this.working = true;
            this.semaphore.release();
        }
        
        public void stopWorking() {
            this.working = false;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    semaphore.acquire();
                    while (working) {
                        robot.keyRelease(KeyEvent.VK_ALT);
                        robot.keyRelease(KeyEvent.VK_TAB);
                        frame.requestFocus();
                        Thread.sleep(10);
                    }
                }
            } catch (InterruptedException e) {
                log.warn("Thread " + getName() + "has been interrupted", e);
            }
        }
    }
}