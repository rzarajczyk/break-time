package pl.rzarajczyk.breaktime.utils;

/**
 *
 * @author rafalz
 */
public abstract class AbstractSettingsEvent implements SettingsEvent {

    @Override
    public void onAfterLoad() {
        //nothing
    }

    @Override
    public void onBeforeSave() {
        // nothing
    }

    @Override
    public void onUpdate() {
        // nothing
    }
    
}
