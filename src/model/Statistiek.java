package model;

/**
 * @author Mathias Van den Cruijce
 */
public class Statistiek {
    private String strategie;
    private int gekozen, gewonnen;
    private double inzet, uitgekeerd;

    public Statistiek(String strategie) {
        this.strategie = strategie;
        this.gekozen = 0;
        this.gewonnen = 0;
        this.inzet = 0;
        this.uitgekeerd = 0;
    }

    public String getStrategie() {
        return strategie;
    }

    public void setStrategie(String strategie) {
        this.strategie = strategie;
    }

    public int getGekozen() {
        return gekozen;
    }

    public void setGekozen(int gekozen) {
        this.gekozen = gekozen;
    }

    public int getGewonnen() {
        return gewonnen;
    }

    public void setGewonnen(int gewonnen) {
        this.gewonnen = gewonnen;
    }

    public double getInzet() {
        return inzet;
    }

    public void setInzet(double inzet) {
        this.inzet = inzet;
    }

    public double getUitgekeerd() {
        return uitgekeerd;
    }

    public void setUitgekeerd(double uitgekeerd) {
        this.uitgekeerd = uitgekeerd;
    }
}
