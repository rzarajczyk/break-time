package pl.rzarajczyk.utils.system.notification;

/**
 * Uses the standard Swing JFrame to show notification.
 */
class DefaultNotification extends AbstractIconizedNotification {
    
    

    @Override
    public void show(String title, String text, NotificationType type) {
        new NotificationJFrame(title, text, getIcon(type)).setVisible(true);
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
