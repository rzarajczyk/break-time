package pl.rzarajczyk.utils.resources;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.logging.Log;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author Rafal
 */
public class ResourceManagerFactory {
    
    private static Log log = LazyLogFactory.getLog(ResourceManagerFactory.class);
    
    private static URI getSourceCodeUri() throws IOException {
        Class<?> clazz = ResourceManagerFactory.class;
        try {
            return clazz.getProtectionDomain().getCodeSource().getLocation().toURI();
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }        
    }
    
    static DeployType getDeployType(URI uri) throws IOException {
        return switch (uri.getScheme()) {
            case "file" -> DeployType.DIR;
            case "jar" -> DeployType.JAR;
            default -> throw new IOException("Cannot determine deploy type for sources located in [" + uri + "]");
        };
    }
    
    public static DeployType getDeployType() throws IOException {
        URI uri = getSourceCodeUri();
        return getDeployType(uri);
    }
    
    public static ResourceManager getInstance() throws IOException {
        switch ( getDeployType() ) {
            case JAR: return new JarResourceManager(getSourceCodeUri().getPath());
            case DIR: return new DirResourceManager(new File(getSourceCodeUri()));
        }
        throw new IllegalArgumentException("Unknown deploy type: " + getDeployType());
    }
    
}
