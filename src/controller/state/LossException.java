package controller.state;

/**
 * @author Mathias Van den Cruijce
 */
public class LossException extends Exception {
    public LossException() {
    }

    public LossException(String message) {
        super(message);
    }

    public LossException(String message, Throwable cause) {
        super(message, cause);
    }
}
