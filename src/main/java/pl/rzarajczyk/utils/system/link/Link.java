package pl.rzarajczyk.utils.system.link;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Rafal
 */
public abstract class Link extends File {

    Link(File linkFile) throws IOException {
        super(linkFile.getAbsolutePath());
        validate();
    }
    
    Link(File linkFile, String target) throws IOException {
        this(linkFile);
        setTarget(target);
    }
    
    protected abstract void validate() throws IOException, NotLinkException;
    
    public abstract String getTarget() throws IOException;
    
    public abstract void setTarget(String target) throws IOException;
    
}
