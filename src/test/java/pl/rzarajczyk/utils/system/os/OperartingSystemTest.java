package pl.rzarajczyk.utils.system.os;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rafalz
 */
public class OperartingSystemTest {
    
    private String osName;
    
    private void testOs(String [] names, OperatingSystemType type) {
        for ( String name : names ) {
            OperatingSystem.setSystemProperty(name);
            Assert.assertEquals(type, OperatingSystem.determine());
            Assert.assertEquals("name=" + name + ", type=" + type, type == OperatingSystemType.UNIX, OperatingSystem.isUnix() );
            Assert.assertEquals("name=" + name + ", type=" + type, type == OperatingSystemType.WINDOWS, OperatingSystem.isWindows() );
            Assert.assertEquals("name=" + name + ", type=" + type, type == OperatingSystemType.MAC, OperatingSystem.isMac() );
        }
    }

    @Before
    public void setUp() {
        osName = System.getProperty("os.name");
    }
    
    @After
    public void tearDown() {
        System.setProperty("os.name", osName);
    }
    
    @Test
    public void isUnixShouldDetect() {
        String [] names = new String [] {"Linux"};
        testOs(names, OperatingSystemType.UNIX);
    }
    
    @Test
    public void isWindowsShouldDetect() {
        String [] names = new String [] {"Windows 95", "Windows 98", "Windows Me", "Windows NT", "Windows 2000", "Windows XP", "Windows CE", "Windows Vista", "Windows 7"};
        testOs(names, OperatingSystemType.WINDOWS);
    }
    
    @Test
    public void isMacShouldDetect() {
        String [] names = new String [] {"Mac OS", "Mac OS X"};
        testOs(names, OperatingSystemType.MAC);
    }
    
    @Test
    public void isMacIsWindowsIsUnixShouldNotDetectOtherOs() {
        String [] names = new String [] {"OS/2", "Solaris", "SunOS", "MPE/iX", "HP-UX", "AIX", "OS/390", "FreeBSD", "Irix", "NetWare 4.11", "OSF1", "OpenVMS"};
        testOs(names, OperatingSystemType.UNKNOWN);        
    }
}
