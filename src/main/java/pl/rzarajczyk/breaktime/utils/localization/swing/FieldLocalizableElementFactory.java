package pl.rzarajczyk.breaktime.utils.localization.swing;

import java.lang.reflect.Field;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;

/**
 *
 * @author rafalz
 */
public interface FieldLocalizableElementFactory {

    boolean isSupported(Field f, Object self);
    
    LocalizableElement build(Field f, Object self);
    
}
