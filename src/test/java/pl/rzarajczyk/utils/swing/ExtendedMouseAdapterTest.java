package pl.rzarajczyk.utils.swing;

import org.junit.Test;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;
import junit.framework.Assert;
import org.junit.Before;

import static org.mockito.Mockito.*;

/**
 *
 * @author Rafal
 */
public class ExtendedMouseAdapterTest {
    private static int COUNT = 10;
    private static int INTERVAL = 200;

    private ExtendedMouseAdapter adapter;
    private final AtomicInteger singleClicks = new AtomicInteger();
    private final AtomicInteger doubleClicks = new AtomicInteger();
    private final AtomicInteger rightClicks = new AtomicInteger();
    private final AtomicInteger middleClicks = new AtomicInteger();
    
    @Before
    public void setUp() {
        singleClicks.set(0);
        doubleClicks.set(0);
        middleClicks.set(0);
        rightClicks.set(0);
        adapter = new ExtendedMouseAdapter(INTERVAL) {
            @Override
            public void mouseDoubleClicked(MouseEvent e) {
                doubleClicks.incrementAndGet();
            }
            @Override
            public void mouseMiddleClicked(MouseEvent e) {
                middleClicks.incrementAndGet();
            }
            @Override
            public void mouseSingleClicked(MouseEvent e) {
                singleClicks.incrementAndGet();
            }
            @Override
            public void mouseRightClicked(MouseEvent e) {
                rightClicks.incrementAndGet();
            }
        };
    }
    
    @Test
    public void testRightClick() throws InterruptedException {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getButton()).thenReturn(MouseEvent.BUTTON3);
        for ( int i=0; i<COUNT; i++ ) {
            adapter.mouseClicked(event);
            Thread.sleep(INTERVAL / 2);
        }
        Assert.assertEquals(COUNT, rightClicks.get());
    }
    
    @Test
    public void testMiddleClick() throws InterruptedException {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getButton()).thenReturn(MouseEvent.BUTTON2);
        for ( int i=0; i<COUNT; i++ ) {
            adapter.mouseClicked(event);
            Thread.sleep(INTERVAL / 2);
        }
        Assert.assertEquals(COUNT, middleClicks.get());
    }
    
    @Test
    public void testLeftClick() throws InterruptedException {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getButton()).thenReturn(MouseEvent.BUTTON1);
        for ( int i=0; i<COUNT; i++ ) {
            adapter.mouseClicked(event);
            Thread.sleep(INTERVAL * 2);
        }
        Assert.assertEquals(COUNT, singleClicks.get());
    }
    
    @Test
    public void testDoubleClick() throws InterruptedException {
        MouseEvent event = mock(MouseEvent.class);
        when(event.getButton()).thenReturn(MouseEvent.BUTTON1);
        for ( int i=0; i<COUNT; i++ ) {
            adapter.mouseClicked(event);
            Thread.sleep(INTERVAL / 3);
        }
        Assert.assertEquals(COUNT / 2, doubleClicks.get());
    }
    
}