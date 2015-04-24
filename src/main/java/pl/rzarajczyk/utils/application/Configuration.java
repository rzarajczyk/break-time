package pl.rzarajczyk.utils.application;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import javax.swing.UIManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pl.rzarajczyk.utils.log.LazyLogFactory;
import pl.rzarajczyk.utils.resources.ResourceManager;
import pl.rzarajczyk.utils.resources.ResourceManagerFactory;
import pl.rzarajczyk.utils.system.paths.PathsFactory;
import pl.rzarajczyk.utils.temp.Temp;

/**
 *
 * @author rafalz
 */
public class Configuration {
    
    public static final String MAVEN_PROPERTIES_PATH = "meta/meta.properties";

    private Properties properties;
    private ResourceManager resourceManager = ResourceManagerFactory.getInstance();
    private File temp;
    private Log log = LazyLogFactory.getLog(getClass());
    
    public Configuration() throws IOException {
        properties = new Properties();
        URL resource = resourceManager.getResourceByPath(MAVEN_PROPERTIES_PATH);
        InputStream is = resourceManager.read(resource);
        if ( is == null ) {
            log.warn("Could not find " + MAVEN_PROPERTIES_PATH + " file!");
        } else {
            try {
                properties.load(is);
            } finally {
                is.close();
            }
        }
    }
    
    public String getLookAndFeelName() {
        return UIManager.getSystemLookAndFeelClassName();
    }
    
    public File getStorageDir() throws IOException {
        File dir = PathsFactory.getInstance().getApplicationDataDir();
        dir = new File(dir, "." + getApplicationId());
        if ( ! dir.exists() ) {
            if ( ! dir.mkdirs() ) {
                throw new IOException("Could not create directory structure: " + dir.getAbsolutePath());
            }
        }
        return dir;
    }

    public File getTempDir() throws IOException {
        if ( temp == null ) {
            temp = Temp.dir(getClass());
        }
        return temp;
    }
    
    public String getApplicationId() {
        return properties.getProperty("artifactId");
    }
    
    public String getApplicationName() {
        return properties.getProperty("name");
    }
    
    public String getApplicationVersion() {
        return properties.getProperty("version");
    }
    
    public String [] getBeanConfigurations() throws IOException {
        List<URL> urls = resourceManager.findResourcesByPattern(".*spring/[a-zA-Z0-9-_]*\\.xml");
        String rootPath = resourceManager.getResourceByPath(MAVEN_PROPERTIES_PATH).toString();
        rootPath = rootPath.substring(0, rootPath.length() - MAVEN_PROPERTIES_PATH.length());
        List<String> strings = Lists.newArrayList();
        for ( URL url : urls ) {
            String path = url.toString();
            if ( ! path.startsWith(rootPath)  ) {
                log.warn("Unexpected resource path: [" + path + "] does not start with [" + rootPath + "]");
            } else {
                path = path.substring( rootPath.length() );
                strings.add(path);
            }
        }
        return strings.toArray( new String [0] );
    }

    
}
