package pl.rzarajczyk.breaktime.gui;

import com.google.common.collect.Iterators;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import pl.rzarajczyk.breaktime.utils.localization.Localizable;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;
import pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint;
import pl.rzarajczyk.breaktime.utils.localization.swing.LocalizationUtils;
import pl.rzarajczyk.utils.system.notification.NotificationFactory;
import pl.rzarajczyk.utils.system.notification.NotificationType;

/**
 * Spring bean directly related to the initial notification window
 * <p>
 * Responsibilities:
 * <ul>
 *  <li>managing the layout and text of the notification</li>
 *  <li>automatically showing the initial notification after startup</li>
 * </ul>
 */
public class StartupNotification implements Localizable {
    
    private String title = "BreakTime!";
    private String text = "BreakTime is running!";
 
    @LocalizationPoint
    public void show() throws IOException {
        NotificationFactory.getInstance().show(title, text, NotificationType.INFO);
    }

    @Override
    public void beforeLocalization() {
        // nothing
    }

    @Override
    public void afterLocalization() {
        // nothing
    }

    @Override
    public Iterator<LocalizableElement> iterator() {
        return Iterators.forArray((LocalizableElement) new TitleLocalizableElement(), new TextLocalizableElement());
    }    
    
    @PostConstruct
    public void open() throws IOException{
        this.show();
    }
    
    // =========================================================================
    
    private class TitleLocalizableElement implements LocalizableElement {

        @Override
        public String getKey() {
            return LocalizationUtils.buildKey(StartupNotification.class, "title");
        }

        @Override
        public void localize(String text) {
            title = text;
        }
    }
    
    private class TextLocalizableElement implements LocalizableElement {

        @Override
        public String getKey() {
            return LocalizationUtils.buildKey(StartupNotification.class, "text");
        }

        @Override
        public void localize(String text) {
            StartupNotification.this.text = text;
        }
    }
}
