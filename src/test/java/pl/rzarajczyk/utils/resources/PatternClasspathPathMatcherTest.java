package pl.rzarajczyk.utils.resources;

import pl.rzarajczyk.utils.resources.PatternClasspathPathMatcher;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rafal
 */
public class PatternClasspathPathMatcherTest {
 
    private PatternClasspathPathMatcher matcher;
    
    @Before
    public void setUp() throws IOException {
        matcher = new PatternClasspathPathMatcher("/a/[a-z]+/c/.*");
    }
    
    @Test
    public void matchesShouldMatchPathsWithCorrectPaths() {
        assertMatches("/a/b/c/");
        assertMatches("/a/ba/c/");
        assertMatches("/a/baobab/c/d");
        assertMatches("/a/bsgvsfgs/c/d/");
        assertMatches("/a/bfhvfjv/c/d/f");
        assertMatches("/a/dfgf/c/e");
        assertMatches("/a/b/c/e/");
        assertMatches("/a/b/c/e/g");
    }
    
    @Test
    public void matchesShouldNotMatchWithIncorrectPaths() {
        assertNotMatches("");
        assertNotMatches("/");
        assertNotMatches("/a");
        assertNotMatches("/a/b/");
        assertNotMatches("/a/c/b");
        assertNotMatches("/a//c");
    }
    
    private void assertMatches(String path) {
        Assert.assertTrue(path, matcher.matches(path));
    }
    
    private void assertNotMatches(String path) {
        Assert.assertFalse(path, matcher.matches(path));
    }
    
}
