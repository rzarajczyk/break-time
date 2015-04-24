package pl.rzarajczyk.breaktime;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import pl.rzarajczyk.breaktime.menuitems.MenuItem;
import pl.rzarajczyk.breaktime.utils.localization.Localizable;
import pl.rzarajczyk.breaktime.utils.localization.LocalizableElement;
import pl.rzarajczyk.breaktime.utils.localization.LocalizationPoint;

/**
 *
 * @author rzarajczyk
 */
public class Menu extends JPopupMenu implements Localizable  {
    private static final long serialVersionUID = 1L;

    private List<MenuItem> items;
    private MenuItem singleClick;
    private MenuItem doubleClick;

    public Menu() {
        super();
    }

    @PostConstruct
    public void open() throws IOException {
        for (MenuItem menuItem : items) {
            JMenuItem item = new ExtendedJMenuItem(this, menuItem);
            this.add(item);
        }
        this.setInvoker(this);
    }

    public void invokeSingleClickAction(MouseEvent e) {
        singleClick.execute(e);
    }

    public void invokeDoubleClickAction(MouseEvent e) {
        doubleClick.execute(e);
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void setSingleClick(MenuItem singleClick) {
        this.singleClick = singleClick;
    }

    public void setDoubleClick(MenuItem doubleClick) {
        this.doubleClick = doubleClick;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<LocalizableElement> iterator() {
        List<Iterator<LocalizableElement>> iterators = Lists.newArrayList();
        for (MenuItem item : items)
            iterators.add(item.iterator());
        return Iterators.concat(iterators.toArray(new Iterator[0]));
    }

    @Override
    public void beforeLocalization() {
        for (MenuItem item : items)
            item.beforeLocalization();
    }

    @Override
    public void afterLocalization() {
        for (MenuItem item : items)
            item.afterLocalization();
    }

    @LocalizationPoint
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    // =========================================================================

    class ExtendedJMenuItem extends JMenuItem {
        private static final long serialVersionUID = 1L;
        private final MenuItem item;

        public ExtendedJMenuItem(final Menu menu, MenuItem item) {
            this.item = item;
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    ExtendedJMenuItem.this.item.execute(e);
                }
            });
        }

        @Override
        public String getText() {
            if ( item == null ) {
                return null;
            }
            return item.getName();
        }

        @Override
        public Icon getIcon() {
            if ( item == null ) {
                return null;
            }
            try {
                String iconPath = item.getIcon();
                URL iconUrl = Main.class.getResource(iconPath);
                return new ImageIcon( ImageIO.read(iconUrl) );
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        }
    }



}
