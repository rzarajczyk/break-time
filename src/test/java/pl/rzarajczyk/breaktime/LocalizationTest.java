package pl.rzarajczyk.breaktime;

import pl.rzarajczyk.breaktime.utils.localization.Localization;
import java.util.Locale;
import junit.framework.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author rafalz
 */
public class LocalizationTest {

    @Test
    public void toStringShouldProduceCorrectFormat() {
        Locale locale = new Locale("polski", "PL");
        Assert.assertEquals("polski_PL", Localization.toString(locale));
    }
    
    @Test
    public void fromStringShouldCorrectlyParseString() {
        Locale locale = Localization.fromString("polski_PL");
        Assert.assertEquals("polski", locale.getLanguage());
        Assert.assertEquals("PL", locale.getCountry());
    }
    
}
