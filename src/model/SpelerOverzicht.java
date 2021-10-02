package model;

import model.db.*;
import model.gokstrategie.GokStrategieEnum;
import model.observer.OverzichtObserver;
import model.observer.OverzichtSubject;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Mathias Van den Cruijce, Kobe Liesenborgs, Rafael Hoek
 */
public class SpelerOverzicht implements OverzichtSubject {
    HashMap<String, Speler> lijst;

    HashSet<OverzichtObserver> observers = new HashSet<>();

    private GokStrategieEnum[] GokStrategieEnums;

    public SpelerOverzicht() { }

    public HashMap<String, Speler> getLijst() {
        return lijst;
    }


    public void removeFromSaldoFromSpeler(String spelernaam, double bedrag) {
        Speler speler = lijst.get(spelernaam);
        speler.setSaldo(speler.getSaldo()-bedrag);
        lijst.put(spelernaam, speler);
        notifyOverzicht();
    }

    public void addToSaldoFromPlayer(String spelernaam, double bedrag) {
        Speler speler = lijst.get(spelernaam);
        speler.setSaldo(speler.getSaldo()+bedrag);
        lijst.put(spelernaam, speler);
        notifyOverzicht();
    }

    public Speler getSpeler(String user) {
        return lijst.get(user);
    }


    @Override
    public void addObserver(OverzichtObserver observer) {
        if (observer == null) throw new IllegalArgumentException("Geef een valid observer");
        observers.add(observer);
    }

    public void updateLijst(HashMap<String, Speler> lijst){this.lijst = lijst;}

    public boolean checkInzet(String user, double inzet) {
        return lijst.get(user).getSaldo()>=inzet;
    }

    public void setGokStrategieen(GokStrategieEnum[] GokStrategieEnums) {
        this.GokStrategieEnums = GokStrategieEnums;
    }

    public GokStrategieEnum[] getGokStrategieen() {
        return GokStrategieEnums;
    }

    @Override
    public void notifyOverzicht() {
        for (OverzichtObserver observer : observers) {
            observer.updateOverzicht();
        }
    }
}
