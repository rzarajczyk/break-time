package pl.rzarajczyk.utils.system.monitor;

import java.io.IOException;

// =========================================================================
/**
 *
 * @author rafalz
 */
public interface MonitorController {

    void turnOn() throws IOException;

    void turnOff() throws IOException;
    
}
