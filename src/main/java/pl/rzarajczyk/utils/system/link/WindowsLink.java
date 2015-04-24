package pl.rzarajczyk.utils.system.link;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import pl.rzarajczyk.utils.exec.Exec;

/**
 * TODO: http://javasourcecode.org/html/open-source/jdk/jdk-6u23/sun/awt/shell/ShellFolder.html
 * @author Rafal
 */
public class WindowsLink extends Link {

    public WindowsLink(File linkFile) throws IOException {
        super(linkFile);
    }

    public WindowsLink(File linkFile, String targetFile) throws IOException {
        super(linkFile, targetFile);
    }

    @Override
    protected void validate() throws NotLinkException {
        if ( exists() && !getName().toLowerCase().endsWith(".lnk") ) {
            throw new NotLinkException(this);
        }
    }

    @Override
    public String getTarget() throws IOException {
        if ( ! this.exists() ) {
            return null;
        }
        LnkParser parser = new LnkParser(this);
        String path = parser.getRealFilename();
        return path;
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
        script.append("Set sh = CreateObject(\"WScript.Shell\")\r\n");
        script.append("Set shortcut = sh.CreateShortcut(\"").append( getAbsolutePath() ).append("\")\r\n");
        script.append("shortcut.TargetPath = \"").append( target ).append("\"\r\n");
        script.append("shortcut.Save\r\n");

        File file = File.createTempFile("script", ".vbs");
        Files.write(script.toString().getBytes(Charsets.UTF_8), file);
        
        Exec.runAndWait("wscript.exe " + file.getAbsolutePath());
        file.delete();
    }
}
