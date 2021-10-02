package view.panels;

import controller.SettingsController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;
import model.gokstrategie.GokStrategieEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs, Rafael Hoek
 */
public class SettingsPane extends GridPane {
    private SettingsController settingsController;

    private RadioButton textFile, excelFile;
    private CheckBox gokStrategie;
    private TextField winstFactor;
    private ToggleGroup fileType = new ToggleGroup();
    private Button save;

    private List<CheckBox> gokStrategieen = new ArrayList<>();
    private List<TextField> winstFactoren = new ArrayList<>();


    Alert fileWriteError = new Alert(Alert.AlertType.ERROR, "Er is een probleem met de properties op slagen.", ButtonType.OK);

    int i;

    public SettingsPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        i = 0;

        displaySettings();

        this.setPadding(new Insets(10, 10, 10, 10));
    }

    private void displaySettings(){
        if(settingsController != null) {
            HashMap<String , String> settings = settingsController.getSettings();

            textFile = new RadioButton("TextFile");
            excelFile = new RadioButton("ExcelFile");

            textFile.setToggleGroup(fileType);
            excelFile.setToggleGroup(fileType);

            for(Toggle toggle : fileType.getToggles()) {
                RadioButton button = (RadioButton) toggle;
                if(button.getText().equals(settingsController.getSettings().get("fileType"))) {
                    toggle.setSelected(true);
                }
            }

            this.add(new Label("File Type:") , 0 , iterate() , 1 , 1);

            this.add(textFile , 0 , iterate() , 1 , 1);
            this.add(excelFile , 0 , iterate() , 1 , 1);

            this.add(new Label("Beschikbare gokstrategieën en winstfactoren:") , 0 , iterate() , 1 , 1);

            List<GokStrategieEnum> gebruikteStrategieen = settingsController.getGebruikteGokStrategieen(settings.get("gokStrategieData"));

            for (GokStrategieEnum strat : settingsController.getGokStrategieEnums()) {
                gokStrategie = new CheckBox(strat.getBeschrijving());
                gokStrategie.setSelected(gebruikteStrategieen.contains(strat));
                winstFactor = new TextField();
                winstFactor.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
                winstFactor.setText(Integer.toString(strat.getWinstfactor()));
                this.add(gokStrategie, 0, iterate(), 1, 1);
                this.add(winstFactor , 2 , this.i , 1 , 1);

                gokStrategieen.add(gokStrategie);
                winstFactoren.add(winstFactor);
            }

            save = new Button("Save Settings");
            this.add(save , 2 , iterate() , 1 , 1);
        }
    }

    private int iterate() {
        this.i++;
        return i;
    }

    public Button getSave() { return save; }

    public ToggleGroup getFileType() { return fileType; }

    public List<CheckBox> getGokStrategieen() { return gokStrategieen; }

    public List<TextField> getWinstFactoren() { return winstFactoren; }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    public void refresh() {
        this.getChildren().clear();
        this.i = 0;
        displaySettings();
    }

    public Alert getFileWriteError() { return fileWriteError; }
}
