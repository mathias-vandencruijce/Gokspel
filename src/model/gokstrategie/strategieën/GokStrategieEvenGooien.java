package model.gokstrategie.strategieën;

import model.gokstrategie.GokStrategie;

/**
 * @author Kobe liesenborgs
 */
public class GokStrategieEvenGooien implements GokStrategie {
    public boolean evalueerGok(int worp){
        if(worp % 2==0){
            return true;
        }
        return false;
    }

    public String toString(){
        return "evenGooien";
    }
}
