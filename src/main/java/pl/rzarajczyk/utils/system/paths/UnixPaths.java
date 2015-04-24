package pl.rzarajczyk.utils.system.paths;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.apache.commons.logging.Log;
import pl.rzarajczyk.utils.exec.Exec;
import pl.rzarajczyk.utils.log.LazyLogFactory;
import pl.rzarajczyk.utils.system.os.OperatingSystem;
import pl.rzarajczyk.utils.temp.Temp;

/**
 * Uses default Linux directory structure, gets the HOME location from environment
 * variable, and - if present - variables stored in $HOME/.config/user-dirs.dirs
 * to determine user multimedia folders.
 */
public class UnixPaths implements PathsWithSupportedMarker {
    
    private Log log = LazyLogFactory.getLog(this.getClass());

    @Override
    public boolean isSupported() {
        return OperatingSystem.isUnix();
    }

    @Override
    public File getApplicationDataDir() {
        return getUserDir();
    }

    @Override
    public File getBinDir() {
        return new File("/opt");
    }

    @Override
    public File getDesktopDir() {
        return getXdgUserDir("XDG_DESKTOP_DIR");
    }

    @Override
    public File getDocumentsDir() {
        return getXdgUserDir("XDG_DOCUMENTS_DIR");
    }

    @Override
    public File getMusicDir() {
        return getXdgUserDir("XDG_MUSIC_DIR");
    }

    @Override
    public File getPicturesDir() {
        return getXdgUserDir("XDG_PICTURES_DIR");
    }

    @Override
    public File getStartupDir() {
        return new File(getUserDir(), ".config/autostart");
    }

    @Override
    public File getSystemDir() {
        return new File("/");
    }

    @Override
    public File getTempDir() {
        return new File("/tmp");
    }

    @Override
    public File getUserDir() {
        return new File( System.getenv("HOME") );
    }

    @Override
    public File getVideoDir() {
        return getXdgUserDir("XDG_VIDEOS_DIR");
    }
    
    private File getXdgUserDir(String name) {
        try {
            StringBuilder script = new StringBuilder();
            script.append("#!/bin/bash\n");
            script.append("cp $HOME/.config/user-dirs.dirs /tmp/user-dirs.dirs\n");
            script.append("chmod u+x /tmp/user-dirs.dirs\n");
            script.append(". /tmp/user-dirs.dirs\n");
            script.append("echo $").append(name).append("\n");
            File tmpFile = Temp.volatileFile(getClass(), ".sh");
            Files.write(script.toString(), tmpFile, Charsets.UTF_8);
            tmpFile.setExecutable(true);
            String commandResult = Exec.runAndWait(tmpFile.getAbsolutePath());
            File result = new File(commandResult);
            if ( result.exists() ) {
                return result;
            }
        } catch (IOException e) {
            log.warn("Could not find XDG custom dirs!");
        }
        return getUserDir();
    }
    
    
    
}
