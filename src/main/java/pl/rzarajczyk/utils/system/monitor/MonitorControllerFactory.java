package pl.rzarajczyk.utils.system.monitor;

import pl.rzarajczyk.utils.system.os.OperatingSystem;

/**
 *
 * @author rafalz
 */
public class MonitorControllerFactory {    
    public static MonitorController getMonitorController() {
        if ( OperatingSystem.isUnix() ) {
            return new UnixMonitorController();
        }
        if ( OperatingSystem.isWindows() ) {
            return new WindowsMonitorController();
        }
        
        return new EmptyMonitorController();
    }
    
    public static boolean isSupported() {
        return !(getMonitorController() instanceof EmptyMonitorController);
    }
}
