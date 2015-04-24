package pl.rzarajczyk.utils.resources;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import junit.framework.Assert;
import org.junit.Test;
import pl.rzarajczyk.utils.temp.Temp;

/**
 *
 * @author rafalz
 */
public class ResourceManagerFactoryTest {
    
    @Test
    public void getDeployTypeDirShouldReturnCorrectType() throws IOException, URISyntaxException {
        File tmp = Temp.volatileDir(getClass());
        URI uri = tmp.toURI();
        DeployType type = ResourceManagerFactory.getDeployType(uri);
        Assert.assertEquals(DeployType.DIR, type);
    }
    
    @Test
    public void getDeployTypeJarShouldReturnCorrectType() throws IOException, URISyntaxException {
        File tmp = Temp.volatileFile(getClass(), ".jar");
        URI uri = tmp.toURI();
        DeployType type = ResourceManagerFactory.getDeployType(uri);
        Assert.assertEquals(DeployType.JAR, type);
    }
    
    @Test(expected=IOException.class)
    public void getDeployTypeOtherShouldReturnCorrectType() throws IOException, URISyntaxException {
        File tmp = Temp.volatileFile(getClass(), ".war");
        URI uri = tmp.toURI();
        ResourceManagerFactory.getDeployType(uri);
    }
}
