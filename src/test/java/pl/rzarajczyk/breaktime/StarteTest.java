package pl.rzarajczyk.breaktime;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author rafalz
 */
public class StarteTest {
    
    public StarteTest() {
    }
    
    @Test
    public void hasPausedStateChangedShouldCorrectlyDetectChange() {
        State s1 = new State();
        s1.setPaused(true);
        State s2 = new State();
        s2.setPaused(false);
        Assert.assertTrue( s2.hasPausedStateChanged(s1) );
        Assert.assertTrue( s1.hasPausedStateChanged(s2) );
    }

}
