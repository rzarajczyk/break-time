package pl.rzarajczyk.utils.temp;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.springframework.util.FileSystemUtils;

/**
 *
 * @author Rafal
 */
class ShutdownHookDeleteDirThread extends Thread {

    private final File dir;
    
    public ShutdownHookDeleteDirThread(File dir) {
        super("ShutdownHookDeleteDirThread[" + dir.getAbsolutePath() + "]");
        this.dir = dir;
    }

    @Override
    public void run() {      
        FileSystemUtils.deleteRecursively(dir);
    }
}
