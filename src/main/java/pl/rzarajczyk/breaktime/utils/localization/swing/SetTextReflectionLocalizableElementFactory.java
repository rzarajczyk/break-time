package pl.rzarajczyk.breaktime.utils.localization.swing;

import java.lang.reflect.Field;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;

/**
 *
 * @author rafalz
 */
public class SetTextReflectionLocalizableElementFactory implements FieldLocalizableElementFactory {

    @Override
    public boolean isSupported(Field f, Object self) {
        return SetTextReflectionLocalizableElement.convert(f, self) != null;
    }

    @Override
    public LocalizableElement build(Field f, Object self) {
        return SetTextReflectionLocalizableElement.convert(f, self);
    }
}
