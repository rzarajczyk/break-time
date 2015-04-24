package pl.rzarajczyk.utils.system.notification;

import java.io.IOException;

public interface Notification {

    /**
     * Shows on-screen notification to the user.
     * <p>
     * The notifications location, appearance and time of living is
     * implementation-dependand.
     * 
     * @param title Short summary of the notification
     * @param text Text of the notification
     * @param type Type of the notification
     * @throws IOException if some problems occurs
     */
    void show(String title, String text, NotificationType type) throws IOException;
    
}
