package pl.rzarajczyk.utils.system.link;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 *
 * @author rafalz
 */
public class UnixDesktopLink extends Link {

    public UnixDesktopLink(File linkFile, String target) throws IOException {
        super(linkFile, target);
    }

    public UnixDesktopLink(File linkFile) throws IOException {
        super(linkFile);
    }
    
    @Override
    protected void validate() throws IOException {
        if ( exists() && !getName().endsWith(".desktop") ) {
            throw new NotLinkException(this);
        }
    }

    @Override
    public String getTarget() throws IOException {
        if ( ! exists() ) {
            return null;
        }
        
        Properties properties = new Properties();
        Reader reader = Files.newReader(this, Charsets.UTF_8);
        try {
            properties.load(reader);
        } finally {
            reader.close();
        }
        return properties.getProperty("Exec");
    }

    @Override
    public void setTarget(String target) throws IOException {
        if ( target == null ) {
            this.delete();
            return;
        }
        
        if ( this.exists() && !this.getTarget().equals(target) ) {
            this.delete();
        }
        if ( ! this.exists() ) {
           create(target);
        }
    }
    
    private void create(String target) throws IOException {   
        StringBuilder script = new StringBuilder();
        script.append("#!/usr/bin/env xdg-open\n");
        script.append("\n");
        script.append("[Desktop Entry]\n");
        script.append("Encoding=UTF-8\n");
        script.append("Version=1.0\n");
        script.append("Type=Application\n");
        script.append("Terminal=false\n");
        script.append("Exec=").append(target).append("\n");
        script.append("Name=").append(target).append("\n");
        script.append("Icon=gnome-panel-launcher\n");
        Files.write(script, this, Charsets.UTF_8);
    }


    
    
}
