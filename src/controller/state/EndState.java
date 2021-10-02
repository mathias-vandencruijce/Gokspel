package controller.state;

import controller.GamblerController;

/**
 * @author Mathias Van den Cruijce
 */
public class EndState extends SpelState {

    public EndState(GamblerController controller) {
        super(controller);
    }

    @Override
    public void eindigSpelMetWinst(boolean gewonnen) {
        setState("Endstate");
        pane.getWerpDobbelSteen().setDisable(true);
        String naam = pane.getNaam().getText();
        if (gewonnen) {
            modelFacade.addToSaldoFromPlayer(naam, Double.parseDouble(pane.getInzet().getText()) * currentWinstFactor);
            pane.getResultaat().setText("Gefeliciteerd! Je hebt gewonnen! Je opbrengt is "+ Double.parseDouble(pane.getInzet().getText()) * currentWinstFactor +" en je huidige goksaldo is " + modelFacade.getSpeler(naam).getSaldo());
        } else {
            if (!pane.getInzet().isDisabled()) pane.getInzet().setDisable(true);
            modelFacade.removeFromSaldoFromSpeler(naam, Double.parseDouble(pane.getInzet().getText()));
            pane.getResultaat().setText("Pech! Je hebt deze keer niet gewonnen. Je verlies is "+ pane.getInzet().getText() +" en je huidige goksaldo is " + modelFacade.getSpeler(naam).getSaldo());
        }
    }
}
