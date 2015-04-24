package pl.rzarajczyk.utils.system.monitor;

import java.io.IOException;

/**
 *
 * @author rafalz
 */
class EmptyMonitorController implements MonitorController {

    @Override
    public void turnOn() throws IOException {
        // nothing
    }

    @Override
    public void turnOff() throws IOException {
        // nothing
    }
    
}
