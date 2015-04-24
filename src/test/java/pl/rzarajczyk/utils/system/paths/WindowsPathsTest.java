package pl.rzarajczyk.utils.system.paths;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.rzarajczyk.utils.junit.OsAwareTestRunner;
import pl.rzarajczyk.utils.junit.RunOnlyOn;
import pl.rzarajczyk.utils.system.os.OperatingSystemType;

/**
 *
 * @author rafalz
 */
@RunWith(OsAwareTestRunner.class)
@RunOnlyOn(OperatingSystemType.WINDOWS)
public class WindowsPathsTest extends PathsTestFramework {
    
    @Override
    protected Paths getInstance() {
        return new WindowsPaths();
    }

    @Test
    public void allPersonalDirsShouldBeInsideHome() {
        Map<String, File> map = executeAllGetters();
        File home = paths.getUserDir();
        for ( Entry<String, File> entry : map.entrySet() ) {
            String name = entry.getKey();
            File file = entry.getValue();
            if ( ! Arrays.asList("getSystemDir", "getBinDir").contains(name) ) {
                String homePath = home.getAbsolutePath();
                String currentPath = file.getAbsolutePath();
                Assert.assertTrue("Result file of method " + name + " (file " + file.getAbsolutePath() + ") is not inside home directory [" + home.getAbsolutePath() + "]!", currentPath.startsWith(homePath));    
            }
        }
    }
}
