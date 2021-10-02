package controller;

import controller.observer.SpelObserver;
import controller.observer.SpelSubject;
import controller.observer.StatistiekObserver;
import controller.state.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import model.Speler;
import model.ModelFacade;
import model.gokstrategie.GokStrategieEnum;
import view.panels.GamblerViewPane;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs
 */
public class GamblerController implements SpelSubject {
    private List<SpelObserver> spelObservers = new ArrayList<>();
    private List<StatistiekObserver> statistiekObservers = new ArrayList<>();
    private ModelFacade modelFacade;
    private GamblerViewPane pane;
    private GokStrategieEnum[] strategies;
    private LoginState loginState;
    private EndState endState;
    private StrategyState strategyState;
    private WerpState werpState;
    private SpelState state;

    public GamblerController(ModelFacade modelFacade, GamblerViewPane pane)  {
        this.modelFacade = modelFacade;
        this.pane = pane;
        pane.setGamblerController(this);
        strategies = modelFacade.getGokStrategieen();
        pane.refresh();
        setupView();
        setupStates();
    }

    private void setupStates() {
        loginState = new LoginState(this);
        endState = new EndState(this);
        strategyState = new StrategyState(this);
        werpState = new WerpState(this);

        state = loginState;
    }

    private void setupView() {
        pane.getNaam().setOnAction(event -> login());
        pane.getInzet().setOnAction(event -> checkInzet());
        pane.getStartSpel().setOnAction(event -> startSpel());
        pane.getBevestigKeuze().setOnAction(event -> bevestigKeuze());
        pane.getWerpDobbelSteen().setOnAction(event -> werpDobbelSteen());
    }

    private void login() {
        try {
            state.login();
            Speler speler = modelFacade.getSpeler(pane.getNaam().getText());
            notifyVerloop("De huidige speler is " + speler.getFirstName() + " " + speler.getLastName() + " - Spelernaam: " + speler.getId(), 0);
        } catch (Exception exc) {
            pane.getStateError().setHeaderText("Error bij inloggen");
            pane.setStateError(exc.getMessage());
            pane.getStateError().show();
        }
    }

    private void checkInzet() {
        try {
            state.checkInzet();
            notifyVerloop("Inzet = " + pane.getInzet().getText(), 1);
        } catch (Exception exc) {
            pane.getStateError().setHeaderText("Error bij checken van inzet");
            pane.setStateError(exc.getMessage());
            pane.getStateError().show();
        }
    }

    private void startSpel() {
        try {
            state.startSpel();
            Speler speler = modelFacade.getSpeler(pane.getNaam().getText());
            notifyVerloop("De huidige speler is " + speler.getFirstName() + " " + speler.getLastName() + " - Spelernaam: " + speler.getId(), 0);
            state = strategyState;
        } catch (Exception exc) {
            pane.getStateError().setHeaderText("Error bij starten");
            pane.setStateError(exc.getMessage());
            pane.getStateError().show();
        }
    }

    private void bevestigKeuze() {
        try {
            state.bevestigKeuze();
            state = werpState;
            RadioButton button = (RadioButton) pane.getGroup().getSelectedToggle();
            notifyVerloop("Gekozen strategie is: " + button.getText(), 2);
        } catch (Exception exc) {
            pane.getStateError().setHeaderText("Error bij strategie");
            pane.setStateError(exc.getMessage());
            pane.getStateError().show();
        }
    }

