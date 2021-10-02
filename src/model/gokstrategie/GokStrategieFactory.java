package model.gokstrategie;

/**
 * @author Kobe liesenborgs
 */
public class GokStrategieFactory {

    private static GokStrategieFactory instance = new GokStrategieFactory();

    private GokStrategieFactory(){}

    public static GokStrategieFactory getInstance(){
        return instance;
    }

    public GokStrategie getGokStrategie(String gokstrategie){
        return GokStrategieEnum.valueOf(gokstrategie).getGokStrategie();
    }

}
