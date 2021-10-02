package view.panels;

import controller.SpelVerloopController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs, Rafael Hoek
 */
public class SpelVerloopPane extends GridPane {
    private SpelVerloopController spelVerloopController;
    private Label header;
    private Button startNieuwSpel;
    private TextArea textVeld = new TextArea();

    public SpelVerloopPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        startView();

        this.setPadding(new Insets(10, 10, 10, 10));
    }

    private void startView() {
        this.header = new Label();
        this.startNieuwSpel = new Button("Start nieuw spel");
        this.add(header,0,0);
        textVeld.setPrefWidth(500);
        textVeld.setPrefHeight(500);
        this.add(textVeld, 0,1,5,5);


        this.add(startNieuwSpel, 0, 7);
        this.startNieuwSpel.setDisable(true);
    }

    public void refresh() {
        this.getChildren().clear();
        startView();
        this.header.setText("Dit is het " + spelVerloopController.getGameCount() + "e spel van deze sessie.");
        this.getStartNieuwSpel().setOnAction(event -> spelVerloopController.resetGame());
    }

    public void reset() {
        this.textVeld.clear();
        refresh();
    }

    public Label getHeader() {
        return header;
    }

    public Button getStartNieuwSpel() {
        return startNieuwSpel;
    }

    public TextArea getTextVeld() {
        return textVeld;
    }

    public void setController(SpelVerloopController spelVerloopController) {
        this.spelVerloopController = spelVerloopController;
    }
}
