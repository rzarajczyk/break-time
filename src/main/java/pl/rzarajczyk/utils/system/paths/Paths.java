package pl.rzarajczyk.utils.system.paths;

import java.io.File;

public interface Paths {

    /**
     * Gets the storage for temporary files. Files stored in the temporary directory
     * might disappear from time to time.
     * @return Temp directory location
     */
    File getTempDir();
    
    /**
     * Gets the user's default directory location. All private files should be
     * stored there. Note, that certain types of media files might be also stored
     * in {@link #getDocumentsDir()}, {@link #getMusicDir()}, {@link #getVideoDir()},
     * {@link #getPicturesDir()}
     * @return User's default directory location. 
     */
    File getUserDir();
    
    /**
     * Gets the location of a user-specific directory, where applications should
     * put their private data. Note, that is is strongly recommended for the application
     * to create a separate subdirectory inside this dir and name it in a 
     * platform-dependant way.
     * @return application's data directory location
     */
    File getApplicationDataDir();
    
    /**
     * Gets the location of a folder representing a "Desktop".
     * This is a platform-dependant feature, might not be supported on all
     * platforms.
     * @return desktop directory location
     */
    File getDesktopDir();

    /**
     * Gets the location of a folder containing links to startup applications.
     * This is a platform-dependant feature, might not be supported on all
     * platforms.
     * @return startup directory location
     */
    File getStartupDir();
    
    /**
     * Gets the default storage place for user's document files. Usually it is inside
     * {@link #getUserDir()} directory
     * @return documents directory location.
     */
    File getDocumentsDir();
    
    /**
     * Gets the default storage place for user's picture files. Usually it is inside
     * {@link #getUserDir()} directory
     * @return pictures directory location.
     */
    File getPicturesDir();
    
    /**
     * Gets the default storage place for user's music files. Usually it is inside
     * {@link #getUserDir()} directory
     * @return music directory location.
     */
    File getMusicDir();
    
    /**
     * Gets the default storage place for user's video files. Usually it is inside
     * {@link #getUserDir()} directory
     * @return video directory location.
     */
    File getVideoDir();
    
    /**
     * Gets the location of a directory, where applications put their binary files.
     * This is a platform-dependant feature, might not be supported on all
     * platforms.
     * @return application's binary data directory location
     */
    File getBinDir();
    
    /**
     * Gets the location of a directory, where the OS put its binary files.
     * This is a platform-dependant feature, might not be supported on all
     * platforms.
     * @return OS's binary data directory location
     */
    File getSystemDir();
}
