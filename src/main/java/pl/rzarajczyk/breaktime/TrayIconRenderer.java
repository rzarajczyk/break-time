package pl.rzarajczyk.breaktime;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pl.rzarajczyk.utils.log.LazyLogFactory;

/**
 * Spring bean for rendering the tray icon.
 * <p>
 * Responsibilities:
 * <ul>
 *  <li>rendering the tray icon depending on the current state</li>
 *  <li>sending the tray icon to {@link Tray}</li>
 *  <li>providing interface for setting the state</li>
 * </ul>
 */
public class TrayIconRenderer implements Updateable {
    
    private static final int OUTLINE_WIDTH = 1;
    
    private static final int PAUSE_DISTANCE = 2;
    private static final int PAUSE_MARGIN_X = 3;
    private static final int PAUSE_MARGIN_Y = 3;
    
    private static final Color BACKGROUND_COLOR = javax.swing.UIManager.getDefaults().getColor("window");
    private static final Color BREAK_BAR_TOP_COLOR = Color.GREEN;
    private static final Color BREAK_BAR_BOTTOM_COLOR = Color.BLUE;
    private static final Color PAUSE_ICON_COLOR = Color.RED;
        
    private double progress;
    private boolean paused;
    
    private int pixelProgress;
    private int width;
    private int height;    
    private Log log = LazyLogFactory.getLog(getClass());
    
    private State state;
    
    @Autowired
    private Tray tray;
    
    @PostConstruct
    public void open() throws IOException {
        width = (int) Math.round(tray.getTrayIconDimension().getWidth());
        height = (int) Math.round(tray.getTrayIconDimension().getHeight());
        setPaused(false);
        setProgress(0.0);
    }

    @Override
    public void update(State state) {
        if ( ! state.isBreakActive() ) {
            setProgress(state.getProgress());
        }
        if ( state.hasPausedStateChanged(this.state) ) {
            setPaused(state.isPaused());
        }
        this.state = state.clone();
    }

    private void setPaused(boolean paused) {
        this.paused = paused;
        render(true);
    }

    private void setProgress(double progress) {
        this.progress = progress;
        render(false);
    }
    
    private int calculatePixelProgress() {
        double realDoubleProgress = ( 1.0 - this.progress ) * ( height - 2 * OUTLINE_WIDTH);
        int realIntProgress = (int) Math.round(realDoubleProgress);
        return realIntProgress;
    }
    
    public void render(boolean force) {
        int currentPixelProgress = calculatePixelProgress();
        if ( force || currentPixelProgress != pixelProgress ) {
            pixelProgress = currentPixelProgress;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            renderBackground(graphics);
            renderProgressBar(graphics);
            renderImage(graphics);
            if ( paused ) {
                renderPauseIcon(graphics);
            }
            tray.setTrayIconImage(image);
        }
    }
    
    private void renderBackground(Graphics2D graphics) {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, width, height);
        graphics.setStroke( new BasicStroke(OUTLINE_WIDTH) );
        int w = width - OUTLINE_WIDTH;
        int h = height - OUTLINE_WIDTH;
        graphics.draw3DRect(0, 0, w, h, false);
    }
    
    private void renderProgressBar(Graphics2D graphics) {
        graphics.setColor(BREAK_BAR_TOP_COLOR);
        
        int w = width - 2 * OUTLINE_WIDTH;
        int h = height - 2 * OUTLINE_WIDTH;
        int x = OUTLINE_WIDTH;
        int y = OUTLINE_WIDTH + h - pixelProgress;
        
        graphics.setStroke( new BasicStroke() );
        graphics.setPaint(new GradientPaint(0, 0, BREAK_BAR_TOP_COLOR, 0, h, BREAK_BAR_BOTTOM_COLOR));
        graphics.fillRect(x, y, w, pixelProgress);   
    }
    
    private void renderImage(Graphics2D graphics) {
//        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        graphics.drawImage(icon, 0, 0, null);
    }
    
    private void renderPauseIcon(Graphics2D graphics) {
        graphics.setColor(PAUSE_ICON_COLOR);
        int pauseBarWidth = (width - PAUSE_DISTANCE - 2 * PAUSE_MARGIN_X) / 2;
        graphics.fillRect(PAUSE_MARGIN_X, PAUSE_MARGIN_Y, pauseBarWidth, height - 2 * PAUSE_MARGIN_Y);
        graphics.fillRect(pauseBarWidth + PAUSE_MARGIN_X + PAUSE_DISTANCE, PAUSE_MARGIN_Y, pauseBarWidth, height - 2 * PAUSE_MARGIN_Y);
    }
    
}
