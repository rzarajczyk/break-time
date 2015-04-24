package pl.rzarajczyk.utils.exec;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import pl.rzarajczyk.utils.io.InputStreamReadingThread;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author rafalz
 */
public class Exec {
    
    private static Log log = LazyLogFactory.getLog(Exec.class);
    
    static String [] tokenize(String str) {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(str);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        } 
        return matchList.toArray(new String[0]);
    }
    
    static Process exec(String command) throws IOException {
        String [] tokens = tokenize(command);
        return Runtime.getRuntime().exec(tokens);
    }
    
    public static void runAndForget(String commandLine) throws IOException {
        log.info("Executing: " + commandLine);
        exec(commandLine);
    }
    
    public static String runAndWait(String commandLine) throws IOException {
        return runAndWait(commandLine, null);
    }

    public static String runAndWait(String commandLine, String stdin) throws IOException {
        try {
            log.info("Executing: " + commandLine);
            Process process = exec(commandLine);

            OutputStream os = process.getOutputStream();
            if ( stdin != null ) {
                InputStream is = new ByteArrayInputStream( stdin.getBytes(Charsets.UTF_8) );    
                ByteStreams.copy(is, os);
            }
            os.close();

            InputStreamReadingThread stdout = new InputStreamReadingThread( process.getInputStream() );
            InputStreamReadingThread stderr = new InputStreamReadingThread( process.getErrorStream() );

            stdout.start();
            stderr.start();

            stderr.waitFor();
            if ( stderr.getException() != null ) {
                throw stderr.getException();
            }
            String err = new String(stderr.toByteArray(), Charsets.UTF_8).trim();
            if ( !err.isEmpty() ) {
                throw new IOException("Error message on stderr:\n" + err);
            }
            
            stdout.waitFor();
            if ( stdout.getException() != null ) {
                throw stdout.getException();
            }
            String out = new String(stdout.toByteArray(), Charsets.UTF_8).trim();
            
            int returnValue = process.waitFor();
            
            if ( returnValue != 0 ) {
                throw new IOException("Non-zero return value: " + returnValue);
            }

            return out;
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
    }
    
}
