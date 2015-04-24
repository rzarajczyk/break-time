package pl.rzarajczyk.breaktime.utils.localization;

import java.util.Iterator;

/**
 *
 * @author Rafal
 */
public interface Localizable extends Iterable<LocalizableElement> {

    @Override
    Iterator<LocalizableElement> iterator();

    void beforeLocalization();

    void afterLocalization();

}
