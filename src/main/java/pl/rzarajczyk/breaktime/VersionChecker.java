package pl.rzarajczyk.breaktime;

import java.net.MalformedURLException;
import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import pl.rzarajczyk.utils.application.Application;
import pl.rzarajczyk.utils.application.VersionCheck;

/**
 *
 * @author rafalz
 */
public class VersionChecker {

    @Autowired
    private Application application;
    
    @Autowired
    private BreakTimeSettings settings;
    
    @PostConstruct
    public void open() throws MalformedURLException {
        VersionCheck check = new VersionCheck(application.getConfiguration(), BreakTimeSettings.VERSION_CHECK_URL);
        if ( settings.isCheckForUpdates() ) {
            check(check);
        }
    }
    
    @Async
    private void check(VersionCheck check) {
        if ( check.hasNewerVersion() ) {
            JOptionPane.showMessageDialog(null, "Newer version available!", "Newer version available", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
