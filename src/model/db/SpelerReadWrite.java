package model.db;

import model.Speler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Rafael Hoek, Kobe liesenborgs
 */
public interface SpelerReadWrite {

    HashMap<String, Speler> read() throws DBException;

    void write(HashMap<String , Speler> list) throws DBException;
}
