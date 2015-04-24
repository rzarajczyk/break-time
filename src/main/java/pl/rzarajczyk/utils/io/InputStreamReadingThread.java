package pl.rzarajczyk.utils.io;

import com.google.common.io.ByteStreams;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author rafalz
 */
public class InputStreamReadingThread extends Thread {

    private InputStream is;
    private ByteArrayOutputStream os;
    private volatile boolean finished;
    private volatile IOException exception;
    
    public InputStreamReadingThread(InputStream is) {
        this.is = is;
        this.finished = false;
    }

    @Override
    public void run() {
        os = new ByteArrayOutputStream();
        
        try {
            try {
                try {
                    ByteStreams.copy(is, os);
                    finished = true;
                } finally {
                    os.close();
                }
            } finally {
                is.close();
            }
        } catch (IOException e) {
            this.exception = e;
        }
    }
    
    public byte [] toByteArray() {
        return os.toByteArray();
    }

    public IOException getException() {
        return exception;
    }

    public boolean isFinished() {
        return finished;
    }
    
    public void waitFor() throws InterruptedException {
        join();
    }
}
