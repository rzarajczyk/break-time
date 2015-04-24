package pl.rzarajczyk.breaktime.utils;

/**
 *
 * @author rafalz
 */
public interface SettingsEvent {
    
    void onAfterLoad();
    
    void onBeforeSave();
    
    void onUpdate();
    
}
