package pl.rzarajczyk.utils.system.monitor;

import java.io.IOException;
import pl.rzarajczyk.utils.exec.Exec;

/**
 *
 * @author rafalz
 */
class UnixMonitorController implements MonitorController {

    @Override
    public void turnOff() throws IOException {
        Exec.runAndWait("xset dpms force off");
    }

    @Override
    public void turnOn() throws IOException {
        Exec.runAndWait("xset dpms force on");
        Exec.runAndWait("xset s reset");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
