package pl.rzarajczyk.utils.system.notification;

import java.io.IOException;

/**
 *
 * @author rafalz
 */
public class NotificationFactory {
    
    private static NotificationWithSupportedMarker [] instances = new NotificationWithSupportedMarker[] {
//        new UbuntuNotification()
    };
    
    public static Notification getInstance() throws IOException {
        for ( NotificationWithSupportedMarker instance : instances ) {
            if ( instance.isSupported() ) {
                return instance;
            }
        }
        return new DefaultNotification();
    }
    
}
