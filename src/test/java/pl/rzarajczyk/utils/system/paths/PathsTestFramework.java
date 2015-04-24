package pl.rzarajczyk.utils.system.paths;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class PathsTestFramework {

    protected abstract Paths getInstance();
    
    protected Paths paths;
    
    @Before
    public void setUp() {
        paths = getInstance();
    }
    
    @Test
    public void writeAll() {
        for ( Entry<String, File> entry : executeAllGetters().entrySet() ) {
            System.out.println(entry.getKey() + " = " + entry.getValue().getAbsolutePath());
        }
    }
    
    @Test
    public void allDirsShouldExist() {
        for ( Entry<String, File> entry : executeAllGetters().entrySet() ) {
            String name = entry.getKey();
            File file = entry.getValue();
            Assert.assertTrue("Result file of method " + name + " (file " + file.getAbsolutePath() + ") does not exist!", file.exists());    
        }
    }
    
    protected Map<String, File> executeAllGetters() {
        Map<String, File> result = Maps.newHashMap();
        
        try {
            Class<?> clazz = paths.getClass();
            Method [] methods = clazz.getMethods();
            for ( Method method : methods ) {
                String name = method.getName();
                if ( name.startsWith("get") && method.getReturnType().equals(File.class) ) {
                    File file = (File) method.invoke(paths);
                    result.put(name, file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Throwables.propagate(e);
        }
        
        return result;
    }
    
    
}
