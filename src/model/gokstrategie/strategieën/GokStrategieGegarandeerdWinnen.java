package model.gokstrategie.strategieën;

import model.gokstrategie.GokStrategie;

/**
 * @author Kobe liesenborgs
 */
public class GokStrategieGegarandeerdWinnen implements GokStrategie {
    @Override
    public boolean evalueerGok(int worp) {
        return true;
    }

    public String toString(){
        return "gegarandeerdWinnen";
    }
}
