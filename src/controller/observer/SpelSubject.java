package controller.observer;

/**
 * @author Rafael Hoek
 */
public interface SpelSubject {
    void notifyVerloop(String string, int lijn);
    void notifyWorpen(String string);
    void addSpelObserver(SpelObserver spelObserver);
    void addStatistiekObserver(StatistiekObserver statistiekObserver);
    void updateStatistieken(String strat, boolean gewonnen, double inzet, double uitgekeerd);
}
