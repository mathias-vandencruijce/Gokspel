package model.gokstrategie.strategieën;

import model.gokstrategie.GokStrategie;

/**
 * @author Kobe liesenborgs
 */
public class GokStrategieMinderDan5 implements GokStrategie {
    public boolean evalueerGok(int worp){
        if(worp < 5){
            return true;
        }
        return false;
    }

    public String toString(){
        return "minderDan5";
    }
}
