package pl.rzarajczyk.breaktime.gui;

import java.awt.Desktop;
import java.net.URI;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.utils.localization.Localization;
import pl.rzarajczyk.breaktime.BreakTimeSettings;
import pl.rzarajczyk.breaktime.utils.AbstractSettingsEvent;
import pl.rzarajczyk.breaktime.utils.ExtendedJFrame;
import pl.rzarajczyk.breaktime.utils.SwingUtils;
import pl.rzarajczyk.breaktime.utils.Utils;
import pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author rzarajczyk
 */
public class AboutWindow extends ExtendedJFrame  {

    @Autowired
    private Localization localization;

    @Autowired
    private BreakTimeSettings settings;

    private Log log = LazyLogFactory.getLog(this.getClass());

    /** Creates new form AboutDialog */
    public AboutWindow() {
        super();
        initComponents();
    }

    @PostConstruct
    public void open() {
        settings.addEvent( new AbstractSettingsEvent() {
            @Override
            public void onUpdate() {
                update();
            }

        } );
    }

    private void update() {
        versionLabel.setText( settings.getVersion() );
    }

    @LocalizationPoint
    @Override
    public void setVisible(boolean visible) {
        if ( visible ) {
            SwingUtils.center(this);
            update();
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

        okButton = new javax.swing.JButton();
        aboutPanel = new javax.swing.JPanel();
        authorDescLabel = new javax.swing.JLabel();
        authorLabel = new javax.swing.JLabel();
        licenseDescLabel = new javax.swing.JLabel();
        lisenseLabel = new javax.swing.JLabel();
        urlDescLabel = new javax.swing.JLabel();
        urlLabel = new javax.swing.JLabel();
        versionDescLabel = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();

        setTitle("BreakTime - About");

        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        aboutPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("About..."));

        authorDescLabel.setText("Author:");

        authorLabel.setForeground(java.awt.SystemColor.activeCaption);
        authorLabel.setText("Rafal Zarajczyk");
        authorLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        authorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorLabelMouseClicked(evt);
            }
        });

        licenseDescLabel.setText("License:");

        lisenseLabel.setForeground(java.awt.SystemColor.activeCaption);
        lisenseLabel.setText("Apache License 2.0");
        lisenseLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lisenseLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lisenseLabelMouseClicked(evt);
            }
        });

        urlDescLabel.setText("Project page: ");

        urlLabel.setForeground(java.awt.SystemColor.activeCaption);
        urlLabel.setText("https://github.com/rzarajczyk/break-time");
        urlLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        urlLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                urlLabelMouseClicked(evt);
            }
        });

        versionDescLabel.setText("Version:");

        versionLabel.setText("0.9");

        javax.swing.GroupLayout aboutPanelLayout = new javax.swing.GroupLayout(aboutPanel);
        aboutPanel.setLayout(aboutPanelLayout);
        aboutPanelLayout.setHorizontalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(aboutPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(aboutPanelLayout.createSequentialGroup()
                            .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(urlDescLabel)
                                .addComponent(licenseDescLabel)
                                .addComponent(authorDescLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(authorLabel)
                                .addComponent(lisenseLabel)
                                .addComponent(urlLabel)
                                .addComponent(versionLabel)))
                        .addComponent(versionDescLabel))
                    .addContainerGap(139, Short.MAX_VALUE)))
        );
        aboutPanelLayout.setVerticalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 105, Short.MAX_VALUE)
            .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(aboutPanelLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(authorDescLabel)
                        .addComponent(authorLabel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(licenseDescLabel)
                        .addComponent(lisenseLabel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(urlDescLabel)
                        .addComponent(urlLabel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(versionDescLabel)
                        .addComponent(versionLabel))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(aboutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 367, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(aboutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lisenseLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lisenseLabelMouseClicked
        go("http://www.apache.org/licenses/LICENSE-2.0");
    }//GEN-LAST:event_lisenseLabelMouseClicked

    private void authorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorLabelMouseClicked
        go("http://www.rafal-zarajczyk.pl");
    }//GEN-LAST:event_authorLabelMouseClicked

    private void urlLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_urlLabelMouseClicked
        go("https://code.google.com/p/break-time");
    }//GEN-LAST:event_urlLabelMouseClicked

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_okButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JLabel authorDescLabel;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JLabel licenseDescLabel;
    private javax.swing.JLabel lisenseLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel urlDescLabel;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JLabel versionDescLabel;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables

    private void go(String url) {
        try {
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            Utils.error(this, log, "The URL could not be opened", e);
        }
    }

}
