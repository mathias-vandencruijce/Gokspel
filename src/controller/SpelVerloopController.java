package controller;

import controller.observer.SpelObserver;
import view.panels.SpelVerloopPane;

/**
 * @author Mathias Van den Cruijce
 */
public class SpelVerloopController implements SpelObserver {
    private String[] text = {"","","","",""};
    private GamblerController gamblerController;
    private SettingsController settingsController;
    private SpelVerloopPane pane;
    private int gameCount = 1;

    public SpelVerloopController(GamblerController gamblerController, SettingsController settingsController, SpelVerloopPane pane) {
        this.gamblerController = gamblerController;
        this.gamblerController.addSpelObserver(this);
        this.settingsController = settingsController;
        this.pane = pane;
        this.pane.setController(this);
        this.pane.getHeader().setText("Dit is het " + gameCount + "e spel van deze sessie.");
    }

    public void resetGame() {
        int i = 4;
        for (String str : text) {
            text[i] = "";
            i--;
        }
        gameCount++;
        settingsController.update();
        gamblerController.reset();
        pane.reset();
    }

    @Override
    public void updateVerloop(String string, int lijn) {
        text[lijn] = string;
        String texts = "";
        for (String str : text) {
            texts += str + "\n";
        }
        pane.getTextVeld().setText(texts);
        pane.refresh();

        if (!text[4].equalsIgnoreCase("")) pane.getStartNieuwSpel().setDisable(false);
    }

    @Override
    public void updateWorpen(String string) {
        text[3] += string + " ";
        String texts = "";
        for (String str : text) {
            texts += str + "\n";
        }
        pane.getTextVeld().setText(texts);
        pane.refresh();
    }

    public int getGameCount() {
        return gameCount;
    }
}
