package pl.rzarajczyk.utils.system.autorun;

/**
 *
 * @author Rafal
 */
public abstract class AutorunFactory {
    
    public static boolean isSupported() {
        return false;
    }
    
    public static Autorun getInstance() {
        return null;
    }
    
}
