package controller;

import model.ModelFacade;
import model.Speler;
import model.SpelerOverzicht;
import model.observer.OverzichtObserver;
import view.AdminView;
import view.GamblerView;
import view.panels.GamblerOverviewPane;
import view.panels.GamblerViewPane;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mathias Van den Cruijce
 */
public class GamblerOverviewController implements OverzichtObserver {
    private
    ModelFacade modelFacade;
    private GamblerOverviewPane pane;

    public GamblerOverviewController(ModelFacade modelFacade, GamblerOverviewPane pane)  {
            this.modelFacade = modelFacade;
            this.pane = pane;
            pane.setGamblerOverviewController(this);
            pane.setSpelers();
            this.modelFacade.addObserver(this);
    }

    public List<Speler> getSpelerList() {
        List<Speler> list = new ArrayList<Speler>(modelFacade.getLijst().values());
        list.sort(Comparator.comparing(Speler::getId));

        return list;
    }

    @Override
    public void updateOverzicht() {
        pane.setSpelers();
    }
}
