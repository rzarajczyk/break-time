package pl.rzarajczyk.breaktime.utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.logging.Log;
import pl.rzarajczyk.breaktime.utils.localization.Localization;
import pl.rzarajczyk.breaktime.MonitorPowerUsageControllState;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 * TODO:
 *  - more general settings comment
 * 
 * @author rzarajczyk
 */
public class Settings {
    
    public static final String DEFAULT_SETTINGS_XML_COMMENT = "Application Settings";
    
    private File file;
    private Properties settings;
    private List<SettingsEvent> events;
    private boolean updateLock;
    private boolean autoUpdateTrigger;
    private Log log = LazyLogFactory.getLog(this.getClass());

    public void initialize(File file, boolean autoUpdateTrigger) {
        this.settings = new Properties();
        this.file = file;
        this.events = new CopyOnWriteArrayList<SettingsEvent>();
        this.updateLock = false;
        this.autoUpdateTrigger = autoUpdateTrigger;
    }
    
    // =========================================================================

    public final void save() throws IOException {
        for ( SettingsEvent event : events ) {
            event.onBeforeSave();
        }
        OutputStream os = new FileOutputStream(file);
        try {
            settings.storeToXML(os, DEFAULT_SETTINGS_XML_COMMENT);
        } finally {
            os.close();
        }
        log.debug("Settings saved");
    }
    
    public final void load() throws IOException {
        if ( file.exists() ) {
            InputStream is = new FileInputStream(file);
            try {
                settings.loadFromXML(is);
            } finally {
                is.close();
            }
        }
        
        for ( SettingsEvent event : events ) {
            event.onAfterLoad();
        }
        log.debug("Settings loaded");
    }
    
    // =========================================================================    
    
    public void addEvent(SettingsEvent event) {
        this.events.add(event);
    }
    
    public void removeEvent(SettingsEvent event) {
        this.events.remove(event);
    }
    
    // =========================================================================
    
    public void beginBatchUpdate() {
        this.updateLock = true;
    }
    
    public void finishBatchUpdate() {
        finishBatchUpdate(true);
    }
    
    public void finishBatchUpdate(boolean triggerUpdate) {
        this.updateLock = false;
        if ( autoUpdateTrigger && triggerUpdate ) {
            triggerUpdate();
        }
    }
    
    public void triggerUpdate() {
        log.info("settings update");
        for ( SettingsEvent event : events ) {
            event.onUpdate();
        }        
    }
    
    // =========================================================================

    public <T> T get(String key, T defaultValue) {
        if ( defaultValue == null )
            throw new IllegalArgumentException("Cannot use null as an argument for Settings.get(String, null)");
        return get(key, (Class<T>) defaultValue.getClass(), defaultValue);
    }

    public <T> T get(String key, Class<T> clazz, T defaultValue) {
        String defaultValueString = null;
        if ( defaultValue != null )
            defaultValueString = defaultValue.toString();

        if ( String.class.isAssignableFrom(clazz) )
            return (T) settings.getProperty(key, defaultValueString);
        if ( Integer.class.isAssignableFrom(clazz) )
            return (T) new Integer(Integer.parseInt( settings.getProperty(key, defaultValueString) ));
        if ( Boolean.class.isAssignableFrom(clazz) )
            return (T) new Boolean(Boolean.parseBoolean( settings.getProperty(key, defaultValueString) ));
        if ( Long.class.isAssignableFrom(clazz) )
            return (T) new Long(Long.parseLong( settings.getProperty(key, defaultValueString) ));
        if ( Double.class.isAssignableFrom(clazz) )
            return (T) new Double(Double.parseDouble( settings.getProperty(key, defaultValueString) ));
        if ( Float.class.isAssignableFrom(clazz) )
            return (T) new Float(Float.parseFloat( settings.getProperty(key, defaultValueString) ));
        if ( Byte.class.isAssignableFrom(clazz) )
            return (T) new Byte(Byte.parseByte( settings.getProperty(key, defaultValueString) ));
        if ( Locale.class.isAssignableFrom(clazz) )
            return (T) Localization.fromString( settings.getProperty(key, defaultValueString) );
        
        if ( MonitorPowerUsageControllState.class.isAssignableFrom(clazz) )
            return (T) MonitorPowerUsageControllState.valueOf(settings.getProperty(key, defaultValueString));
        
        throw new IllegalArgumentException("Could not convert String to " + defaultValue.getClass() + " for key " + key);
    }

    public <T> void set(String key, T value) {
        if ( value == null ) {
            settings.remove(key);
        } else {
            Class<?> clazz = value.getClass();

            if ( Locale.class.isAssignableFrom(clazz) ) {
                settings.setProperty(key, Localization.toString((Locale) value));
            } else {
                settings.setProperty(key, value.toString());
            }
        }
        
        if ( !updateLock && autoUpdateTrigger ) {
            triggerUpdate();
        }
    }

    @Override
    public String toString() {
        return "Settings [" + settings + "]";
    }
    
    
}
