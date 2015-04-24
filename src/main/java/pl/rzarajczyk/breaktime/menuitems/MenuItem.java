package pl.rzarajczyk.breaktime.menuitems;

import java.awt.event.MouseEvent;
import pl.rzarajczyk.breaktime.utils.localization.Localizable;

/**
 *
 * @author Rafal
 */
public interface MenuItem extends Localizable {

    void execute(MouseEvent e);

    String getIcon();

    String getName();
    
}
