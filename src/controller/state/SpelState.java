package controller.state;

import controller.GamblerController;
import model.ModelFacade;
import model.gokstrategie.GokStrategie;
import model.gokstrategie.GokStrategieEnum;
import view.panels.GamblerViewPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mathias Van den Cruijce
 */
public abstract class SpelState {
    protected ModelFacade modelFacade;
    protected GamblerViewPane pane;
    protected GokStrategieEnum[] strategies;
    protected GokStrategie currentStrategy;
    protected List<Integer> worpen = new ArrayList<>();
    protected double currentWinstFactor;
    protected String state;
    protected String strategie;


    public SpelState(GamblerController controller) {
        this.modelFacade = controller.getmodelFacade();
        this.pane = controller.getPane();
        this.strategies = controller.getStrategies();
    }

    public void login() throws StateException {
       throw new StateException("Je kan niet inloggen omdat het spel in de " + state + "bent");
    }

    public void checkInzet() throws StateException {
        throw new StateException("Je kan niet je inzet bewerken omdat je in de " + state +"bent");
    }

    public void startSpel() throws StateException {
        throw new StateException("Je kan het spel niet starten omdat je in de " + state + "bent");
    }

    public void bevestigKeuze() throws StateException {
        throw new StateException("Je kan je keuze niet bevestigen omdat je in de " + state + "bent");
    }

    public void werpDobbelSteen() throws StateException, LossException {
        throw new StateException("Je kan de dobbelsteen niet werpen omdat je in de " + state + "bent");
    }

    public void eindigSpelMetWinst(boolean gewonnen) throws StateException {
        throw new StateException("Je kan deze actie niet uitvoeren omdat je in de " + state + "bent");
    }

    public void setCurrentStrategy(GokStrategie currentStrategy) {
        this.currentStrategy = currentStrategy;
    }

    public GokStrategie getCurrentStrategy() {
        return currentStrategy;
    }

    public void setCurrentWinstFactor(double currentWinstFactor) {
        this.currentWinstFactor = currentWinstFactor;
    }

    public double getCurrentWinstFactor() {
        return currentWinstFactor;
    }

    public List<Integer> getWorpen() {
        return worpen;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStrategie(String strategie) {
        this.strategie = strategie;
    }

    public String getStrategie() {
        return strategie;
    }
}
