package pl.rzarajczyk.utils.system.paths;

import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.WinNT.HRESULT;
import java.io.File;

/**
 * Uses WinAPI and environment variables to determine location on folders on Windows OS.
 */
public class WindowsPaths implements PathsWithSupportedMarker {
    
    private Shell32 shell32;

    public WindowsPaths() {
        try {
            shell32 = Shell32.INSTANCE;
        } catch (Error e) {
            // nothing
        }
    }

    @Override
    public boolean isSupported() {
        return shell32 != null;
    }

    private File get(int folder){
        char [] chars = new char[1024];
        HRESULT result = shell32.SHGetFolderPath(null, folder, null, null, chars);
        if ( result.intValue() != 0 )
            throw new RuntimeException("Could not get folder path");
        String path = new String(chars);
        int index = path.indexOf('\0');
        path = path.substring(0, index);
        return new File(path);
    }

    @Override
    public File getApplicationDataDir() {
        return get(0x1A);
    }

    @Override
    public File getBinDir() {
        return get(0x26);
    }

    @Override
    public File getDesktopDir() {
        return get(0x10);
    }

    @Override
    public File getDocumentsDir() {
        return get(0x05);
    }

    @Override
    public File getMusicDir() {
        return get(0x0D);
    }

    @Override
    public File getPicturesDir() {
        return get(0x27);
    }

    @Override
    public File getStartupDir() {
        return get(0x07);
    }

    @Override
    public File getSystemDir() {
        return get(0x25);
    }

    @Override
    public File getTempDir() {
        return new File( System.getenv("TEMP") );
    }

    @Override
    public File getUserDir() {
        return get(0x28);
    }

    @Override
    public File getVideoDir() {
        return get(0x0E);
    }
    
    // =========================================================================
    
    public File getStartMenuDir() {
        return get(0x0B);
    }
    
    
    
}
