package model.gokstrategie.strategieën;

import model.gokstrategie.GokStrategie;

/**
 * @author Kobe liesenborgs
 */
public class GokStrategieHogerDanVorige implements GokStrategie {
    private int vorigeWorp = 0;
    public boolean evalueerGok(int worp){
        if(worp > vorigeWorp){
            vorigeWorp = worp;
            return true;
        }
        vorigeWorp = 0;
        return false;
    }

    public String toString(){
        return "hogerDanVorige";
    }
}
