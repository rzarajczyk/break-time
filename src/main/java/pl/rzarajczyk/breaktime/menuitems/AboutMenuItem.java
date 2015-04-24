package pl.rzarajczyk.breaktime.menuitems;

import java.awt.event.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.gui.AboutWindow;

/**
 *
 * @author Rafal
 */
public class AboutMenuItem extends SimpleMenuItem {

    @Autowired
    private AboutWindow aboutWindow;

    public AboutMenuItem() {
        super("About");
    }
    
    @Override
    public void execute(MouseEvent e) {
        aboutWindow.setVisible(true);
    }

    @Override
    public String getIcon() {
        return "/icons/About.png";
    }
}
