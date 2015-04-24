package pl.rzarajczyk.utils.system.paths;

import com.google.common.base.Throwables;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

/**
 * Uses only information possible to get from the Java VM<ul>
 * <li>all user-related dirs are assumed to be equal to {@link FileSystemView#getDefaultDirectory()} </li>
 * <li>all system-related dirs are assumed to be the first root of the filesystem</li>
 * <li>temp dir is where {@link File#createTempFile(java.lang.String, java.lang.String)} is created</li>
 * </ul>
 */
public class DefaultPaths implements Paths {

    private FileSystemView fsv = FileSystemView.getFileSystemView();
    
    @Override
    public File getApplicationDataDir() {
        return getUserDir();
    }

    @Override
    public File getBinDir() {
        return getSystemDir();
    }

    @Override
    public File getDesktopDir() {
        return getUserDir();
    }

    @Override
    public File getDocumentsDir() {
        return getUserDir();
    }

    @Override
    public File getMusicDir() {
        return getUserDir();
    }

    @Override
    public File getPicturesDir() {
        return getUserDir();
    }

    @Override
    public File getStartupDir() {
        return getUserDir();
    }

    @Override
    public File getSystemDir() {
        return File.listRoots()[0];
    }

    @Override
    public File getTempDir() {
        try {
            File tmp = File.createTempFile("test", "test");
            tmp.deleteOnExit();
            String path = tmp.getAbsolutePath();
            System.out.println(path);
            int index = path.lastIndexOf(File.separator);
            path = path.substring(0, index);
            return new File(path);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public File getUserDir() {
        return fsv.getDefaultDirectory();
    }

    @Override
    public File getVideoDir() {
        return getUserDir();
    }
    
}
