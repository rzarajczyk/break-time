package pl.rzarajczyk.utils.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rafalz
 */
public class InputStreamReadingThreadTest {

    private byte [] data;
    private InputStream is;
    private InputStreamReadingThread thread;
    
    @Before
    public void setUp() {
        data = new byte[1024000];
        Random random = new Random();
        random.nextBytes(data);
        is = new ByteArrayInputStream(data);
        thread = new InputStreamReadingThread(is);
    }
    
    @Test
    public void shouldGatherAllBytes() throws InterruptedException {
        thread.start();
        thread.waitFor();
        byte [] acquired = thread.toByteArray();
        Assert.assertArrayEquals(data, acquired);
    }
    
    @Test
    public void shouldBeFinidhedAtTheEnd() throws InterruptedException {
        // TODO: test the state before waitFor
        thread.start();
        thread.waitFor();
        Assert.assertTrue(thread.isFinished());
    }
    
    @Test
    public void shouldNotHaveAnyException() throws InterruptedException {
        // TODO: check passing the exception
        thread.start();
        thread.waitFor();
        Assert.assertNull(thread.getException());
    }
    
}
