package pl.rzarajczyk.utils.system.link;

import java.io.File;
import java.io.IOException;
import pl.rzarajczyk.utils.system.os.OperatingSystem;

/**
 *
 * @author Rafal
 */
public class LinkFactory {
    
    public boolean isSupported() {
        return OperatingSystem.isWindows() || OperatingSystem.isUnix();
    }

    public static Link open(File link) throws IOException {
         if ( OperatingSystem.isWindows() ) {
             return new WindowsLink(link);
         } else if ( OperatingSystem.isUnix() ) {
             return new UnixDesktopLink(link);
         }
         return null;
    }
    
    public static Link create(File link, String target) throws IOException {
         if ( OperatingSystem.isWindows() ) {
             return new WindowsLink(link, target);
         } else if ( OperatingSystem.isUnix() ) {
             return new UnixDesktopLink(link, target);
         }
         return null;
    }
    
}
