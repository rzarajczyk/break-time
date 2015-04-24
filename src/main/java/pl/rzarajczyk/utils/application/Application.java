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
import pl.rzarajczyk.utils.log.LazyLogFactory;

public class Application {
    
    /* Singleton */
    private static Application instance;
    public static Application getInstance() {
        if ( instance == null ) {
            instance = new Application();
        }
        return instance;
    }
    private Application() {
        try {
            configuration = new Configuration();
            File logFile = new File(configuration.getStorageDir(), BreakTimeSettings.LOG_FILE_NAME);
            System.setProperty("LOG_FILE_PATH", logFile.getAbsolutePath());
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
    
    private AbstractXmlApplicationContext context;
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
        
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("application", this);
        GenericApplicationContext parent = new GenericApplicationContext(factory);
        parent.refresh();
        
        String [] springXmlFiles = configuration.getBeanConfigurations();
        context = new ClassPathXmlApplicationContext(springXmlFiles, parent);
        log.info("Application started!");
    }
    
    public void stop() {
        context.close();
        System.exit(0);
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }

    public ResourcesManager getResourcesManager() {
        return resourcesManager;
    }
}
