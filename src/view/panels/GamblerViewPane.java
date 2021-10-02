package view.panels;

import controller.GamblerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.gokstrategie.GokStrategie;
import model.gokstrategie.GokStrategieEnum;

import java.util.ArrayList;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs, Rafael Hoek
 */
public class GamblerViewPane extends GridPane {
    public static ObservableList<String> list = FXCollections.observableArrayList();
    private GamblerController gamblerController;
    private Alert stateError = new Alert(Alert.AlertType.ERROR,"Er is iets mis gegaan", ButtonType.OK);
    private ArrayList<RadioButton> strategies = new ArrayList<>();
    private int iterator;


    private ToggleGroup group = new ToggleGroup();
    private Button startSpel, bevestigKeuze, werpDobbelSteen;
    private TextField naam, inzet;
    private RadioButton optie;
    private Label saldo,worp1, worp2, worp3, worp4, resultaat;

    public GamblerViewPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.iterator = 6;
        stateError.setHeaderText("Error");
        showStart();
        showGokOpties();
        showDobbelWerpenView();


        this.setPadding(new Insets(10, 10, 10, 10));
    }

    private void showStart() {
        naam = new TextField();
        inzet = new TextField();
        saldo = new Label();
        startSpel = new Button("Start gokspel");
        this.add(new Label("Wat is je spelernaam?"), 0, 0, 1, 1);
        this.add(naam,1,0,1,1);
        this.add(saldo, 2,0,1,1);
        this.add(new Label("Wat is je inzet?"), 0,1,1,1);
        this.add(inzet, 1, 1 , 1, 1);
        this.add(startSpel,0,3,1,1);
        Separator hSeparatorTwo = new Separator(Orientation.HORIZONTAL);
        this.add(hSeparatorTwo, 0, 4, 3, 1);
        hSeparatorTwo.setPrefHeight(10);
        startSpel.setDisable(true);
        inzet.setDisable(true);
    }

    public void showGokOpties() {
        this.add(new Label("Kies je gok strategie uit onderstaande lijst"), 0,5,2,1);

        if (gamblerController != null) {
            for (GokStrategieEnum str : gamblerController.getStrategies()) {
                optie = new RadioButton(str.getBeschrijving());
                optie.setToggleGroup(group);
                optie.setDisable(true);
                this.add(optie, 0, iterator, 2, 1);
                this.add(new Label("Mogelijke winst is " + str.getWinstfactor() + "x"), 2, iterator, 1, 1);
                strategies.add(optie);
                iterator++;
            }
        }

        bevestigKeuze = new Button("Bevestig Keuze");
        this.add(bevestigKeuze, 0,iterator,1,1);
        iterator++;
        bevestigKeuze.setDisable(true);
    }

    private void showDobbelWerpenView() {
        Separator hSeparatorTwo = new Separator(Orientation.HORIZONTAL);
        this.add(hSeparatorTwo, 0, iterator, 5, 1);
        iterator++;
        hSeparatorTwo.setPrefHeight(10);
        worp1 = new Label("");
        worp2 = new Label("");
        worp3 = new Label("");
        worp4 = new Label("");

        werpDobbelSteen = new Button("Werp dobbelsteen");
        this.add(werpDobbelSteen, 0,iterator,1,1);
        iterator++;
        this.add(new Label("Worp 1"), 0,iterator,1,1);
        this.add(worp1, 1, iterator, 1, 1);
        iterator++;
        this.add(new Label("Worp 2"), 0 ,iterator,1,1);
        this.add(worp2, 1, iterator, 1, 1);
        iterator++;
        this.add(new Label("Worp 3"), 0 ,iterator,1,1);
        this.add(worp3, 1, iterator, 1, 1);
        iterator++;
        this.add(new Label("Worp 4"), 0 ,iterator,1,1);
        this.add(worp4, 1, iterator, 1, 1);
        iterator++;
        werpDobbelSteen.setDisable(true);
        resultaat = new Label("");

        this.add(resultaat, 0, iterator+1,3,1);
    }

    public void changeGokstrategieenState(boolean to) {
        for (RadioButton radioButton : strategies) {
            radioButton.setDisable(to);
        }
    }

    public void refresh() {
        this.getChildren().clear();
        this.iterator = 6;
        showStart();
        showGokOpties();
        showDobbelWerpenView();
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public Button getStartSpel() {
        return startSpel;
    }

    public Button getBevestigKeuze() {
        return bevestigKeuze;
    }

    public Button getWerpDobbelSteen() {
        return werpDobbelSteen;
    }

    public TextField getNaam() {
        return naam;
    }

    public TextField getInzet() {
        return inzet;
    }

    public void setGamblerController(GamblerController gamblerController) {
        this.gamblerController = gamblerController;
    }

    public Label getSaldo() {
        return saldo;
    }

    public Label getWorp1() {
        return worp1;
    }

    public Label getWorp2() {
        return worp2;
    }

    public Label getWorp3() {
        return worp3;
    }

    public Label getWorp4() {
        return worp4;
    }

    public Label getResultaat() {
        return resultaat;
    }

    public Alert getStateError() {
        return stateError;
    }

    public void setStateError(String text) {
        this.stateError.setContentText(text);
    }
}
