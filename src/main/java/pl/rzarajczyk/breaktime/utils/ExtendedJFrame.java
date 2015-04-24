package pl.rzarajczyk.breaktime.utils;

import com.google.common.collect.Lists;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import pl.rzarajczyk.breaktime.utils.localization.Localizable;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;
import pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint;
import pl.rzarajczyk.breaktime.utils.localization.swing.FieldLocalizableElementFactory;
import pl.rzarajczyk.breaktime.utils.localization.swing.JFrameTitleLocalizableElement;
import pl.rzarajczyk.breaktime.utils.localization.swing.PanelWithTitledBorderLocalizableElementFactory;
import pl.rzarajczyk.breaktime.utils.localization.swing.SetTextReflectionLocalizableElementFactory;

/**
 *
 * @author rafalz
 */
public class ExtendedJFrame extends JFrame implements Localizable {
    private static final long serialVersionUID = 1L;

    private GraphicsDevice gd;

    private void initializeGraphicsDevice() {
        if ( gd == null ) {
            gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        }
    }

    public GraphicsDevice getGraphicsDevice() {
        initializeGraphicsDevice();
        return gd;
    }

    public void setGraphicsDevice(GraphicsDevice device) {
        gd = device;
    }



    // =========================================================================

    @Override
    public void afterLocalization() {
        pack();
    }

    @Override
    public void beforeLocalization() {
        // nothing
    }

    @Override
    public Iterator<LocalizableElement> iterator() {
        List<FieldLocalizableElementFactory> factories = Arrays.<FieldLocalizableElementFactory>asList(new SetTextReflectionLocalizableElementFactory(), new PanelWithTitledBorderLocalizableElementFactory());
        Class<?> clazz = getClass();
        List<LocalizableElement> result = Lists.newArrayList();
        for (Field field : clazz.getDeclaredFields()) {
            for (FieldLocalizableElementFactory factory : factories) {
                if ( factory.isSupported(field, this) ) {
                    LocalizableElement element = factory.build(field, this);
                    result.add(element);
                }
            }
        }
        result.add( new JFrameTitleLocalizableElement(this) );
        return result.iterator();
    }



    // =========================================================================

    public ExtendedJFrame(String string, GraphicsConfiguration gc) {
        super(string, gc);
    }

    public ExtendedJFrame(String string) throws HeadlessException {
        super(string);
    }

    public ExtendedJFrame(GraphicsConfiguration gc) {
        super(gc);
    }

    public ExtendedJFrame() throws HeadlessException {
    }

    private boolean isFullScreen = false;
    private boolean wasAlwaysOnTop = false;
    private boolean wasUndecorated = false;
    private boolean wasResizable = false;

    public void setFullScreen(boolean enabled) {
        initializeGraphicsDevice();
        boolean visible = this.isVisible();
        if ( isFullScreen != enabled ) {
            this.dispose();
            if ( enabled ) {
                wasAlwaysOnTop = isAlwaysOnTop();
                wasUndecorated = isUndecorated();
                wasResizable = isResizable();
                setUndecorated(true);
                setAlwaysOnTop(true);
                setResizable(false);
                getGraphicsDevice().setFullScreenWindow(this);
            } else {
                getGraphicsDevice().setFullScreenWindow(null);
                setUndecorated(wasUndecorated);
                setAlwaysOnTop(wasAlwaysOnTop);
                setResizable(wasResizable);
            }
            if ( visible ) {
                this.setVisible(true);
            }
            isFullScreen = enabled;
        }
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }
}
