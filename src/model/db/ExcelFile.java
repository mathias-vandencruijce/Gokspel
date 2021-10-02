package model.db;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.Speler;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

/**
 * @author Rafael Hoek, Kobe liesenborgs
 */
public class ExcelFile implements SpelerReadWrite {
    static File file = new File("src\\bestanden\\speler.xls");

    public ExcelFile() { }

    public File getFile() { return file; }

    @Override
    public HashMap<String, Speler> read() throws DBException {
        ExcelPlugin excelPlugin = new ExcelPlugin();
        ArrayList<ArrayList<String>> data;
        HashMap<String , Speler> hashMap = new HashMap<>();

        try {
            data = excelPlugin.read(getFile());

            for(ArrayList<String> array : data) {
                String lastName = array.get(0);
                String firstName = array.get(1);
                String id = array.get(2);
                double saldo = parseInt(array.get(3));

                Speler speler = new Speler(lastName , firstName , id , saldo);
                hashMap.put(id , speler);
            }

        } catch(Exception exc) {
            throw new DBException(exc.getMessage());
        }

        return hashMap;
    }

    @Override
    public void write(HashMap<String, Speler> list) throws DBException {
        ExcelPlugin excelPlugin = new ExcelPlugin();
        ArrayList<ArrayList<String>> input = new ArrayList<>();

        for(Speler speler : list.values()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(speler.getLastName());
            row.add(speler.getFirstName());
            row.add(speler.getId());
            int saldo = (int)speler.getSaldo();
            row.add((valueOf(saldo)));

            input.add(row);
        }

        try {
            excelPlugin.write(getFile() , input);
        } catch (Exception exc) {
            throw new DBException(exc.getMessage());
        }
    }
}
