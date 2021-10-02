package controller.state;

import controller.GamblerController;

/**
 * @author Mathias Van den Cruijce
 */
public class LoginState extends SpelState {
    public LoginState(GamblerController controller) {
        super(controller);
    }

    @Override
    public void login() throws StateException {
        setState("LoginState");
        String checkUser = pane.getNaam().getText();
        if(modelFacade.getLijst().containsKey(checkUser)){
            double saldo = modelFacade.getLijst().get(checkUser).getSaldo();
            pane.getSaldo().setText("Je huidige saldo is €" + saldo);
            pane.getInzet().setDisable(false);
        } else{
            pane.getInzet().setText("");
            pane.getSaldo().setText("");
            pane.getInzet().setDisable(true);
            throw new StateException("Geef een geldige naam.");
        }
    }

    @Override
    public void checkInzet() throws StateException {
        String checkUser = pane.getNaam().getText();
        double inzet = -1;
        try {
            inzet = Double.parseDouble(pane.getInzet().getText());
        } catch (NumberFormatException exc) {
            throw new StateException("De opgegeven inzet is niet geldig");
        }

        if (modelFacade.getLijst().containsKey(checkUser)) {
            if (modelFacade.checkInzet(checkUser,inzet)) {
                pane.getStartSpel().setDisable(false);
            } else {
                pane.getStartSpel().setDisable(true);
                throw new StateException("Inzet mag niet hoger zijn als je saldo");
            }
        } else {
            pane.getStartSpel().setDisable(true);
            throw new StateException("Je kon niet terug gevonden worden");
        }
    }

    @Override
    public void startSpel() throws StateException {
        String checkUser = pane.getNaam().getText();
        double inzet = -1;
        try {
            inzet = Double.parseDouble(pane.getInzet().getText());
        } catch (NumberFormatException exc) {
            throw new StateException("De opgegeven inzet is niet geldig");
        }

        if (modelFacade.checkInzet(checkUser,inzet)) {
            prepareSecondStage();
        } else if (!modelFacade.getLijst().containsKey(checkUser)) {
            throw new StateException("De opgegeven spelersnaam werd niet teruggevonden");
        } else {
            throw new StateException("De opgegeven inzet is niet geldig");
        }
    }

    private void prepareSecondStage() {
        pane.getNaam().setDisable(true);
        pane.getInzet().setDisable(true);
        pane.getStartSpel().setDisable(true);
        pane.changeGokstrategieenState(false);
        pane.getBevestigKeuze().setDisable(false);
    }
}
