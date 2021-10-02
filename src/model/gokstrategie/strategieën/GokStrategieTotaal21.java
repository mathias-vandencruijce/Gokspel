package model.gokstrategie.strategieën;

import model.gokstrategie.GokStrategie;

/**
 * @author Kobe liesenborgs
 */
public class GokStrategieTotaal21 implements GokStrategie {
    private int totaal = 0;
    private int worpCount = 0;
    public boolean evalueerGok(int worp){
        totaal += worp;
        worpCount++;
        if(worpCount < 4 && totaal + ( 6 * ( 4 - worpCount ) ) >= 21 && totaal < 21){
            return true;
        }
        else if(totaal == 21){
            return true;
        }
        totaal = 0;
        worpCount = 0;
        return false;
    }

    public String toString(){
        return "totaal21";
    }
}
