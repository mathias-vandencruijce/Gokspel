package view;


import controller.GamblerOverviewController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.*;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs, Rafael Hoek
 */
public class AdminMainPane extends BorderPane {
    private GamblerOverviewPane gamblerOverviewPane = new GamblerOverviewPane();
    private SettingsPane settingsPane = new SettingsPane();
    private SpelVerloopPane spelVerloopPane = new SpelVerloopPane();
    private StatistiekenPane statistiekenPane = new StatistiekenPane();

    public AdminMainPane(){

        TabPane tabPane = new TabPane();
        Tab spelVerloopTab = new Tab("Spelverloop", spelVerloopPane);
        Tab spelerTab = new Tab("Spelers",gamblerOverviewPane);
        Tab instellingTab = new Tab("Instellingen", settingsPane);
        Tab statistiekTab = new Tab("Statistieken", statistiekenPane);

        tabPane.getTabs().add(spelVerloopTab);
        tabPane.getTabs().add(spelerTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);
        this.setCenter(tabPane);
    }

    public GamblerOverviewPane getGamblerOverviewPane() {
        return gamblerOverviewPane;
    }

    public SettingsPane getSettingsPane() {
        return settingsPane;
    }

    public SpelVerloopPane getSpelVerloopPane() {
        return spelVerloopPane;
    }

    public StatistiekenPane getStatistiekenPane() {
        return statistiekenPane;
    }
}
