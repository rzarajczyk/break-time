package pl.rzarajczyk.utils.resources;

import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rafalz
 */
public class AbstractResourceManagerTest {
    
    @Test
    public void findShouldWorkCorrectly() throws IOException {
        AbstractResourceManager finder = new StaticClasspathFinder() {
            @Override
            public URL getResourceByPath(String name) throws IOException {
                return new URL("file:" + name);
            }
            
        };
        ClasspathPathMatcher matcher = new ClasspathPathMatcher() {
            @Override
            public boolean matches(String path) {
                return path.contains("123");
            }
        };
        
        List<URL> result = finder.find(matcher);
        List<URL> expected = Lists.newArrayList(new URL("file:1234"), new URL("file:123"));
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void findByPrefixShouldCreateAppropriateMatcher() throws IOException {
        AbstractResourceManager finder = new StaticClasspathFinder() {
            @Override
            protected List<URL> find(ClasspathPathMatcher matcher) throws IOException {
                Assert.assertTrue("Matcher is of class [" + matcher.getClass() + "]", matcher instanceof PrefixClasspathPathMatcher);
                return null;
            }
        };
        finder.findResourcesByPrefix("");
    }
    
    @Test
    public void findByPatternStringShouldCreateAppropriateMatcher() throws IOException {
        AbstractResourceManager finder = new StaticClasspathFinder() {
            @Override
            protected List<URL> find(ClasspathPathMatcher matcher) throws IOException {
                Assert.assertTrue("Matcher is of class [" + matcher.getClass() + "]", matcher instanceof PatternClasspathPathMatcher);
                return null;
            }
        };
        finder.findResourcesByPattern("");
    }
    
    @Test
    public void findByPatternShouldCreateAppropriateMatcher() throws IOException {
        AbstractResourceManager finder = new StaticClasspathFinder() {
            @Override
            protected List<URL> find(ClasspathPathMatcher matcher) throws IOException {
                Assert.assertTrue("Matcher is of class [" + matcher.getClass() + "]", matcher instanceof PatternClasspathPathMatcher);
                return null;
            }
        };
        finder.findResourcesByPattern(Pattern.compile(""));
    }
    
    @Test
    public void getResourceShouldLocateExistingResources() throws IOException {
        ResourceManager manager = new NotImplementedResourceManafer();
        URL url = manager.getResourceByPath("test.jar");
        Assert.assertNotNull(url);
    }
    
    @Test(expected=FileNotFoundException.class)
    public void getResourceShouldNotLocateNonExistingResources() throws IOException {
        ResourceManager manager = new NotImplementedResourceManafer();
        manager.getResourceByPath("testfgv.jar");
    }
    
    @Test
    public void toStringShouldReadResource() throws IOException {
        ResourceManager manager = new NotImplementedResourceManafer();
        URL url = manager.getResourceByPath("test.txt");
        Assert.assertEquals("Ala ma kota", manager.toString(url));
    }
    
    @Test
    public void toByteArrayShouldReadResource() throws IOException {
        ResourceManager manager = new NotImplementedResourceManafer();
        URL url = manager.getResourceByPath("test.txt");
        Assert.assertArrayEquals("Ala ma kota".getBytes(), manager.toByteArray(url));
    }
    
    @Test
    public void readShouldReadResource() throws IOException {
        ResourceManager manager = new NotImplementedResourceManafer();
        URL url = manager.getResourceByPath("test.txt");
        InputStream is = manager.read(url);
        Reader reader = new InputStreamReader(is);
        String text = CharStreams.toString(reader);
        Assert.assertEquals("Ala ma kota", text);
    }
    
    // =========================================================================
    
    class NotImplementedResourceManafer extends AbstractResourceManager {

        @Override
        protected List<String> getContents() throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    
    class StaticClasspathFinder extends AbstractResourceManager {
        @Override
        protected List<String> getContents() throws IOException {
            return Lists.newArrayList("1234", "123", "124", "145");
        }
    }
    
}
