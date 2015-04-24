package pl.rzarajczyk.utils.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Rafal
 */
public interface ResourceManager {
    
    public URL getResourceByPath(String name) throws IOException;
    public List<URL> findResourcesByPrefix(String prefix) throws IOException;
    public List<URL> findResourcesByPattern(String pattern) throws IOException;
    public List<URL> findResourcesByPattern(Pattern pattern) throws IOException;
    
    public String toString(URL path) throws IOException;
    public byte [] toByteArray(URL path) throws IOException;
    public InputStream read(URL path) throws IOException;
}
 