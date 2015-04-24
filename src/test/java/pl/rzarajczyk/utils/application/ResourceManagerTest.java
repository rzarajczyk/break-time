package pl.rzarajczyk.utils.application;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import pl.rzarajczyk.utils.temp.Temp;

/**
 *
 * @author rafalz
 */
public class ResourceManagerTest {
    
    private ResourcesManager manager;
    
    @Before
    public void setUp() {
        manager = new ResourcesManager();
    }
    
    @Test
    public void getDirContentsShouldCorrectlyListDir() throws IOException {
        File tmpDir = Temp.volatileDir(getClass());
        File a = new File(tmpDir, "dir/a");
        File b = new File(tmpDir, "dir/b");
        File c = new File(tmpDir, "dir/b/c");
        a.mkdirs();
        b.mkdirs();
        c.mkdirs();
        
        List<String> contents = manager.getDirContents(tmpDir, "/dir/b");
        System.out.println(contents);
    }
    
}
