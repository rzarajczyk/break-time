package pl.rzarajczyk.breaktime.menuitems;

import java.awt.event.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.Menu;
import pl.rzarajczyk.breaktime.gui.SettingsWindow;

/**
 *
 * @author Rafal
 */
public class SettingsMenuItem extends SimpleMenuItem {

    @Autowired
    private SettingsWindow settingsWindow;

    public SettingsMenuItem() {
        super("Settings");
    }
    
    @Override
    public void execute(MouseEvent e) {
        settingsWindow.setVisible(true);
    }

    @Override
    public String getIcon() {
        return "/icons/Settings.png";
    }
}
