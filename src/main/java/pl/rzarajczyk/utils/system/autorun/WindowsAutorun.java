package pl.rzarajczyk.utils.system.autorun;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeMapped;
import com.sun.jna.PointerType;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import pl.rzarajczyk.utils.system.paths.PathsFactory;

/**
 *
 * @author Rafal
 */
class WindowsAutorun implements Autorun {

    private File dir;
    
    public WindowsAutorun() {
        dir = PathsFactory.getInstance().getStartupDir();
    }

    @Override
    public boolean exists(String entry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(String entry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String entry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
