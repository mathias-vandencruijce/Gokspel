package model.db;

import model.Speler;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Rafael Hoek
 */
public class TextFile extends TekstLoadSaveTemplate implements SpelerReadWrite {

    public TextFile(){ }

    @Override
    public HashMap<String, Speler> read() throws DBException { return load(); }

    @Override
    public void write(HashMap<String, Speler> list) throws DBException { save(list); }
}
