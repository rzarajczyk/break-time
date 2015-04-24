package pl.rzarajczyk.utils.system.monitor;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.io.IOException;

/**
 *
 * @author Rafal
 */
class WindowsMonitorController implements MonitorController {

    private User32 library;
    private Exception exception;

    public WindowsMonitorController() {
        try {
            library = (User32) Native.loadLibrary("User32", User32.class);
        } catch (Exception e) {
            this.exception = e;
        }
    }
    
    @Override
    public void turnOn() throws IOException {
        if ( exception != null  ) {
            throw new IOException(exception);
        }
        library.SendMessageW(0xffff, 0x0112, 0xF170, -1);
    }

    @Override
    public void turnOff() throws IOException {
        if ( exception != null  ) {
            throw new IOException(exception);
        }
        library.SendMessageW(0xffff, 0x0112, 0xF170, 2);
    }
    
    // =========================================================================
    
    public interface User32 extends Library {
        Pointer SendMessageW(int hwnd, int msg, int wParam, int lParam);
    }
}
