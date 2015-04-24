package pl.rzarajczyk.breaktime.utils;

import org.junit.Ignore;

/**
 *
 * @author rzarajczyk
 */
@Ignore
public class SwingUtilsTest {
    
    
//    @Before
//    public void setUp() {
//        SwingUtils.setToolkit(null);
//    }
//    
//    @Test
//    public void getScreenWidthShouldDelegateCallToToolkit() {
//        Toolkit tk = mock(Toolkit.class);
//        Dimension dm = mock(Dimension.class);
//        when(tk.getScreenSize()).thenReturn(dm);
//        SwingUtils.setToolkit(tk);
//        
//        SwingUtils.getScreenWidth();
//        
//        verify(dm).getWidth();
//    }
//    
//    @Test
//    public void getScreenHeightShouldDelegateCallToToolkit() {
//        Toolkit tk = mock(Toolkit.class);
//        Dimension dm = mock(Dimension.class);
//        when(tk.getScreenSize()).thenReturn(dm);
//        SwingUtils.setToolkit(tk);
//        
//        SwingUtils.getScreenHeight();
//        
//        verify(dm).getHeight();
//    }
//    
//    @Test
//    public void centerShouldCorrectlyCenterTheFrame() {
//        Toolkit tk = mock(Toolkit.class);
//        Dimension dm = mock(Dimension.class);
//        when(tk.getScreenSize()).thenReturn(dm);
//        when(dm.getWidth()).thenReturn(120.0);
//        when(dm.getHeight()).thenReturn(100.0);
//        SwingUtils.setToolkit(tk);
//        JFrame frame = mock(JFrame.class);
//        when(frame.getWidth()).thenReturn(60);
//        when(frame.getHeight()).thenReturn(50);
//        int expectedX = 30;
//        int expectedY = 25;
//        final AtomicInteger actualX = new AtomicInteger();
//        final AtomicInteger actualY = new AtomicInteger();
//        doAnswer( new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                actualX.set((Integer) invocation.getArguments()[0]);
//                actualY.set((Integer) invocation.getArguments()[1]);
//                return null;
//            }
//        } ).when(frame).setLocation(anyInt(), anyInt());
//        
//        SwingUtils.center(frame);
//        
//        Assert.assertEquals(expectedX, actualX.get());
//        Assert.assertEquals(expectedY, actualY.get());
//    }
}
