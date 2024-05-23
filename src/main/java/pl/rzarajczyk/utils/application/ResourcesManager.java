package pl.rzarajczyk.utils.application;

import com.google.common.base.Throwables;
import com.google.common.io.ByteStreams;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * TODO: restrict resources to certain folder only
 *
 * @author rafalz
 */
public class ResourcesManager {

    public URI getSourceCodeUri() throws IOException {
        Class<?> clazz = getClass();
        try {
            return clazz.getProtectionDomain().getCodeSource().getLocation().toURI();
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    public boolean isApplicationPacked() throws IOException {
        URI uri = getSourceCodeUri();
        return uri.getScheme().equals("jar");
    }

    public void unpack(String classpathResourcePrefix, File dir) throws IOException {
        var resolver = new PathMatchingResourcePatternResolver();
        var resources = resolver.getResources("classpath:" + classpathResourcePrefix + "/*");
        Arrays.asList(resources).forEach(it -> {
            try {
                var path = it.getURI().toString();
                path = path.substring(path.lastIndexOf(classpathResourcePrefix));
                var file = new File(dir, path);
                file.getParentFile().mkdirs();
                var input = it.getInputStream();
                try (var output = new BufferedOutputStream(new FileOutputStream(file))) {
                    ByteStreams.copy(input, output);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