    private void werpDobbelSteen() {
        pane.getInzet().setOnAction(event -> stopEvent());
        try {
            if (state.getCurrentStrategy() == null) {
                state.setCurrentStrategy(strategyState.getCurrentStrategy());
                state.setCurrentWinstFactor(strategyState.getCurrentWinstFactor());
            }

            if (state.getWorpen().size() == 0) notifyVerloop("De worpen van " + pane.getNaam().getText() + ": " ,3);
            if (state.getWorpen().size() == 2) notifyVerloop("Inzet = " + pane.getInzet().getText(), 1);
            try {
                state.werpDobbelSteen();
            } catch (LossException e) {
                verlies();
            }

            if (state.getWorpen().size() < 4 && state == werpState) {
                notifyWorpen(state.getWorpen().get(state.getWorpen().size()-1) + "");
            } else if (state.getWorpen().size() == 4 && state == werpState) {
                winst();
            }


        } catch (StateException exc) {
            pane.getStateError().setHeaderText("Error bij werpen");
            pane.setStateError(exc.getMessage());
            pane.getStateError().show();
        }
    }

    private void stopEvent() {

    }

    private void winst() {
        notifyWorpen(state.getWorpen().get(state.getWorpen().size()-1) + "");
        state = endState;
        state.setCurrentWinstFactor(werpState.getCurrentWinstFactor());
        endState.eindigSpelMetWinst(true);
        state.setStrategie(strategyState.getStrategie());
        notifyVerloop(pane.getNaam().getText() + " heeft GEWONNEN!", 4);
        updateStatistieken(state.getStrategie(), true, Double.parseDouble(pane.getInzet().getText()), Double.parseDouble(pane.getInzet().getText())* state.getCurrentWinstFactor());

    }

    private void verlies() throws StateException {
        notifyWorpen(state.getWorpen().get(state.getWorpen().size()-1) + "");
        state = endState;
        state.eindigSpelMetWinst(false);
        state.setStrategie(strategyState.getStrategie());
        notifyVerloop(pane.getNaam().getText() + " heeft NIET GEWONNEN!", 4);
        updateStatistieken(state.getStrategie(), false, Double.parseDouble(pane.getInzet().getText()), 0);
    }

    public void reset() {
        try {
            setStrategies(modelFacade.getGokStrategieen());
            pane.refresh();
            setupView();
            setupStates();
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }

    public GokStrategieEnum[] getStrategies() {
        return strategies;
    }

    public void setStrategies(GokStrategieEnum[] strategies) {
        this.strategies = strategies;
    }

    public ModelFacade getmodelFacade() {
        return modelFacade;
    }

    public void setmodelFacade(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
    }

    public GamblerViewPane getPane() {
        return pane;
    }

    public void setPane(GamblerViewPane pane) {
        this.pane = pane;
    }


    public LoginState getLoginState() {
        return loginState;
    }

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public EndState getEndState() {
        return endState;
    }

    public void setEndState(EndState endState) {
        this.endState = endState;
    }

    public StrategyState getStrategyState() {
        return strategyState;
    }

    public void setStrategyState(StrategyState strategyState) {
        this.strategyState = strategyState;
    }

    public WerpState getWerpState() {
        return werpState;
    }

    public void setWerpState(WerpState werpState) {
        this.werpState = werpState;
    }

    public SpelState getState() {
        return state;
    }

    public void setState(SpelState state) {
        this.state = state;
    }

    @Override
    public void notifyVerloop(String string, int lijn) {
        for (SpelObserver spelObserver : spelObservers) {
            spelObserver.updateVerloop(string, lijn);
        }
    }

    @Override
    public void notifyWorpen(String string) {
        for (SpelObserver spelObserver : spelObservers) {
            spelObserver.updateWorpen(string);
        }
    }

    @Override
    public void addSpelObserver(SpelObserver observer) {
        if (observer == null) throw new IllegalArgumentException("Geef een valid observer");
        spelObservers.add(observer);
    }

    @Override
    public void addStatistiekObserver(StatistiekObserver statistiekObserver) {
        if (statistiekObserver == null) throw new IllegalArgumentException("Geef een valid observer");
        statistiekObservers.add(statistiekObserver);
    }

    @Override
    public void updateStatistieken(String strat, boolean gewonnen, double inzet, double uitgekeerd) {
        for (StatistiekObserver statistiekObserver : statistiekObservers) {
            statistiekObserver.update(strat, gewonnen, inzet, uitgekeerd);
        }
    }
}
