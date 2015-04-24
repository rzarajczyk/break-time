package pl.rzarajczyk.utils.resources;

import pl.rzarajczyk.utils.resources.JarResourceManager;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.rzarajczyk.utils.application.Configuration;

/**
 *
 * @author Rafal
 */
public class JarResourceManagerTest {
    
    private String path;
    
    @Before
    public void setUp() throws IOException {
        File testJarFile = File.createTempFile("test", ".jar");
        path = testJarFile.getAbsolutePath();
        OutputStream os = new FileOutputStream(testJarFile);
        try {
            URL testJarInResourcesUrl = Resources.getResource("test.jar");
            Resources.copy(testJarInResourcesUrl, os);
        } finally {
            os.close();
        }
    }
    
    @After
    public void tearDown() throws IOException {
        new File(path).delete();
    }
    
    @Test
    public void shouldListJarCorrectly() throws IOException {
        List<String> contents = new JarResourceManager(path).getContents();
        List<String> expected = Lists.newArrayList();
        expected.add("a/");
        expected.add("a/b/");
        expected.add("a/b/d/");
        expected.add("a/b/d/e.txt");
        expected.add("a/c/");
        Assert.assertEquals(expected, contents);
    }    
}
