package view.panels;


import controller.GamblerOverviewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Speler;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs, Rafael Hoek
 */
public class GamblerOverviewPane extends GridPane{
	private TableView<Speler> table;
	private ObservableList<Speler> spelers;
	private GamblerOverviewController gamblerOverviewController;

	public GamblerOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);
		this.add(new Label("Spelers:"), 0, 0, 1, 1);


		this.setPadding(new Insets(10, 10, 10, 10));
		table = new TableView<Speler>();
		refresh();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Speler, String> colAchternaam = new TableColumn<Speler, String>("Achternaam");
		colAchternaam.setCellValueFactory(new PropertyValueFactory<Speler, String>("lastName"));
		colAchternaam.setMinWidth(150);

		TableColumn<Speler, String> colVoornaam = new TableColumn<Speler, String>("Voornaam");
		colVoornaam.setCellValueFactory(new PropertyValueFactory<Speler, String>("firstName"));
		colVoornaam.setMinWidth(150);

		TableColumn<Speler, String> colId = new TableColumn<Speler, String>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<Speler, String>("id"));
		colId.setMinWidth(150);


		TableColumn<Speler, Double> colSaldo = new TableColumn<Speler, Double>("Saldo");
		colSaldo.setCellValueFactory(new PropertyValueFactory<Speler, Double>("saldo"));
		colSaldo.setMinWidth(50);

		table.getColumns().addAll(colId, colAchternaam, colVoornaam, colSaldo);


		this.add(table, 0, 1);
	}

	public void refresh(){
		table.setItems(spelers);
		table.refresh();
	}

	public void setGamblerOverviewController(GamblerOverviewController gamblerOverviewController) {
		this.gamblerOverviewController = gamblerOverviewController;
	}

	public void setSpelers() {
		this.spelers = FXCollections.observableArrayList(gamblerOverviewController.getSpelerList());
		refresh();
	}
}