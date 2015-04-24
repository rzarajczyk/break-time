package pl.rzarajczyk.breaktime.utils;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author rafalz
 */
@Ignore
public class SettingsTest {
    
    private Settings settings;
    private File file;
    
    @Before
    public void setUp() throws IOException {
        file = File.createTempFile("generic-settings-test", "xml");
        file.delete();
//        settings = new Settings(file, true);
    }
    
    @After
    public void tearDown() throws IOException {
//        settings.close();
    }
    
//    @Test
//    public void setAndGetOnIntShouldWork() {
//        int value = 1;
//        settings.set("value", value);
//        Assert.assertEquals(value, settings.get("value", 0).intValue());
//    }
//    
//    @Test
//    public void setAndGetOnLongShouldWork() {
//        long value = 1l;
//        settings.set("value", value);
//        Assert.assertEquals(value, settings.get("value", 0l).longValue());
//    }
//    
//    @Test
//    public void setAndGetOnDoubleShouldWork() {
//        double value = 1.0;
//        settings.set("value", value);
//        Assert.assertEquals(value, settings.get("value", 0.0).doubleValue());
//    }
//    
//    @Test
//    public void setAndGetOnFloatShouldWork() {
//        float value = 1.0f;
//        settings.set("value", value);
//        Assert.assertEquals(value, settings.get("value", 0.0f).floatValue());
//    }
//    
//    @Test
//    public void setAndGetOnByteShouldWork() {
//        byte value = 1;
//        settings.set("value", value);
//        Assert.assertEquals(value, settings.get("value", 0).byteValue());
//    }
//    
//    @Test
//    public void setAndGetOnBoolenShouldWork() {
//        boolean value = true;
//        settings.set("value", value);
//        Assert.assertEquals(value, settings.get("value", false).booleanValue());
//    }
//    
//    @Test
//    public void setNullShouldRemoveMapping() {
//        String value1 = "value1";
//        String value2 = "value2";
//        String key = "key";
//        
//        settings.set(key, value1);
//        settings.set(key, null);
//        
//        Assert.assertEquals(value2, settings.get(key, value2));
//    }
    
}
