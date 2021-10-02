package controller.state;

import controller.GamblerController;
import javafx.scene.control.RadioButton;
import model.gokstrategie.GokStrategieEnum;
import model.gokstrategie.GokStrategieFactory;

/**
 * @author Mathias Van den Cruijce
 */
public class StrategyState extends SpelState{
    public StrategyState(GamblerController controller) {
        super(controller);
    }

    @Override
    public void bevestigKeuze() throws StateException {
        setState("StrategyState");
        if (pane.getGroup().getSelectedToggle() == null) {
            throw new StateException("Je moet een gokstrategie selecteren");
        } else {
            pane.getBevestigKeuze().setDisable(true);
            pane.changeGokstrategieenState(true);
            pane.getWerpDobbelSteen().setDisable(false);
            RadioButton button = (RadioButton) pane.getGroup().getSelectedToggle();

            for (GokStrategieEnum str : strategies) {
                if (str.getBeschrijving().equals(button.getText())) {
                    GokStrategieFactory factory = GokStrategieFactory.getInstance();
                    currentStrategy = factory.getGokStrategie(str.name());
                    strategie = str.toString();
                    currentWinstFactor = str.getWinstfactor();
                }
            }
        }
    }
}
