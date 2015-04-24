package pl.rzarajczyk.breaktime.utils.localization.swing;

import java.lang.reflect.Field;
import javax.swing.JPanel;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;

/**
 *
 * @author rafalz
 */
public class PanelWithTitledBorderLocalizableElementFactory implements FieldLocalizableElementFactory {

    @Override
    public LocalizableElement build(Field f, Object self) {
        return new PanelWithTitledBorderLocalizableElement(f, self);
    }

    @Override
    public boolean isSupported(Field f, Object self) {
        Object o = LocalizationUtils.get(f, self, false);
        return o instanceof JPanel && new PanelWithTitledBorderLocalizableElement(f, self).canHandle();
    }
    
    
    
    
}
