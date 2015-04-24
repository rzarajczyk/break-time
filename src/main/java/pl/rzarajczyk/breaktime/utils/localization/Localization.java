package pl.rzarajczyk.breaktime.utils.localization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rzarajczyk.breaktime.BreakTimeSettings;
import pl.rzarajczyk.breaktime.utils.localization.swing.LocalizationUtils;
import pl.rzarajczyk.utils.application.Application;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 *
 * @author rzarajczyk
 */
public class Localization  {

    @Autowired
    private BreakTimeSettings settings;

    @Autowired
    private Application application;

    private File dir;
    private List<Locale> available;

    private Locale locale;
    private Properties texts;

    private Log log = LazyLogFactory.getLog(this.getClass());

    public static String toString(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    public static Locale fromString(String string) {
        if ( string == null )
            return null;
        String language = string;
        String country = string;
        int underscore = string.indexOf('_');
        if ( underscore >= 0 ) {
            country = language.substring(underscore + 1);
            language = language.substring(0, underscore);
        }
        return new Locale(language, country);
    }

    @PostConstruct
    public void open() throws IOException {
        dir = new File(application.getConfiguration().getTempDir(), "languages");
        application.getResourcesManager().unpack("languages", application.getConfiguration().getTempDir());

        this.available = new ArrayList<Locale>();
        for ( File file : dir.listFiles( new PropertiesFilenameFilter() ) ) {
            String name = file.getName();
            name = name.substring(0, name.length() - BreakTimeSettings.LOCALIZATION_EXT.length());
            available.add( fromString(name) );
        }

        texts = new Properties();

        Locale localeToLoad = settings.getLocale();
        setLocale(localeToLoad);
    }

    public void setLocale(Locale locale) throws IOException {
        if ( locale != null ) {
            if ( ! getAvailableLocales().contains(locale) ) {
                throw new IllegalArgumentException("Unsupported locale: " + locale);
            }
            this.locale = locale;
            texts.clear();
            InputStream is = new FileInputStream( new File(dir, toString(locale) + BreakTimeSettings.LOCALIZATION_EXT) );
            try {
                texts.load(is);
            } finally {
                is.close();
            }
            settings.setLocale(locale);
            settings.triggerUpdate();
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public List<Locale> getAvailableLocales() {
        return available;
    }
    
    public String text(Class<?> clazz, String text, String defaultValue) {
        String key = LocalizationUtils.buildKey(clazz, text);
        String result = texts.getProperty(key, null);
        if ( result == null ) {
            log.warn("Could not find translation for [" + key + "] for locale [" + locale + "]");
            result = defaultValue;
        }
        return result;
    }

    public void localize(Localizable localizable) {
        localizable.beforeLocalization();
        for (LocalizableElement element : localizable) {
            String key = element.getKey();
            String value = texts.getProperty(key);
            if ( value == null ) {
                log.warn("Could not find translation for [" + key + "] for locale [" + locale + "]");
            } else {
                element.localize(value);
            }
        }
        localizable.afterLocalization();
    }

    // =========================================================================

    class PropertiesFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(BreakTimeSettings.LOCALIZATION_EXT);
        }
    }

}
