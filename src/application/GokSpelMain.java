package application;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelFacade;
import view.AdminView;
import view.GamblerView;

import java.io.IOException;

public class GokSpelMain extends Application {
	public ModelFacade modelFacade;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.modelFacade = new ModelFacade();
		AdminView adminView = new AdminView();
		GamblerView gamblerView = new GamblerView();

		SettingsController settingscontroller = new SettingsController(modelFacade, adminView.adminPane().getSettingsPane());
		GamblerOverviewController gamblerOverviewController = new GamblerOverviewController(modelFacade, adminView.adminPane().getGamblerOverviewPane());
		GamblerController gamblerController = new GamblerController(modelFacade, gamblerView.getGamblerViewPane());
		SpelVerloopController spelVerloopController = new SpelVerloopController(gamblerController, settingscontroller ,  adminView.adminPane().getSpelVerloopPane());
		StatistiekenController statistiekenController = new StatistiekenController(gamblerController, modelFacade, adminView.adminPane().getStatistiekenPane());
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		modelFacade.saveLijst();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
