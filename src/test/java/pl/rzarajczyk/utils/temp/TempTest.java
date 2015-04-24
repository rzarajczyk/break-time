package pl.rzarajczyk.utils.temp;

import java.io.File;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.Test;
import pl.rzarajczyk.utils.system.paths.PathsFactory;

/**
 *
 * @author rafalz
 */
public class TempTest {

    @Test
    public void fileShouldCreateFileInTemp() throws IOException {
        File tmpDir = PathsFactory.getInstance().getTempDir();
        File testFile = Temp.file(getClass());
        String filePath = testFile.getAbsolutePath();
        String dirPath = tmpDir.getAbsolutePath();
        Assert.assertTrue("Temp path: [" + dirPath + "], file path: [" + filePath + "]", filePath.startsWith(dirPath));        
        testFile.delete();
    }
    
    @Test
    public void dirShouldCreateDirInTemp() throws IOException {
        File tmpDir = PathsFactory.getInstance().getTempDir();
        File testDir = Temp.dir(getClass());
        String filePath = testDir.getAbsolutePath();
        String dirPath = tmpDir.getAbsolutePath();
        Assert.assertTrue("Temp path: [" + dirPath + "], dir path: [" + filePath + "]", filePath.startsWith(dirPath));        
        testDir.delete();
    }
    
    @Test
    public void defaultTempFilePostfixShouldBeTmp() throws IOException {
        File file = Temp.file(getClass());
        Assert.assertTrue( file.getName().endsWith(".tmp") );
        file.delete();
    }
    
    @Test
    public void tempFilePostfixShouldBeRespected() throws IOException {
        File file = Temp.file(getClass(), ".abc");
        Assert.assertTrue( file.getName().endsWith(".abc") );
        file.delete();
    }
    
    @Test
    public void volatileFileShouldDisappear() throws IOException {
        File file = Temp.volatileFile(getClass());
        System.out.println("Voilatile File is [" + file.getAbsolutePath() + "]");
        System.out.println("Will end test now. Check if the file exists manually!");
    }
    
    @Test
    public void volatileDirShouldDisappear() throws IOException {
        File dir = Temp.volatileDir(getClass());
        File file = new File(dir, "1");
        file.createNewFile();
        System.out.println("Voilatile Dir is [" + dir.getAbsolutePath() + "]");
        System.out.println("Will end test now. Check if the file exists manually!");
    }
}
