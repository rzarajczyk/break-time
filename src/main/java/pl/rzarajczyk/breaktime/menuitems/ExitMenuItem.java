package pl.rzarajczyk.breaktime.menuitems;

import java.awt.event.MouseEvent;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.utils.application.Application;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author Rafal
 */
public class ExitMenuItem extends SimpleMenuItem {

    @Autowired
    private Application application;
    
    private Log log = LazyLogFactory.getLog(getClass());
    
    public ExitMenuItem() {
        super("Exit");
    }
    
    @Override
    public void execute(MouseEvent e) {
        try {
            application.stop();
        } catch (NullPointerException ex) {
            log.fatal("Fatal error", ex);
            System.exit(0);
        }
    }

    @Override
    public String getIcon() {
        return "/icons/Exit.png";
    }
}
