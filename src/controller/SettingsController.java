package controller;

import javafx.scene.control.*;
import model.ModelFacade;
import model.SpelerOverzicht;
import model.gokstrategie.GokStrategieEnum;
import model.gokstrategie.GokStrategieFactory;
import view.panels.SettingsPane;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static java.lang.Integer.parseInt;

/**
 * @author Rafael Hoek
 */
public class SettingsController {
    private ModelFacade modelFacade;
    private SettingsPane settingsPane;
    private GokStrategieEnum[] gokStrategieEnums = GokStrategieEnum.values(); //var moet daarna aangepast worden aan properties file
    private Properties  properties;
    HashMap<String , String> settings;

    public SettingsController(ModelFacade modelFacade, SettingsPane settingsPane) throws IOException {
        this.modelFacade = modelFacade;
        this.settingsPane = settingsPane;
        settingsPane.setSettingsController(this);

        setup();
        settingsPane.refresh();
        listen();
    }

    public void listen() {
        settingsPane.getSave().setOnAction(event -> save());
    }

    public void setup() throws IOException {
        //load settings-bestand in een property-object
        properties = new Properties();
        InputStream input = new FileInputStream("src\\bestanden\\settings");
        properties.load(input);
        input.close();

        //haal alle settings op van property-object en zet ze in de settings-map
        settings = new HashMap<>();
        settings.put("fileType" , properties.getProperty("FileType"));
        settings.put("gokStrategieData" , properties.getProperty("GokStrategieen"));

        //verwerking van alle settings
        loadFileType(getSettings().get("fileType"));
        loadGokStrategieen(getSettings().get("gokStrategieData"));
    }

    public void update(){
        //wordt opgeroepen bij het opstarten van een nieuw spel
        loadGokStrategieen(getSettings().get("gokStrategieData"));
    }

    private void save() {
        //wordt opgeroepen wanneer er in de settings-tab op de save-knop wordt gedrukt

        //inhalen van de ingegeven informatie op de settings-pane
        RadioButton fileTypeButton = (RadioButton) settingsPane.getFileType().getSelectedToggle();
        List<CheckBox> gokStrategieCheckboxes = settingsPane.getGokStrategieen();
        List<TextField> winstFactorTextFields = settingsPane.getWinstFactoren();

        //opslaan van fileType-data
        String fileTypeSetting = fileTypeButton.getText();
        saveFileType(fileTypeSetting);

        //opslaan van gokStrategie-data
        saveGokStrategieen(gokStrategieCheckboxes , winstFactorTextFields);

        //sla geupdate properties op in het settings-bestand
        try {
            FileOutputStream output = new FileOutputStream("src\\bestanden\\settings");
            properties.store(output, null);
            output.close();
        } catch(Exception exc) {
            settingsPane.getFileWriteError().show();
        }
    }

    private void saveFileType(String fileTypeSetting) { properties.setProperty("FileType" , fileTypeSetting); }

    private void saveGokStrategieen(List<CheckBox> gokStrategieCheckboxes, List<TextField> winstFactorTextFields) {
        String result = "";

        for(int i = 0 ; i < gokStrategieCheckboxes.size() ; i++){
            GokStrategieEnum gokStrategieEnum = getGokStrategieEnums()[i];
            CheckBox checkBox = gokStrategieCheckboxes.get(i);
            TextField textField = winstFactorTextFields.get(i);

            result= result + gokStrategieEnum.getGokStrategie().toString() + ";" + checkBox.isSelected() + ";" + textField.getText() + ",";
        }
        result = result.substring(0 , result.length() - 1);

        properties.setProperty("GokStrategieen" , result);
        getSettings().put("gokStrategieData" , properties.getProperty("GokStrategieen"));
    }

    private ModelFacade getModelFacade() { return this.modelFacade; }

    public GokStrategieEnum[] getGokStrategieEnums() { return gokStrategieEnums; }

    private void loadFileType(String fileType) {
        getModelFacade().setBestand(fileType);
        getModelFacade().updateLijst();
    }

    private void loadGokStrategieen(String gokStrategieData) {
        List<GokStrategieEnum> gebruikteStrategieen = getGebruikteGokStrategieen(gokStrategieData);

        GokStrategieEnum[] strategieenArray = new GokStrategieEnum[gebruikteStrategieen.size()];

        for(int i = 0 ; i < strategieenArray.length ; i++) {
            strategieenArray[i] = gebruikteStrategieen.get(i);
        }

        getModelFacade().setGokStrategieen(strategieenArray);
    }

    public List<GokStrategieEnum> getGebruikteGokStrategieen(String gokStrategieData) {
        String strategieSplit = ",";
        String elementSplit = ";";
        String[] gokStrategieen = gokStrategieData.split(strategieSplit);
        List<GokStrategieEnum> gebruikteStrategieen = new ArrayList<>();


        for(String stratString : gokStrategieen) {
            String[] stratArray = stratString.split(elementSplit);
            GokStrategieEnum.valueOf(stratArray[0]).setWinstfactor(parseInt(stratArray[2]));
            if(stratArray[1].equals("true")) {
                gebruikteStrategieen.add(GokStrategieEnum.valueOf(stratArray[0]));
            }
        }

        return gebruikteStrategieen;
    }

    public HashMap<String, String> getSettings() { return settings; }
}
