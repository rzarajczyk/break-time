package pl.rzarajczyk.breaktime;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import pl.rzarajczyk.breaktime.gui.BreakNotification;
import pl.rzarajczyk.breaktime.gui.BreakWindow;
import pl.rzarajczyk.breaktime.gui.TimerWindow;
import pl.rzarajczyk.breaktime.utils.SettingsEvent;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author rafalz
 */
public class BreakManager  {
    private BreakSettingsEvent breakSettingsEvent;

    @Autowired
    private BreakWindow breakWindow;

    @Autowired
    private TimerWindow timerWindow;

    @Autowired
    private BreakTimeSettings settings;

    @Autowired
    private TrayIconRenderer tray;

    @Autowired
    private BreakNotification notification;
    
    private boolean timerWindowWasVisible;
    private boolean notificationShown;
    private List<ExtendedBreak> breaks;
    private State state;

    private long time;

    private Log log = LazyLogFactory.getLog(getClass());

    public BreakManager() {
        breakSettingsEvent = new BreakSettingsEvent();
        breaks = new CopyOnWriteArrayList<ExtendedBreak>();
        Break b = new Break() {
            @Override
            public int getDuration() {
                return settings.getMicroBreakDuration();
            }

            @Override
            public String getName() {
                return "MicroBreak";
            }

            @Override
            public int getInterval() {
                return settings.getMicroBreakInterval();
            }
        };
        breaks.add(new ExtendedBreak(b));
    }


    @PostConstruct
    public void open() {
        time = 0;
        state = getCurrentState();
        onFinish();
        settings.addEvent(breakSettingsEvent);
    }
    
    public boolean isPaused() {
        return state.isPaused();
    }
    
    public void setPaused(boolean paused) {
        state.setPaused(paused);
        update(state);
    }

    private void onUpdate() {
        log.info("Settings update");
        if ( state.isBreakActive() ) {
            onStart();
        } else {
            onFinish();
        }
    }

    public void onStart() {
        log.info("Break start");
        timerWindowWasVisible = timerWindow.isVisible();
        timerWindow.setVisible(false);
    }

    public void onFinish() {
        log.info("Break finish");
        if (timerWindow.isVisible() != timerWindowWasVisible)
            timerWindow.setVisible(timerWindowWasVisible);
        notificationShown = false;
    }

    @Override
    public String toString() {
        return "BreakManager [" + breaks + "]";
    }
    
    @Scheduled(fixedRate=1000)
    public void tick() {
        log.debug("Tick time=" + time);
        if (!state.isPaused()) {
            time++;
            State state = getCurrentState();
            boolean isBreakStart = this.state.hasDisplayStateChanged(state) && state.isBreakActive();
            boolean isBreakFinish = this.state.hasDisplayStateChanged(state) && !state.isBreakActive();
            update(state);

            if ( ! state.isBreakActive() ) {
                if ( settings.isShowNotificationEnabled() ) {
                    if ( state.getRemainingTimeSec() * 1000 <= settings.getShowNotificationOffset() && !notificationShown ) {
                        try {
                            notification.show();
                        } catch (IOException e) {
                            log.error("Unable to show notification", e);
                        }
                        notificationShown = true;
                    }
                }
            }

            if (isBreakStart) {
                onStart();
            }
            if (isBreakFinish) {
                onFinish();
            }
            this.state = state;
        }
    }

    private void update(State state) {
        tray.update(state);
        breakWindow.update(state);
        timerWindow.update(state);
    }
    
    public State getCurrentState() {
        return getStateAt(time);
    }
    
    public State getStateAt(long time) {
        State result = new State();
        Break b = this.breaks.get(0);
        result.setTotalTimeSec( BreakCalculator.getTotalTimeSec(time, b) );
        result.setBreakActive( BreakCalculator.isBreakActive(time, b) );
        result.setElapsedTimeSec( BreakCalculator.getRemainingTimeSec(time, b) );
        result.setPaused( state == null ? false : state.isPaused() );   
        return result;
    }


    // =========================================================================

    class BreakSettingsEvent implements SettingsEvent {
        @Override
        public void onAfterLoad() {
            // nothing
        }

        @Override
        public void onBeforeSave() {
            // nothing
        }

        @Override
        public void onUpdate() {
            BreakManager.this.onUpdate();
        }
    }
    
    class ExtendedBreak implements Break {
        
        private Break original;
        private long lastRunSec;

        public ExtendedBreak(Break original) {
            this.original = original;
            this.lastRunSec = 0;
        }

        @Override
        public int getDuration() {
            return original.getDuration();
        }

        @Override
        public int getInterval() {
            return original.getInterval();
        }

        @Override
        public String getName() {
            return original.getName();
        }

        public long getLastRunSec() {
            return lastRunSec;
        }

        public void setLastRunSec(long lastRunSec) {
            this.lastRunSec = lastRunSec;
        }
    }

}
