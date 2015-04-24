package pl.rzarajczyk.utils.system.link;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.rzarajczyk.utils.resources.ResourceManagerFactory;
import pl.rzarajczyk.utils.temp.Temp;

/**
 *
 * @author rafalz
 */
public class UnixDesktopLinkTest {

    private UnixDesktopLink link;
    private File linkFile;
    private String originalTargetFile;
    private String newTargetFile;
    
    @Before
    public void setUp() throws Exception {
        File originalLinkFile = new File(ResourceManagerFactory.getInstance().getResourceByPath("ls.desktop").toURI());
        linkFile = Temp.volatileFile(getClass(), ".desktop");
        Files.copy(originalLinkFile, linkFile);
        
        newTargetFile = "/bin/pwd";
        originalTargetFile = "/bin/ls";
        link = new UnixDesktopLink(linkFile);
    }
    
    @Test
    public void shouldBehaveAsNormalFile() throws IOException {
        Assert.assertTrue(link instanceof File);
        Assert.assertEquals(linkFile.canExecute(), link.canExecute());
        Assert.assertEquals(linkFile.canRead(), link.canRead());
        Assert.assertEquals(linkFile.canWrite(), link.canWrite());
        Assert.assertEquals(linkFile.exists(), link.exists());
        Assert.assertEquals(linkFile.getAbsoluteFile(), link.getAbsoluteFile());
        Assert.assertEquals(linkFile.getAbsolutePath(), link.getAbsolutePath());
        Assert.assertEquals(linkFile.getCanonicalFile(), link.getCanonicalFile());
        Assert.assertEquals(linkFile.getCanonicalPath(), link.getCanonicalPath());
        Assert.assertEquals(linkFile.getFreeSpace(), link.getFreeSpace());
        Assert.assertEquals(linkFile.getName(), link.getName());
        Assert.assertEquals(linkFile.getParent(), link.getParent());
        Assert.assertEquals(linkFile.getParentFile(), link.getParentFile());
        Assert.assertEquals(linkFile.getPath(), link.getPath());
        Assert.assertEquals(linkFile.getTotalSpace(), link.getTotalSpace());
        Assert.assertEquals(linkFile.getUsableSpace(), link.getUsableSpace());
        Assert.assertEquals(linkFile.isAbsolute(), link.isAbsolute());
        Assert.assertEquals(linkFile.isDirectory(), link.isDirectory());
        Assert.assertEquals(linkFile.isFile(), link.isFile());
        Assert.assertEquals(linkFile.isHidden(), link.isHidden());
        Assert.assertEquals(linkFile.lastModified(), link.lastModified());
        Assert.assertEquals(linkFile.length(), link.length());
        Assert.assertArrayEquals(linkFile.list(), link.list());
        Assert.assertArrayEquals(linkFile.listFiles(), link.listFiles());
        Assert.assertEquals(linkFile.toURI(), link.toURI());
    }
    
    @Test
    public void shouldEqualToNormalFile() {
        Assert.assertEquals(link, linkFile);
        Assert.assertEquals(linkFile, link);
    }
    
    @Test
    public void getTargetFileShouldPointToCorrectFile() throws IOException {
        Assert.assertEquals(originalTargetFile, link.getTarget());
    }
    
    @Test
    public void setTargetFileToNullShouldDeleteLink() throws IOException {
        link.setTarget(null);
        Assert.assertEquals(false, link.exists());
    }
    
    @Test
    public void setTargetFileToFileShouldChangeDestination() throws IOException, InterruptedException {
        System.out.println(link.getAbsolutePath());
        link.setTarget(newTargetFile);

        System.out.println("[" + newTargetFile + "]");
        System.out.println("[" + link.getTarget() + "]");
        Assert.assertEquals(newTargetFile, link.getTarget());
    }
    
}
