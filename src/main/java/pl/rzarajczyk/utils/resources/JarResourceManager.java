package pl.rzarajczyk.utils.resources;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.URI;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author Rafal
 */
class JarResourceManager extends AbstractResourceManager {

    private String path;

    public JarResourceManager(String path) throws IOException {
        this.path = path;
    }
    
    @Override
    protected List<String> getContents() throws IOException {
        List<String> result = Lists.newArrayList();
        JarFile jarFile = new JarFile(path);
        try {
            Enumeration<JarEntry> enumeration = jarFile.entries();
            while (enumeration.hasMoreElements()) {
                JarEntry entry = enumeration.nextElement();
                String name = entry.getName();
                result.add(name);
            }
        } finally {
            jarFile.close();
        }
        
        return result;
    }
    
    
    
}
