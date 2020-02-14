package sample;

import javafx.scene.shape.Polygon;
import sample.Esine;

import java.util.Random;

public class Este extends Esine{
    public Este(int korkeus) {
        super(new Polygon(0, 0, 100, 0, 100, 50, 0, 50), PelinAsetukset.RuudunLeveys, korkeus);
        super.setLiike(PelinAsetukset.EsteenNopeus);

    }
    public void liiku(){
        super.setX(super.getX() + super.liike);
    }
    public int arvoKorkeus(){
        Random rng = new Random();
        KORKEUDET korkeus = KORKEUDET.values()[rng.nextInt(KORKEUDET.values().length)];
        switch (korkeus){
            case MATALA:
                return 100;
            case KESKELLA:
                return 150;
            case KORKEA:
                return 200;
        }
        return 200;
    }
}
