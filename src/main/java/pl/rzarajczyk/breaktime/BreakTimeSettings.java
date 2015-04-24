package pl.rzarajczyk.breaktime;



import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.utils.Settings;
import pl.rzarajczyk.breaktime.utils.SettingsEvent;
import pl.rzarajczyk.breaktime.utils.Utils;
import pl.rzarajczyk.utils.application.Application;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author rzarajczyk
 */
public class BreakTimeSettings extends Settings  {
    
    public static final String LOCALIZATION_EXT = ".properties";
    public static int TRAY_INITIALIZATION_COUNT = 3;
    public static int TRAY_INITIALIZATION_INTERVAL = 1000;
    public static int DOUBLE_CLICK_TIME = 200;
    public static String LOG_FILE_NAME = "application-log.txt";
    public static String VERSION_CHECK_URL = "http://break-time.googlecode.com/files/version.txt";
    
    private int microBreakInterval;
    private int microBreakDuration;
    boolean showControlls;
    boolean showCursor;
    private Locale locale;
    private MonitorPowerUsageControllState monitorPowerUsageControll;
    private boolean showNotificationEnabled;
    private int showNotificationOffset;
    private boolean checkForUpdates;
    
    @Autowired
    private Application application;
    
    private Log log = LazyLogFactory.getLog(getClass());
    
    private File getPropertiesFile() throws IOException {
        File dir = application.getConfiguration().getStorageDir();
        return new File(dir, "settings.xml");
    }
    
    @PostConstruct
    public void open() throws IOException {
        initialize(getPropertiesFile(), false);
        this.addEvent( new SettingsEvent() {

            @Override
            public void onAfterLoad() {
                microBreakInterval = get("micro-break-interval", 15 * 60 * Utils.SECOND);
                microBreakDuration = get("micro-break-duration", 10 * Utils.SECOND);
                showControlls = get("show-controlls", true);
                showCursor = get("show-cursor", true);
                monitorPowerUsageControll = get("monitor-power-usage-controll", MonitorPowerUsageControllState.AUTO);
                locale = get("locale", Locale.class, null);
                showNotificationEnabled = get("show-notification-enabled", true);
                showNotificationOffset = get("show-notification-offset", 15000);
                checkForUpdates = get("check-for-updates", true);
                if ( microBreakInterval <= 1 ) {
                    log.warn("Invalid micro break interval (" + microBreakInterval + "); resetting to 30");
                    microBreakInterval = 30;
                }
                if ( microBreakDuration <= 1 ) {
                    log.warn("Invalid micro break duration (" + microBreakDuration + "); resetting to 10");
                    microBreakDuration = 10;
                }
            }

            @Override
            public void onBeforeSave() {
                beginBatchUpdate();
                set("micro-break-interval", microBreakInterval);
                set("micro-break-duration", microBreakDuration);
                set("show-controlls", showControlls);
                set("show-cursor", showCursor);
                set("monitor-power-usage-controll", monitorPowerUsageControll);
                set("locale", locale);
                set("show-notification-enabled", showNotificationEnabled);
                set("show-notification-offset", showNotificationOffset);
                set("check-for-updated", checkForUpdates);
                finishBatchUpdate();
            }

            @Override
            public void onUpdate() {
                // nothing
            }
        } );
        load();
    }
    
    @PreDestroy
    public void close() throws IOException {
        save();
    }
    
    // =========================================================================

    public boolean isCheckForUpdates() {
        return checkForUpdates;
    }

    public void setCheckForUpdates(boolean checkForUpdates) {
        this.checkForUpdates = checkForUpdates;
    }
    
    

    public int getMicroBreakInterval() {
        return microBreakInterval;
    }

    public int getMicroBreakDuration() {
        return microBreakDuration;
    }

    public void setMicroBreakInterval(int value) {
        this.microBreakInterval = value;
    }

    public void setMicroBreakDuration(int value) {
        this.microBreakDuration = value;
    }

    public void setShowControlls(boolean value) {
        this.showControlls = value;
    }

    public boolean isShowControlls() {
        return showControlls;
    }
    
    public void setShowCursor(boolean value) {
        this.showCursor = value;
    }

    public boolean isShowCursor() {
        return showCursor;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isShowNotificationEnabled() {
        return showNotificationEnabled;
    }

    public void setShowNotificationEnabled(boolean showNotificationEnabled) {
        this.showNotificationEnabled = showNotificationEnabled;
    }

    public int getShowNotificationOffset() {
        return showNotificationOffset;
    }

    public void setShowNotificationOffset(int showNotificationOffset) {
        this.showNotificationOffset = showNotificationOffset;
    }

    public MonitorPowerUsageControllState getMonitorPowerUsageControll() {
        return monitorPowerUsageControll;
    }

    public void setMonitorPowerUsageControll(MonitorPowerUsageControllState monitorPowerUsageControll) {
        this.monitorPowerUsageControll = monitorPowerUsageControll;
    }
    
    public String getVersion() {
        return application.getConfiguration().getApplicationVersion();
    }    
}
