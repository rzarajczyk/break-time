package pl.rzarajczyk.utils.system.link;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.rzarajczyk.utils.junit.OsAwareTestRunner;
import pl.rzarajczyk.utils.junit.RunOnlyOn;
import pl.rzarajczyk.utils.resources.ResourceManagerFactory;
import pl.rzarajczyk.utils.system.os.OperatingSystemType;
import pl.rzarajczyk.utils.temp.Temp;

/**
 *
 * @author Rafal
 */
@RunWith(OsAwareTestRunner.class)
public class WindowsLinkTest {

    private WindowsLink link;
    private File linkFile;
    private String originalTarget;
    private File newTargetFile;
    
    @Before
    public void setUp() throws Exception {
        File originalLinkFile = new File(ResourceManagerFactory.getInstance().getResourceByPath("test.txt.lnk").toURI());
        linkFile = Temp.volatileFile(getClass(), ".lnk");
        Files.copy(originalLinkFile, linkFile);
        
        newTargetFile = new File(ResourceManagerFactory.getInstance().getResourceByPath("test.txt").toURI());
        originalTarget ="C:\\WINDOWS\\NOTEPAD.EXE";
        link = new WindowsLink(linkFile);
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
        Assert.assertEquals(originalTarget, link.getTarget());
    }
    
    @Test
    @RunOnlyOn(OperatingSystemType.WINDOWS)
    public void setTargetFileToNullShouldDeleteLink() throws IOException {
        link.setTarget(null);
        Assert.assertEquals(false, link.exists());
    }
    
    @Test
    @RunOnlyOn(OperatingSystemType.WINDOWS)
    public void setTargetFileToFileShouldChangeDestination() throws IOException, InterruptedException {
        System.out.println(link.getAbsolutePath());
        link.setTarget(newTargetFile.getAbsolutePath());

        System.out.println("[" + newTargetFile.getAbsolutePath() + "]");
        System.out.println("[" + link.getTarget() + "]");
        Assert.assertEquals(newTargetFile.getAbsolutePath(), link.getTarget());
    }
    
}
