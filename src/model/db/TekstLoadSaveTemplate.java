package model.db;

import model.Speler;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/**
 * @author Rafael Hoek
 */
public class TekstLoadSaveTemplate {
    static File file = new File("src\\bestanden\\speler.txt");

    public static void save(HashMap<String, Speler> list) throws DBException {
        try{
            FileWriter fw = new FileWriter(file);
            for (Speler speler : list.values()){
                char splitser = ',';
                String string = speler.getLastName() + splitser;
                string += speler.getFirstName() + splitser;
                string += speler.getId() + splitser;
                string += speler.getSaldo() + "\n";
                fw.write(string);
            }
            fw.close();
        }
        catch (Exception e){
            throw new DBException(e.getMessage());
        }
    }

    public static HashMap<String, Speler> load() throws DBException {
        HashMap<String, Speler> hashMap = new HashMap<>();
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                Scanner scLine = new Scanner(sc.nextLine());
                scLine.useDelimiter(",");
                String lastName = scLine.next();
                String firstName = scLine.next();
                String id = scLine.next();
                double saldo = Double.parseDouble(scLine.next());
                Speler speler = new Speler(lastName, firstName, id, saldo);
                hashMap.put(id, speler);
            }
        } catch (Exception e){
            throw new DBException(e.getMessage());
        }
        return hashMap;
    }
}


