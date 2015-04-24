package pl.rzarajczyk.breaktime.utils.localization.swing;

import java.lang.reflect.Field;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author rafalz
 */
public class LocalizationUtils {
    
    
    public static <E> E get(Field f, Object self, boolean logErrors) {
        try {
            boolean accessible = f.isAccessible();
            f.setAccessible(true);
            E o = (E) f.get(self);
            f.setAccessible(accessible);
            return o;
        } catch (IllegalAccessException e) {
            if (logErrors)
                LazyLogFactory.getLog(LocalizationUtils.class).warn("Could not access field " + f, e);
        } catch (SecurityException e) {
            if (logErrors) 
                LazyLogFactory.getLog(LocalizationUtils.class).warn("Security exception cought while accessing the field " + f, e);
        }
        return null;
    }
    
    public static String buildKey(Class<?> clazz, String postfix) {
        return clazz.getCanonicalName() + "#" + postfix;
    }
}
