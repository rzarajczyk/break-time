package pl.rzarajczyk.breaktime.gui;

import java.awt.*;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.BreakTimeSettings;
import pl.rzarajczyk.breaktime.MonitorPowerUsageControllState;
import pl.rzarajczyk.breaktime.State;
import pl.rzarajczyk.breaktime.Updateable;
import pl.rzarajczyk.breaktime.utils.ExtendedJFrame;
import pl.rzarajczyk.breaktime.utils.Utils;
import pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint;
import pl.rzarajczyk.utils.log.LazyLogFactory;
import pl.rzarajczyk.utils.swing.AggresiveFocusProtector;
import pl.rzarajczyk.utils.system.monitor.MonitorControllerFactory;

/**
 * Spring bean directly related to the break window
 * <p>
 * Responsibilities:
 * <ul>
 *  <li>Managing the layout and text of the break window</li>
 *  <li>Rendering the break window depending on the current application state and application's settings</li>
 *  <li>Providing interface for setting the current state</li>
 *  <li>Ensuring the window is in full screen exclusive mode</li>
 * </ul>
 */
public class BreakWindow extends ExtendedJFrame implements Updateable {
    @Autowired
    private BreakTimeSettings settings;

    private Log log = LazyLogFactory.getLog(getClass());

    private AggresiveFocusProtector protector;
    
    private State state;

    public BreakWindow() {
        initComponents();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if ( ! gd.isFullScreenSupported() ) {
            log.warn("Exclusive full screen is not supported by this system");
        }
        protector = new AggresiveFocusProtector(this);
    }

    @PostConstruct
    public void open() {
        this.getContentPane().setBackground(Color.BLACK);
    }

    @Override
    public void update(State state) {
        if ( state.hasDisplayStateChanged(this.state) ) {
            if ( state.isBreakActive() ) {
                onBreakStart(state.getTotalTimeSec());
            } else {
                onBreakStop();
            }
        }
        if ( state.isBreakActive() ) {
            onBreakProgress(state.getTotalTimeSec(), state.getRemainingTimeSec());
        }
        this.state = state.clone();
    }

    @LocalizationPoint
    private void onBreakStart(int length) {
        if ( !settings.isShowCursor() ) {
            hideMouseCursor();
        } else {
            setCursor(null);
        }
        this.progress.setMaximum(length);
        this.progressLabel.setText("00:00");
        this.progress.setValue(0);
        this.setControllsVisible( settings.isShowControlls() );
        this.setFullScreen(true);
        if ( shouldManageMonitorPowerUsage() ) {
            try {
                MonitorControllerFactory.getMonitorController().turnOff();
            } catch (IOException e) {
                Utils.error(this, log, "Cannot turn off the monitor", e);
            }
        }
        protector.setProtecting(true);
        log.debug("BreakWindow started");
    }

    private void onBreakProgress(int length, int timeToFinish) {
        this.progress.setValue(length - timeToFinish);
        this.progressLabel.setText( Utils.toTimeString(timeToFinish) );
    }

    private void onBreakStop() {
        protector.setProtecting(false);
        if ( shouldManageMonitorPowerUsage() ) {
            try {
                MonitorControllerFactory.getMonitorController().turnOn();
            } catch (IOException e) {
                Utils.error(this, log, "Cannot turn on the monitor", e);
            }
        }
        this.setVisible(false);
        this.setFullScreen(false);
        log.debug("BreakWindow hidden");
    }

    private void setControllsVisible(boolean show) {
        this.controllsPanel.setVisible(show);
    }

    private void hideMouseCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.createImage("blank.gif");
        Point hotSpot = new Point(0,0);
        Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Blank");
        setCursor(cursor);
    }

    private boolean shouldManageMonitorPowerUsage() {
        MonitorPowerUsageControllState state = settings.getMonitorPowerUsageControll();
        switch ( state ) {
            case DISABLED:
                return false;
            case ENABLED:
                return true;
            case AUTO:
                return !settings.isShowControlls() && !settings.isShowCursor();
            default:
                log.warn("Unknown enum value: " + state);
                return false;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controllsPanel = new javax.swing.JPanel();
        progress = new javax.swing.JProgressBar();
        progressLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Break time!");
        setLocationByPlatform(true);

        controllsPanel.setBackground(new java.awt.Color(0, 0, 0));

        progressLabel.setForeground(new java.awt.Color(255, 255, 255));
        progressLabel.setText("00:00");

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleLabel.setText("Break time!");

        javax.swing.GroupLayout controllsPanelLayout = new javax.swing.GroupLayout(controllsPanel);
        controllsPanel.setLayout(controllsPanelLayout);
        controllsPanelLayout.setHorizontalGroup(
            controllsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controllsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controllsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controllsPanelLayout.createSequentialGroup()
                        .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressLabel))
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
                .addContainerGap())
        );
        controllsPanelLayout.setVerticalGroup(
            controllsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controllsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controllsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controllsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addComponent(controllsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controllsPanel;
    private javax.swing.JProgressBar progress;
    private javax.swing.JLabel progressLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
