package pl.rzarajczyk.utils.resources;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.rzarajczyk.utils.temp.Temp;

/**
 *
 * @author Rafal
 */
public class DirResourceManagerTest {
    
    private File testDir;
    
    @Before
    public void setUp() throws IOException {
        testDir = Temp.volatileDir(getClass());

        File a = new File(testDir, "dir/a");
        File b = new File(testDir, "dir/b");
        File c = new File(testDir, "dir/b/c");
        a.mkdirs();
        b.mkdirs();
        c.mkdirs();
    }
    
    @Test
    public void shouldListDirCorrectly() throws IOException {
        List<String> contents = new DirResourceManager(testDir).getContents();
        List<String> expected = Lists.newArrayList();
        expected.add("dir/");
        expected.add("dir/a/");
        expected.add("dir/b/");
        expected.add("dir/b/c/");
        Assert.assertEquals(new HashSet<String>(expected), new HashSet<String>(contents));
    }
    
}
