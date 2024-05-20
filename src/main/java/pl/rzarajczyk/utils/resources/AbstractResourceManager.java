package pl.rzarajczyk.utils.resources;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Rafal
 */
abstract class AbstractResourceManager implements ResourceManager {

    @Override
    public List<URL> findResourcesByPattern(Pattern pattern) throws IOException {
        return find( new PatternClasspathPathMatcher(pattern) );
    }

    @Override
    public List<URL> findResourcesByPattern(String pattern) throws IOException {
        return find( new PatternClasspathPathMatcher(pattern) );
    }

    @Override
    public List<URL> findResourcesByPrefix(String prefix) throws IOException {
        return find( new PrefixClasspathPathMatcher(prefix) );
    }
    
    protected abstract List<String> getContents() throws IOException;
    
    protected List<URL> find(ClasspathPathMatcher matcher) throws IOException {
        List<URL> result = Lists.newArrayList();
        for ( String path : getContents() ) {
            if ( matcher.matches(path) ) {
                URL url = getResourceByPath(path);
                result.add(url);
            }
        }
        return result;
    }

    @Override
    public URL getResourceByPath(String name) throws IOException {
        try {
            return Resources.getResource(name);
        } catch (IllegalArgumentException e) {
            throw new FileNotFoundException("Resource not found: [" + name + "]");
        }
    }

    @Override
    public String toString(URL url) throws IOException {
        return Resources.toString(url, Charsets.UTF_8);
    }

    @Override
    public byte[] toByteArray(URL url) throws IOException {
        return Resources.toByteArray(url);
    }

    @Override
    public InputStream read(URL url) throws IOException {
        return url.openStream();
    }
    
}
