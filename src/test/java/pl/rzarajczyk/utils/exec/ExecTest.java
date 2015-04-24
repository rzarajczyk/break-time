package pl.rzarajczyk.utils.exec;

import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;
import pl.rzarajczyk.utils.system.os.OperatingSystem;

/**
 *
 * @author rafalz
 */
public class ExecTest {

    @Test
    public void onLinuxShouldCorrectlyTestCommandWithoutStdIn() throws IOException {
        if ( OperatingSystem.isUnix() ) {
            String uname = Exec.runAndWait("uname -a");
            System.out.println(uname);
            Assert.assertTrue(uname.contains("Linux"));
        }
    }
    
    @Test
    public void onLinuxShouldCorrectlyTestCommandWithStdIn() throws IOException {
        if ( OperatingSystem.isUnix() ) {
            String input = "Test test test";
            String output = Exec.runAndWait("cat", input);
            System.out.println(output);
            Assert.assertEquals(input, output);
        }
    }
    
    @Test
    public void onLinuxShouldCorrectlyHandleErrors() throws IOException {
        if ( OperatingSystem.isUnix() ) {
            try {
                Exec.runAndWait("rm fgdfujghsdfftbs");
                Assert.fail("Should be an exception here");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}
