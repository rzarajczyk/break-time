package pl.rzarajczyk.utils.resources;

import pl.rzarajczyk.utils.resources.PrefixClasspathPathMatcher;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rafal
 */
public class PrefixClasspathPathMatcherTest {
 
    private PrefixClasspathPathMatcher matcher;
    
    @Before
    public void setUp() {
        matcher = new PrefixClasspathPathMatcher("/a/b/c");
    }
    
    @Test
    public void matchesShouldMatchPathsWithCorrectPrefix() {
        assertMatches("/a/b/c");
        assertMatches("/a/b/c/");
        assertMatches("/a/b/c/d");
        assertMatches("/a/b/c/d/");
        assertMatches("/a/b/c/d/f");
        assertMatches("/a/b/c/e");
        assertMatches("/a/b/c/e/");
        assertMatches("/a/b/c/e/g");
    }
    
    @Test
    public void matchesShouldNotMatchWithIncorrectPrefix() {
        assertNotMatches("");
        assertNotMatches("/");
        assertNotMatches("/a");
        assertNotMatches("/a/b/");
        assertNotMatches("/a/c/b");
        assertNotMatches("/a/d/c/e/g");
    }
    
    private void assertMatches(String path) {
        Assert.assertTrue(path, matcher.matches(path));
    }
    
    private void assertNotMatches(String path) {
        Assert.assertFalse(path, matcher.matches(path));
    }
    
}
