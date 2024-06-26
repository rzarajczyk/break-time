package pl.rzarajczyk.breaktime.gui;

import java.io.IOException;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.BreakManager;
import pl.rzarajczyk.breaktime.utils.localization.Localization;
import pl.rzarajczyk.breaktime.BreakTimeSettings;
import pl.rzarajczyk.breaktime.MonitorPowerUsageControllState;
import pl.rzarajczyk.breaktime.utils.ExtendedJFrame;
import pl.rzarajczyk.breaktime.utils.SwingUtils;
import pl.rzarajczyk.breaktime.utils.Utils;
import pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author rzarajczyk
 */
public class SettingsWindow extends ExtendedJFrame {
    private static final long serialVersionUID = 1L;

    @Autowired
    private BreakTimeSettings settings;

    @Autowired
    private Localization localization;

    @Autowired
    private BreakManager manager;
    
    private boolean wasPaused;
    private Log log = LazyLogFactory.getLog(this.getClass());

    /** Creates new form SettingsWindow */
    public SettingsWindow() {
        initComponents();
        getRootPane().setDefaultButton(okButton);
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void open() {
        @SuppressWarnings("unchecked")
        DefaultComboBoxModel languagesComboModel = (DefaultComboBoxModel) languageCombo.getModel();
        languagesComboModel.removeAllElements();
        for ( Locale locale : localization.getAvailableLocales() ) {
            languagesComboModel.addElement(locale);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) monitorPowerUsageControll.getModel();
        model.removeAllElements();
        for ( MonitorPowerUsageControllState controll : MonitorPowerUsageControllState.values() ) {
            model.addElement(controll);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @LocalizationPoint
    public void setVisible(boolean visible) {
        if ( visible ) {
            microBreakInterval.setText( Utils.toTimeString( settings.getMicroBreakInterval() ) );
            microBreakLength.setText( Utils.toTimeString( settings.getMicroBreakDuration() ) );
            showProgress.setSelected( settings.isShowControlls() );
            showCursor.setSelected( settings.isShowCursor() );
            notificationEnabled.setSelected( settings.isShowNotificationEnabled() );
            notificationOffset.setText(Integer.toString(settings.getShowNotificationOffset() / Utils.SECOND));
            notificationOffset.setEditable( settings.isShowNotificationEnabled() );
//            checkForUpdates.setSelected( settings.isCheckForUpdates() );

            DefaultComboBoxModel m = (DefaultComboBoxModel) monitorPowerUsageControll.getModel();
            m.setSelectedItem( settings.getMonitorPowerUsageControll() );

            DefaultComboBoxModel model = (DefaultComboBoxModel) languageCombo.getModel();
            model.setSelectedItem(localization.getLocale());
            SwingUtils.center(this);
        }

        if ( visible ) {
            wasPaused = manager.isPaused();
            manager.setPaused(true);
        } else {
            manager.setPaused(wasPaused);
        }

        super.setVisible(visible);
    }




    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        microBreakLength = new javax.swing.JTextField();
        microBreakInterval = new javax.swing.JTextField();
        microBreakIntervalLabel = new javax.swing.JLabel();
        microBreakLengthLabel = new javax.swing.JLabel();
        showProgress = new javax.swing.JCheckBox();
        languageCombo = new javax.swing.JComboBox();
        languageLabel = new javax.swing.JLabel();
        showCursor = new javax.swing.JCheckBox();
        monitorPowerUsageControllLabel = new javax.swing.JLabel();
        monitorPowerUsageControll = new javax.swing.JComboBox();
        autostart = new javax.swing.JCheckBox();
        notificationEnabled = new javax.swing.JCheckBox();
        notificationOffset = new javax.swing.JTextField();
        secLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setTitle("Break time! - Settings");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        microBreakLength.setText("jTextField2");

        microBreakInterval.setText("jTextField1");

        microBreakIntervalLabel.setText("MicroBreak interval:");

        microBreakLengthLabel.setText("MicroBreak duration:");

        showProgress.setText("Show progressbar durng the break");

        languageLabel.setText("Language");

        showCursor.setText("Show cursor during the break");

        monitorPowerUsageControllLabel.setText("Poweroff the monitor:");

        monitorPowerUsageControll.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoselect", "Enabled power managment", "Disabled power managment" }));

        autostart.setText("Automatically start when computer starts");
        autostart.setEnabled(false);

        notificationEnabled.setText("Show notification before break:");
        notificationEnabled.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                notificationEnabledItemStateChanged(evt);
            }
        });

        notificationOffset.setText("5");

        secLabel.setText("sec");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(microBreakIntervalLabel)
                            .addComponent(microBreakLengthLabel)
                            .addComponent(languageLabel)
                            .addComponent(monitorPowerUsageControllLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monitorPowerUsageControll, 0, 208, Short.MAX_VALUE)
                            .addComponent(microBreakLength, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(microBreakInterval, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(languageCombo, 0, 208, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(notificationEnabled)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notificationOffset, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secLabel))
                    .addComponent(showProgress)
                    .addComponent(showCursor)
                    .addComponent(autostart))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(languageCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(microBreakIntervalLabel)
                    .addComponent(microBreakInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(microBreakLengthLabel)
                    .addComponent(microBreakLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monitorPowerUsageControllLabel)
                    .addComponent(monitorPowerUsageControll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(notificationEnabled)
                    .addComponent(notificationOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showProgress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showCursor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autostart)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        try {
            settings.setMicroBreakDuration( Utils.parseTimeString( this.microBreakLength.getText() ) );
            settings.setMicroBreakInterval( Utils.parseTimeString( this.microBreakInterval.getText() ) );
            settings.setShowControlls( this.showProgress.isSelected() );
            settings.setShowCursor( this.showCursor.isSelected() );
            settings.setMonitorPowerUsageControll( (MonitorPowerUsageControllState) this.monitorPowerUsageControll.getSelectedItem() );
            settings.setShowNotificationEnabled( this.notificationEnabled.isSelected() );
            settings.setShowNotificationOffset( Integer.parseInt( this.notificationOffset.getText() ) * Utils.SECOND );
            settings.setCheckForUpdates( false/*checkForUpdates.isSelected()*/ );
            localization.setLocale((Locale) this.languageCombo.getSelectedItem());
            settings.triggerUpdate();
            setVisible(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Unparsable number: " + e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            Utils.error(this, log, "Error during saving the settings" , e);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void notificationEnabledItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_notificationEnabledItemStateChanged
        this.notificationOffset.setEditable( this.notificationEnabled.isSelected() );
    }//GEN-LAST:event_notificationEnabledItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autostart;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox languageCombo;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JTextField microBreakInterval;
    private javax.swing.JLabel microBreakIntervalLabel;
    private javax.swing.JTextField microBreakLength;
    private javax.swing.JLabel microBreakLengthLabel;
    private javax.swing.JComboBox monitorPowerUsageControll;
    private javax.swing.JLabel monitorPowerUsageControllLabel;
    private javax.swing.JCheckBox notificationEnabled;
    private javax.swing.JTextField notificationOffset;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel secLabel;
    private javax.swing.JCheckBox showCursor;
    private javax.swing.JCheckBox showProgress;
    // End of variables declaration//GEN-END:variables

}
