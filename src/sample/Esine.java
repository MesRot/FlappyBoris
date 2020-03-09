package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Yliluokka luokille Boris ja Este
 *
 * Omistaa paikan, hahmon muodon ja liikkeen
 */
public class Esine {
    Shape hahmo;
    double liike;
    public Esine(Shape monikulmio, int x, int y) {
        this.hahmo = monikulmio;
        this.hahmo.setTranslateX(x);
        this.hahmo.setTranslateY(y);
        this.liike = 0.0;
    }

    /**
     * @return Shape
     *  Palauttaa hahmon Shape olion
     */
    public Shape getHahmo(){
        return hahmo;
    }

    public void setX(double luku){
        //shortcut for super.getHahmo.setTranslateX()
        this.hahmo.setTranslateX(luku);
    }
    public void setY(double luku){
        //shortcut for super.getHahmo.setTranslateY()
        this.hahmo.setTranslateY(luku);
    }
    public double getX(){
        //shortcut for super.getHahmo.GetTranslateX()
        return this.hahmo.getTranslateX();
    }
    public double getY(){
        //shortcut for super.getHahmo.getTranslateY()
        return this.hahmo.getTranslateY();
    }

    /**
     * @param kuva
     * Asettaa hahmon kuvaksi parametrina saadu kuvan
     */
    public void asetaKuva(Image kuva){
        this.hahmo.setFill(new ImagePattern(kuva));
    }

    /**
     * @param luku Asettaa hahmolle parametrina saadun luvun liikkeeksi
     */
    public void setLiike(double luku){
        this.liike = luku;
    }
}
