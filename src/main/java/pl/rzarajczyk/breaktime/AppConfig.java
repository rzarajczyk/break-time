package pl.rzarajczyk.breaktime;

import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.rzarajczyk.breaktime.menuitems.MenuItem;
import pl.rzarajczyk.breaktime.menuitems.PauseMenuItem;
import pl.rzarajczyk.breaktime.menuitems.ShowTimerMenuItem;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "pl.rzarajczyk")
public class AppConfig {

    @Bean
    public pl.rzarajczyk.utils.application.Configuration configuration() throws Exception {
        return pl.rzarajczyk.utils.application.Configuration.instance();
    }

    @Bean(initMethod = "start")
    public pl.rzarajczyk.utils.application.Application application(pl.rzarajczyk.utils.application.Configuration configuration) {
        return new pl.rzarajczyk.utils.application.Application(configuration);
    }

    @Bean
    public pl.rzarajczyk.breaktime.Tray tray() {
        return new pl.rzarajczyk.breaktime.Tray();
    }

    @Bean
    @DependsOn("tray")
    public pl.rzarajczyk.breaktime.TrayIconRenderer trayIconRenderer() {
        return new pl.rzarajczyk.breaktime.TrayIconRenderer();
    }

    @Bean
    public pl.rzarajczyk.breaktime.gui.StartupNotification startupNotification() {
        return new pl.rzarajczyk.breaktime.gui.StartupNotification();
    }

    @Bean
    public pl.rzarajczyk.breaktime.gui.BreakNotification breakNotification() {
        return new pl.rzarajczyk.breaktime.gui.BreakNotification();
    }

    @Bean
    public pl.rzarajczyk.breaktime.gui.BreakWindow breakWindow() {
        return new pl.rzarajczyk.breaktime.gui.BreakWindow();
    }

    @Bean
    public pl.rzarajczyk.breaktime.gui.TimerWindow timerWindow() {
        return new pl.rzarajczyk.breaktime.gui.TimerWindow();
    }

    @Bean
    public pl.rzarajczyk.breaktime.gui.SettingsWindow settingsWindow() {
        return new pl.rzarajczyk.breaktime.gui.SettingsWindow();
    }

    @Bean
    public pl.rzarajczyk.breaktime.gui.AboutWindow aboutWindow() {
        return new pl.rzarajczyk.breaktime.gui.AboutWindow();
    }

    @Bean
    public pl.rzarajczyk.breaktime.BreakManager breakManager() {
        return new pl.rzarajczyk.breaktime.BreakManager();
    }

    @Bean
    public pl.rzarajczyk.breaktime.BreakTimeSettings settings() {
        return new pl.rzarajczyk.breaktime.BreakTimeSettings();
    }

    @Bean
    public pl.rzarajczyk.breaktime.utils.localization.Localization localization() {
        return new pl.rzarajczyk.breaktime.utils.localization.Localization();
    }

    @Bean
    public pl.rzarajczyk.breaktime.utils.localization.LocalizationAspect localizationAspect() {
        return new pl.rzarajczyk.breaktime.utils.localization.LocalizationAspect();
    }

    @Bean
    public pl.rzarajczyk.breaktime.Menu menu(
            PauseMenuItem menuActionPause,
            ShowTimerMenuItem menuActionShowTimer,
            pl.rzarajczyk.breaktime.menuitems.SettingsMenuItem menuActionShowSettings,
            pl.rzarajczyk.breaktime.menuitems.AboutMenuItem menuActionAbout,
            pl.rzarajczyk.breaktime.menuitems.ExitMenuItem menuActionExit) {
        pl.rzarajczyk.breaktime.Menu menu = new pl.rzarajczyk.breaktime.Menu();
        menu.setItems(List.of(
                menuActionPause,
                menuActionShowTimer,
                menuActionShowSettings,
                menuActionAbout,
                menuActionExit
        ));
        menu.setSingleClick(menuActionShowTimer);
        menu.setDoubleClick(menuActionPause);
        return menu;
    }

    @Bean
    public pl.rzarajczyk.breaktime.menuitems.PauseMenuItem menuActionPause() {
        return new pl.rzarajczyk.breaktime.menuitems.PauseMenuItem();
    }

    @Bean
    public pl.rzarajczyk.breaktime.menuitems.ShowTimerMenuItem menuActionShowTimer() {
        return new pl.rzarajczyk.breaktime.menuitems.ShowTimerMenuItem();
    }

    @Bean
    public pl.rzarajczyk.breaktime.menuitems.SettingsMenuItem menuActionShowSettings() {
        return new pl.rzarajczyk.breaktime.menuitems.SettingsMenuItem();
    }

    @Bean
    public pl.rzarajczyk.breaktime.menuitems.AboutMenuItem menuActionAbout() {
        return new pl.rzarajczyk.breaktime.menuitems.AboutMenuItem();
    }

    @Bean
    public pl.rzarajczyk.breaktime.menuitems.ExitMenuItem menuActionExit() {
        return new pl.rzarajczyk.breaktime.menuitems.ExitMenuItem();
    }
}