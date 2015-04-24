package pl.rzarajczyk.breaktime.utils.localization.swing;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;

/**
 *
 * @author Rafal
 */
public class SetTextReflectionLocalizableElement extends AbstractFieldLocalizableElement<Object> {

    static LocalizableElement convert(Field f, Object self) {
        SetTextReflectionLocalizableElement result = new SetTextReflectionLocalizableElement(f, self);
        if ( result.getSetTextMethod() == null ) {
            return null;
        }
        return result;
    }

    private SetTextReflectionLocalizableElement(Field f, Object self) {
        super(f, self);
        this.f = f;
        this.self = self;
    }

    private Method getSetTextMethod() {
        try {
            Object o = getFieldValue();
            if (o != null) {
                Class<?> clazz = o.getClass();
                Method m = clazz.getMethod("setText", String.class);
                return m;
            }
        } catch (NoSuchMethodException ex) {
            // nothing - just return null
        } catch (SecurityException ex) {
            log.error("Security exception caught", ex);
        } catch (IllegalArgumentException ex) {
            log.error("Illegal argument while getting the setText() method", ex);
        }
        return null;
    }

    @Override
    public void localize(String text) {
        try {
            Method m = getSetTextMethod();
            if ( m != null ) {
                Object o = getFieldValue();
                m.invoke(o, text);
            }
        } catch (IllegalAccessException ex) {
            log.error("Could not access setText(String)", ex);
        } catch (IllegalArgumentException ex) {
            log.error("Could not provide an illegal argument, since the argument type is strictly given", ex);
            assert false : "Could not provide an illegal argument, since the argument type is strictly given";
        } catch (InvocationTargetException ex) {
            log.error("setText(String) throwed an exception", ex);
        }
    }

}
