package model.observer;

/**
 * @author Mathias Van den Cruijce
 */
public interface OverzichtSubject {
    void notifyOverzicht();
    void addObserver(OverzichtObserver observer);
}
