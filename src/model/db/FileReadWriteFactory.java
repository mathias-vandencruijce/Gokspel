package model.db;

import model.gokstrategie.GokStrategieFactory;

/**
 * @author Rafael Hoek, Kobe liesenborgs
 */
public class FileReadWriteFactory {

    private static FileReadWriteFactory instance = new FileReadWriteFactory();

    private FileReadWriteFactory(){}

    public static FileReadWriteFactory getInstance(){
        return instance;
    }

    public SpelerReadWrite getFileHandler(String handlerName) {
        SpelerReadWrite fileHandler = null;
        try {
            Class handlerClass = Class.forName("model.db." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            fileHandler = (SpelerReadWrite) handlerObject;
        } catch (Exception exc) {
            throw new DBException(exc.getMessage());
        }
        return fileHandler;
    }
}
