package pl.rzarajczyk.breaktime.utils;

import java.io.File;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;
import pl.rzarajczyk.utils.system.os.OperatingSystem;


/**
 * Note: OS tests source: http://lopica.sourceforge.net/os.html
 * 
 * @author rzarajczyk
 */
public class UtilsTest {

    @Test
    public void toTimeStringShouldWorkCorrectly() {
        Assert.assertEquals("30:23", Utils.toTimeString(Utils.SECOND * (30 * 60 + 23)));
        Assert.assertEquals("03:23", Utils.toTimeString(Utils.SECOND * (3 * 60 + 23)));
        Assert.assertEquals("80:23", Utils.toTimeString(Utils.SECOND * (80 * 60 + 23)));
        Assert.assertEquals("-00:01", Utils.toTimeString(Utils.SECOND * -1));
    }
    
    @Test
    public void parseTimeStringShouldWorkCorrectly() {
        Assert.assertEquals(Utils.SECOND * (30 * 60 + 23), Utils.parseTimeString("30:23"));
        Assert.assertEquals(Utils.SECOND * (3 * 60 + 23), Utils.parseTimeString("03:23"));
        Assert.assertEquals(Utils.SECOND * (3 * 60 + 23), Utils.parseTimeString("3:23"));
        Assert.assertEquals(Utils.SECOND * (80 * 60 + 23), Utils.parseTimeString("80:23"));
        Assert.assertEquals(Utils.SECOND * -1, Utils.parseTimeString("-00:01"));
    }
}