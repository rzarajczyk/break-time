package pl.rzarajczyk.breaktime.gui;

import com.google.common.collect.Iterators;
import java.io.IOException;
import java.util.Iterator;
import pl.rzarajczyk.breaktime.utils.localization.Localizable;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;
import pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint;
import pl.rzarajczyk.breaktime.utils.localization.swing.LocalizationUtils;
import pl.rzarajczyk.utils.system.notification.NotificationFactory;
import pl.rzarajczyk.utils.system.notification.NotificationType;

/**
 * Spring bean directly related to the notification before breaks window
 * <p>
 * Responsibilities:
 * <ul>
 *  <li>managing the layout and text of the notification</li>
 *  <li>showing the notification before break on demand</li>
 *  <li>providing interface for triggering the notification</li>
 * </ul>
 */
public class BreakNotification implements Localizable {
    
    private String title = "Break soon";
    private String text = "The break will start soon";
 
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
    
    // =========================================================================
    
    private class TitleLocalizableElement implements LocalizableElement {

        @Override
        public String getKey() {
            return LocalizationUtils.buildKey(BreakNotification.class, "title");
        }

        @Override
        public void localize(String text) {
            title = text;
        }
    }
    
    private class TextLocalizableElement implements LocalizableElement {

        @Override
        public String getKey() {
            return LocalizationUtils.buildKey(BreakNotification.class, "text");
        }

        @Override
        public void localize(String text) {
            BreakNotification.this.text = text;
        }
    }
}
