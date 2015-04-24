package pl.rzarajczyk.utils.system.link;

import java.io.File;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.rzarajczyk.utils.junit.OsAwareTestRunner;
import pl.rzarajczyk.utils.resources.ResourceManagerFactory;

/**
 *
 * @author Rafal
 */
public class LnkParserTest {
    
    private LnkParser parser;
    private File link;
    private String target;
    
    @Before
    public void setUp() throws Exception {
        link = new File(ResourceManagerFactory.getInstance().getResourceByPath("test.txt.lnk").toURI());
        target = "C:\\WINDOWS\\NOTEPAD.EXE";
        parser = new LnkParser(link);
    }

    @Test
    public void isLocalShouldBeCorrect() throws Exception {
        Assert.assertEquals(true, parser.isLocal());
    }
    
    @Test
    public void isDirectoryShouldBeCorrect() throws Exception {
        Assert.assertEquals(false, parser.isDirectory());
    }
    
    @Test
    public void getRealFilenameShouldReturnCorrectFile() throws Exception {
        Assert.assertEquals(target, parser.getRealFilename());
    }
    
}
