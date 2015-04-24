package pl.rzarajczyk.breaktime.menuitems;

import com.google.common.collect.Iterators;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.BreakManager;
import pl.rzarajczyk.breaktime.TrayIconRenderer;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;

/**
 *
 * @author rafalz
 */
public class PauseMenuItem implements MenuItem {
    
    @Autowired
    private BreakManager manager;
    
    @Autowired
    private TrayIconRenderer tray;
    
    private String resume = "Resume";
    private String pause = "Pause";

    @Override
    public String getName() {
        return manager.isPaused() ? resume : pause;
    }

    @Override
    public String getIcon() {
        return manager.isPaused() ? "/icons/Resume.png" : "/icons/Pause.png";
    }

    @Override
    public void execute(MouseEvent e) {
        boolean paused = ! manager.isPaused();
        manager.setPaused(paused);
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
        return Iterators.forArray(new PauseLocalizableEmenent(), new ResumeLocalizableEmenent());
    }
    
    // =========================================================================
    
    class PauseLocalizableEmenent implements LocalizableElement {
        @Override
        public String getKey() {
            return PauseMenuItem.class.getCanonicalName() + "#pause";
        }

        @Override
        public void localize(String text) {
            pause = text;
        }
    }
    
    class ResumeLocalizableEmenent implements LocalizableElement {
        @Override
        public String getKey() {
            return PauseMenuItem.class.getCanonicalName() + "#resume";
        }

        @Override
        public void localize(String text) {
            resume = text;
        }
    }
}
