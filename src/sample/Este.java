package sample;

import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.Esine;

import java.util.Random;

public class Este extends Esine{
    public Este(Image image, int korkeus) {
        super(new Rectangle(PelinAsetukset.EsteLeveys,PelinAsetukset.EsteKorkeus), PelinAsetukset.RuudunLeveys, korkeus);
        super.setLiike(PelinAsetukset.EsteenNopeus);
        super.asetaKuva(image);
    }
    public void liiku(){
        super.setX(super.getX() + super.liike);
    }
}
