package pl.rzarajczyk.breaktime.utils.localization.swing;

import java.lang.reflect.Field;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author rafalz
 */
public class PanelWithTitledBorderLocalizableElement extends AbstractFieldLocalizableElement<JPanel> {

    public PanelWithTitledBorderLocalizableElement(Field f, Object self) {
        super(f, self);
    }
    
    boolean canHandle() {
        JPanel panel = getFieldValue();
        Border border = panel.getBorder();
        return border != null && border instanceof TitledBorder;
    }

    @Override
    public void localize(String text) {
        JPanel panel = getFieldValue();
        TitledBorder border = (TitledBorder) panel.getBorder();
        border.setTitle(text);
    }
    
}
