package pl.rzarajczyk.utils.temp;

import java.io.File;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Rafal
 */
public class ShutdownHookDeleteDirThreadTest {

    @Test
    public void test() throws IOException, InterruptedException {
        File dir = Temp.dir(getClass());
        ShutdownHookDeleteDirThread thread = new ShutdownHookDeleteDirThread(dir);
        thread.start();
        thread.join();
        Assert.assertFalse(dir.exists());
    }
    
}
