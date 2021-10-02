package controller.observer;

/**
 * @author Rafael Hoek
 */
public interface StatistiekObserver {
    void update(String strategie, boolean gewonnen, double inzet, double uitgekeerd);
}
