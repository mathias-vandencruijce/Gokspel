package model.db;

import model.Speler;

import java.util.HashMap;

/**
 * @author Rafael Hoek, Kobe liesenborgs
 */
public class SpelerOverzichtDB {

    SpelerReadWrite bestand;
    public SpelerReadWrite getBestand() { return this.bestand; }

    public void setBestand(String type) {
        FileReadWriteFactory factory = FileReadWriteFactory.getInstance();
        SpelerReadWrite bestand;

        try{
            bestand = factory.getFileHandler(type);
        } catch (Exception exc) {
            throw new DBException(exc.getMessage());
        }
        this.bestand = bestand;
    }

    public void saveLijst(HashMap<String, Speler> lijst) throws DBException { getBestand().write(lijst); }

    public HashMap<String, Speler> getLijst() throws DBException { return getBestand().read(); }
}
