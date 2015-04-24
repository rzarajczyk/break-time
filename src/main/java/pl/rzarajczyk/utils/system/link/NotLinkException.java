package pl.rzarajczyk.utils.system.link;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author rafalz
 */
public class NotLinkException extends IOException {


    public NotLinkException(File file) {
        super("The file [" + file.getAbsolutePath() + "] is not a valid link");
    }
    
}
