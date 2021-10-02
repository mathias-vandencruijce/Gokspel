package model;

import model.db.*;
        import model.gokstrategie.GokStrategieEnum;
        import model.observer.OverzichtObserver;


        import java.util.HashMap;
        import java.util.HashSet;

/**
 * @author Kobe Liesenborgs
 */
public class ModelFacade {
    SpelerOverzicht overzicht = new SpelerOverzicht();
    SpelerOverzichtDB overzichtDB = new SpelerOverzichtDB();


    public ModelFacade() { }

    public HashMap<String, Speler> getLijst() {
       return overzicht.getLijst();
    }

    public void saveLijst() throws DBException { overzichtDB.saveLijst(overzicht.getLijst()); }

    public void updateLijst() throws DBException { overzicht.updateLijst(overzichtDB.getLijst()); }

    public void removeFromSaldoFromSpeler(String spelernaam, double bedrag) {
        overzicht.removeFromSaldoFromSpeler(spelernaam, bedrag);
    }

    public void addToSaldoFromPlayer(String spelernaam, double bedrag) {
        overzicht.addToSaldoFromPlayer(spelernaam, bedrag);
    }

    public Speler getSpeler(String user) {
        return overzicht.getSpeler(user);
    }


    public void setBestand(String type) {
        overzichtDB.setBestand(type);
    }

    public void addObserver(OverzichtObserver observer) {
        overzicht.addObserver(observer);
    }

    public boolean checkInzet(String user, double inzet) {
        return overzicht.checkInzet(user, inzet);
    }

    public void setGokStrategieen(GokStrategieEnum[] GokStrategieEnums) {
        overzicht.setGokStrategieen(GokStrategieEnums);
    }

    public GokStrategieEnum[] getGokStrategieen() {
        return overzicht.getGokStrategieen();
    }


}
