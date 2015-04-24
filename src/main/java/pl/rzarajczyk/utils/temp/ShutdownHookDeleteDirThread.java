package pl.rzarajczyk.utils.temp;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;

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
        try {
            Files.deleteRecursively(dir);
        } catch (IOException e) {
            // ignore
        }
    }
}
