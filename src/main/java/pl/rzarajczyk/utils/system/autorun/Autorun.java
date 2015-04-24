package pl.rzarajczyk.utils.system.autorun;

/**
 *
 * @author Rafal
 */
public interface Autorun {

    boolean exists(String entry);
    
    void create(String entry);
    
    void delete(String entry);
    
}
