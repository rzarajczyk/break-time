package pl.rzarajczyk.utils.system.paths;

import com.google.common.collect.Maps;
import java.io.File;
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
@RunOnlyOn(OperatingSystemType.UNIX)
public class UnixPathsTest extends PathsTestFramework {

    @Override
    protected Paths getInstance() {
        return new UnixPaths();
    }
    
    @Test
    public void allDirsShouldMatchRegexps() {
        Map<String, String> regexps = getRegexps();
        for ( Entry<String, File> entry : executeAllGetters().entrySet() ) {
            String name = entry.getKey();
            File file = entry.getValue();
            String regexp = regexps.get(name);
            if ( regexp != null ) {
                Assert.assertTrue("Result file of method " + name + " (file " + file.getAbsolutePath() + ") does not match the regexp [" + regexp + "]!", file.getAbsolutePath().matches(regexp));
            }
        }
    }

    private Map<String, String> getRegexps() {
        Map<String, String> map = Maps.newHashMap();
        map.put("getPicturesDir", "/home/[a-zA-Z0-9_-]+/.+");
        map.put("getMusicDir", "/home/[a-zA-Z0-9_-]+/.+");
        map.put("getApplicationDataDir", "/home/[a-zA-Z0-9_-]+");
        map.put("getUserDir", "/home/[a-zA-Z0-9_-]+");
        map.put("getTempDir", "/tmp");
        map.put("getSystemDir", "/");
        map.put("getDesktopDir", "/home/[a-zA-Z0-9_-]+/.+");
        map.put("getVideoDir", "/home/[a-zA-Z0-9_-]+/.+");
        map.put("getStartupDir", "/home/[a-zA-Z0-9_-]+/.config/autostart");
        map.put("getDocumentsDir", "/home/[a-zA-Z0-9_-]+/.+");
        map.put("getBinDir", "/opt");
        return map;
    }
}
