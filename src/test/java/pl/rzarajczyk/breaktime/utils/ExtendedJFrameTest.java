package pl.rzarajczyk.breaktime.utils;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author rafalz
 */
public class ExtendedJFrameTest {
    
    private ExtendedJFrame frame = new SwingUtilsTestWindow();
    
    @After
    public void tearDown() {
        if ( frame.isFullScreen() ) {
            frame.setFullScreen(false);
        }
    }
    
    @Test
    public void setFullScreenShouldBeDisabledByDefault() {
        Assert.assertFalse( frame.isFullScreen() );
    }
    
    @Test
    public void setFullScreenShouldNotThrowIfCalledOnVisibleWindow() {
        frame.setVisible(true);
        frame.setFullScreen(true);
    }
    
    @Test
    public void setFullScreenAndGetFullScreenShouldWorkCorrectly() {
        frame.dispose();
        frame.setFullScreen(true);
        Assert.assertTrue(frame.isFullScreen());
        frame.setFullScreen(false);
    }
    
    @Test
    public void setFullScreenOnShouldMakeWindowBigEnough() throws InterruptedException {
        frame.dispose();
        frame.setFullScreen(true);
        frame.setVisible(true);
        Thread.sleep(10);
        int width = frame.getWidth();
        int height = frame.getHeight();
        int screenWidth = SwingUtils.getScreenWidth();
        int screenHeight = SwingUtils.getScreenHeight();
        Assert.assertTrue("frame width = " + width + ", screen width = " + screenWidth, Math.abs(width - screenWidth) < 10);
        Assert.assertTrue("frame height = " + height + ", screen height = " + screenHeight, Math.abs(height - screenHeight) < 10);
        frame.setFullScreen(false);
    }
    
    @Test
    public void setFullScreenShouldeBeRepeatable() throws InterruptedException {
        for ( int i=0; i<10; i++ ) {
            setFullScreenOnShouldMakeWindowBigEnough();
        }
    }
}
