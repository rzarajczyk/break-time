package pl.rzarajczyk.breaktime.menuitems;

import com.google.common.collect.Iterators;
import java.util.Iterator;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;
import pl.rzarajczyk.breaktime.utils.localization.swing.LocalizationUtils;

/**
 *
 * @author rafalz
 */
public abstract class SimpleMenuItem implements MenuItem, LocalizableElement {
 
    protected String name;

    public SimpleMenuItem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void localize(String text) {
        this.name = text;
    }

    @Override
    public String getKey() {
        return LocalizationUtils.buildKey(getClass(), "name");
    }

    @Override
    public void beforeLocalization() {
        // nothing
    }

    @Override
    public void afterLocalization() {
        // nothing
    }

    @Override
    public Iterator<LocalizableElement> iterator() {
        return Iterators.forArray((LocalizableElement) this);
    }    
}
