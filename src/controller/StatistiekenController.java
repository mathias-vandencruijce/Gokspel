package controller;

import controller.observer.StatistiekObserver;
import model.ModelFacade;
import model.SpelerOverzicht;
import model.Statistiek;
import model.gokstrategie.GokStrategieEnum;
import view.panels.StatistiekenPane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mathias Van den Cruijce; Kobe Liesenborgs, Rafael Hoek
 */
public class StatistiekenController implements StatistiekObserver {
    private ModelFacade modelFacade;
    private StatistiekenPane pane;
    private GokStrategieEnum[] strategies;
    private HashMap<String, Statistiek> statistiekHashMap = new HashMap<>();

    public StatistiekenController(GamblerController gamblerController, ModelFacade modelFacade, StatistiekenPane pane) {
        gamblerController.addStatistiekObserver(this);
        this.modelFacade = modelFacade;
        this.pane = pane;
        this.strategies = modelFacade.getGokStrategieen();
        init();
        this.pane.setStatistiekenController(this);
        this.pane.setStatistieken();
    }

    private void init() {
        for (GokStrategieEnum gokStrategieEnum : strategies) {
            statistiekHashMap.put(gokStrategieEnum.toString(), new Statistiek(gokStrategieEnum.toString()));
        }
    }

    public List<Statistiek> getStatistieken() {
        List<Statistiek> list = new ArrayList<>(statistiekHashMap.values());
        list.sort(Comparator.comparing(Statistiek::getStrategie));
        return list;
    }


    @Override
    public void update(String strategie, boolean gewonnen, double inzet, double uitgekeerd) {
        Statistiek statistiek = statistiekHashMap.get(strategie);
        if (statistiek == null) {
            statistiek = new Statistiek(strategie);
            statistiekHashMap.put(strategie, statistiek);
        }

        statistiek.setGekozen(statistiek.getGekozen()+1);
        if (gewonnen) {
            statistiek.setGewonnen(statistiek.getGewonnen() + 1);
        }
        statistiek.setInzet(statistiek.getInzet()+inzet);
        statistiek.setUitgekeerd(statistiek.getUitgekeerd()+uitgekeerd);
        statistiekHashMap.put(strategie, statistiek);
        pane.setStatistieken();
    }
}
