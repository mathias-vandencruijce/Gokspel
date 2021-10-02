package controller.state;

/**
 * @author Mathias Van den Cruijce
 */
public class StateException extends Exception {
    public StateException() {
    }

    public StateException(String message) {
        super(message);
    }

    public StateException(String message, Throwable cause) {
        super(message, cause);
    }
}
