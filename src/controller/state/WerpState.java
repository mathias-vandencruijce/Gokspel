package controller.state;

import controller.GamblerController;

import java.util.Random;

/**
 * @author Mathias Van den Cruijce
 */
public class WerpState extends SpelState{
    public WerpState(GamblerController controller) {
        super(controller);
    }
    int eersteInzet;


    @Override
    public void werpDobbelSteen() throws LossException, StateException {
        setState("WerpState");
        if (worpen.size() == 0) eersteInzet = Integer.parseInt(pane.getInzet().getText());
        if (worpen.size() != 4) {
            Random rand = new Random();
            int randomNum = rand.nextInt((6 - 1) + 1) + 1;

            if (worpen.size() == 2) {
                if (checkCorrecteInzet()) {
                    pane.getStartSpel().setDisable(true);
                    setWorpInView(randomNum);
                    if (!currentStrategy.evalueerGok(randomNum)) {
                        worpen.add(randomNum);
                        throw new LossException("Je bent verloren");
                    } else {
                        worpen.add(randomNum);
                    }
                }
            } else {
                setWorpInView(randomNum);
                if (!currentStrategy.evalueerGok(randomNum)) {
                    worpen.add(randomNum);
                    throw new LossException("Je bent verloren");
                } else {
                    worpen.add(randomNum);
                }
            }
        }
    }

    private boolean checkCorrecteInzet() throws StateException {
        String checkUser = pane.getNaam().getText();
        double inzet = -1;
        try {
            inzet = Double.parseDouble(pane.getInzet().getText());
        } catch (NumberFormatException exc) {
            throw new StateException("De opgegeven inzet is niet geldig");
        }

        if (modelFacade.getLijst().containsKey(checkUser)) {
            if (modelFacade.checkInzet(checkUser,inzet)) {
                if (inzet > eersteInzet+10 || inzet < eersteInzet) {
                    throw new StateException("Je mag je inzet alleen verhogen met 10 of meer!");
                } else {
                    pane.getStartSpel().setDisable(false);
                    return true;
                }
            } else {
                pane.getStartSpel().setDisable(true);
                throw new StateException("De opgegeven inzet is niet geldig");
            }
        } else {
            pane.getStartSpel().setDisable(true);
            throw new StateException("De opgegeven inzet is niet geldig");
        }
    }

    private void setWorpInView(int inView) {
        switch (worpen.size()+1) {
            case 1:
                pane.getWorp1().setText(inView+"");
                break;
            case 2:
                pane.getWorp2().setText(inView+"");
                pane.getInzet().setDisable(false);
                break;
            case 3:
                pane.getWorp3().setText(inView+"");
                pane.getInzet().setDisable(true);
                break;
            case 4:
                pane.getWorp4().setText(inView+"");
                break;
        }
    }
}
