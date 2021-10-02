package model.gokstrategie;

import model.gokstrategie.strategieën.*;

/**
 * @author Kobe liesenborgs
 */
public enum GokStrategieEnum {

    minderDan5("Gooi minder dan 5 in elke worp om te winnen.",new GokStrategieMinderDan5(),4),
    evenGooien("Gooi even in elke worp om te winnen.",new GokStrategieEvenGooien(),3),
    totaal21("Gooi in totaal 21 met 4 worpen.",new GokStrategieTotaal21(),4),
    hogerDanVorige("Gooi hoger dan de vorige worp in elke worp.",new GokStrategieHogerDanVorige(),3),
    gegarandeerdWinnen("Je wint altijd hiermee", new GokStrategieGegarandeerdWinnen(), 1);


    private final String beschrijving;
    private final GokStrategie gokStrategie;
    private Integer winstfactor;

    GokStrategieEnum(String beschrijving, GokStrategie strategie, Integer winstfactor){
        this.beschrijving = beschrijving;
        this.gokStrategie = strategie;
        this.winstfactor = winstfactor;
    }

    public String getBeschrijving() {
        return  this.beschrijving;
    }

    public GokStrategie getGokStrategie() {
        return this.gokStrategie;
    }

    public Integer getWinstfactor() {
        return this.winstfactor;
    }

    public void setWinstfactor(Integer winstfactor) { this.winstfactor = winstfactor; }


}
