package model;

/**
 * @author Mathias Van den Cruijce
 */
public class Speler {
    private String lastName, firstName, id;
    private double saldo;

    public Speler(String lastName, String firstName, String id, double saldo) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
