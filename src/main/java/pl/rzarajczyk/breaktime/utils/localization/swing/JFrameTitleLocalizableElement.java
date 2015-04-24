package pl.rzarajczyk.breaktime.utils.localization.swing;

import javax.swing.JFrame;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;

/**
 *
 * @author rafalz
 */
public class JFrameTitleLocalizableElement implements LocalizableElement {
    
    private JFrame frame;

    public JFrameTitleLocalizableElement(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void localize(String text) {
        frame.setTitle(text);
    }

    @Override
    public String getKey() {
        return LocalizationUtils.buildKey(frame.getClass(), "title");
    }
    
    
    
}
