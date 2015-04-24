package pl.rzarajczyk.utils.temp;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import pl.rzarajczyk.utils.system.paths.PathsFactory;

/**
 *
 * @author rafalz
 */
public abstract class Temp {
    
    public static File file(Class<?> clazz) throws IOException {
        return file(clazz, ".tmp");
    }
    
    public static File file(Class<?> clazz, String postfix) throws IOException {
        return File.createTempFile(getPrefix(clazz), postfix);
    }
    
    public static File volatileFile(Class<?> clazz) throws IOException {
        return volatileFile(clazz, ".tmp");
    }
    
    public static File volatileFile(Class<?> clazz, String postfix) throws IOException {
        File file = file(clazz, postfix);
        file.deleteOnExit();
        return file;
    }
    
    public static File dir(Class<?> clazz) throws IOException {
        String name = "";
        File dir;
        File tmp = PathsFactory.getInstance().getTempDir();
        Random random = new Random();
        String prefix = getPrefix(clazz);
        do {
            name = prefix + "-" + random.nextInt(Integer.MAX_VALUE);
            dir = new File(tmp, name);
        } while ( !dir.mkdir() );
        return dir;
    }
    
    public static File volatileDir(Class<?> clazz) throws IOException {
        File dir = dir(clazz);
        Runtime.getRuntime().addShutdownHook(new ShutdownHookDeleteDirThread(dir));
        return dir;
    }
    
    static String getPrefix(Class<?> clazz) {
        return clazz.getCanonicalName();
    }
}
