package pl.rzarajczyk.utils.application;

import com.google.common.base.Throwables;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import pl.rzarajczyk.breaktime.BreakTimeSettings;
import pl.rzarajczyk.breaktime.TrayIconRenderer;
import pl.rzarajczyk.utils.log.LazyLogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;


public class Application {
    
    @Autowired
    public Application(Configuration configuration) {
        try {
            this.configuration = configuration;
            resourcesManager = new ResourcesManager();
        } catch (Exception e) {
            Throwables.propagate(e);
        }
    }
    
    // =========================================================================
    // For JUnit tests

    void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    void setResourcesManager(ResourcesManager resourcesManager) {
        this.resourcesManager = resourcesManager;
    }
    
    // =========================================================================
    
    @Autowired
    private ApplicationContext context;
    
    private Log log = LazyLogFactory.getLog(getClass());
    private Configuration configuration;
    private ResourcesManager resourcesManager;


    public void start() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, AWTException, IOException, InterruptedException {
        log.info("Starting the application!");
        log.info(" * using Look-and-Feel: " + configuration.getLookAndFeelName());
        log.info(" * using storage dir: " + configuration.getStorageDir().getAbsolutePath());
        log.info(" * using temp dir: " + configuration.getTempDir().getAbsolutePath());
        
        if ( configuration.getLookAndFeelName() != null ) {
            UIManager.setLookAndFeel(configuration.getLookAndFeelName());
        }
        
        log.info("Application started!");
    }
    
    public void stop() {
        ((ConfigurableApplicationContext) context).close();
        System.exit(0);
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }

    public ResourcesManager getResourcesManager() {
        return resourcesManager;
    }
}
