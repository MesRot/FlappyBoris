package sample;

import javafx.scene.shape.Polygon;
import sample.Esine;

public class Este extends Esine{
    double nopeus;
    public Este() {
        super(new Polygon(0, 0, 100, 0, 100, 50, 0, 50), 400, 100);
        super.setLiike(-1.0);
    }
    public void liiku(){
        super.setX(super.getX() + liike);
    }
}
