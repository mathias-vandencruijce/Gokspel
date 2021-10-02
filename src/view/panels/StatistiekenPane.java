package view.panels;

import controller.GamblerOverviewController;
import controller.SpelVerloopController;
import controller.StatistiekenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Speler;
import model.Statistiek;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs, Rafael Hoek
 */
public class StatistiekenPane extends GridPane {
    private StatistiekenController statistiekenController;
    private TableView<Statistiek> table;
    private ObservableList<Statistiek> statistieken;

    public StatistiekenPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Statistieken:"), 0, 0, 1, 1);


        this.setPadding(new Insets(10, 10, 10, 10));
        table = new TableView<Statistiek>();
        refresh();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Statistiek, String> colGokStrat = new TableColumn<>("Strategie");
        colGokStrat.setCellValueFactory(new PropertyValueFactory<>("strategie"));
        colGokStrat.setMinWidth(125);

        TableColumn<Statistiek, Integer> colGekozen = new TableColumn<>("# Gekozen");
        colGekozen.setCellValueFactory(new PropertyValueFactory<>("gekozen"));
        colGekozen.setMinWidth(100);

        TableColumn<Statistiek, Integer> colGewonnen = new TableColumn<>("# Gewonnen");
        colGewonnen.setCellValueFactory(new PropertyValueFactory<>("gewonnen"));
        colGewonnen.setMinWidth(100);

        TableColumn<Statistiek, Double> colInzet = new TableColumn<>("Inzet");
        colInzet.setCellValueFactory(new PropertyValueFactory<>("inzet"));
        colInzet.setMinWidth(75);

        TableColumn<Statistiek, Double> colUitgekeerd = new TableColumn<>("Uitgekeerd");
        colUitgekeerd.setCellValueFactory(new PropertyValueFactory<>("uitgekeerd"));
        colUitgekeerd.setMinWidth(100);

        table.getColumns().addAll(colGokStrat, colGekozen, colGewonnen, colInzet, colUitgekeerd);


        this.add(table, 0, 1);
    }

    public void refresh(){
        table.setItems(statistieken);
        table.refresh();
    }

    public StatistiekenController getStatistiekenController() {
        return statistiekenController;
    }

    public void setStatistiekenController(StatistiekenController statistiekenController) {
        this.statistiekenController = statistiekenController;
    }

    public void setStatistieken() {
        this.statistieken = FXCollections.observableArrayList(statistiekenController.getStatistieken());
        refresh();
    }
}
