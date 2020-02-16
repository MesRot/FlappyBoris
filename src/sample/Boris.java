package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static sample.PelinAsetukset.MaanKorkeus;


public class Boris extends Esine{
    double painovoima = PelinAsetukset.PainoVoima;
    double hyppyVoima = PelinAsetukset.HyppyVoima;
    boolean ilmassa = false;
    //Image image = new Image("running.gif");
    public Boris() { //TODO: voi myös liikkua x akselilla?
        super(new Rectangle(16, 16), 100, PelinAsetukset.MaanKorkeus);
    }

    public void hyppaa() {
        if(!this.ilmassa){
            super.setLiike(this.hyppyVoima);
        }
    }

    public void paivita() {
        this.paivitaIlmassa();
        this.liike += this.painovoima;
        super.setY(super.getY() + this.liike);
        if(this.liike >= PelinAsetukset.MaxPutoamisNopeus){  //max alaspäin putoamisvauhti
            this.liike = PelinAsetukset.MaxPutoamisNopeus;
        }
        if(this.liike <= -3.0){
            this.liike = -3.0;
        }
        if(super.getHahmo().getTranslateY() >= PelinAsetukset.MaanKorkeus){
            super.getHahmo().setTranslateY(PelinAsetukset.MaanKorkeus);
        }

    }
    public void paivitaIlmassa(){
        if(this.getHahmo().getTranslateY() - PelinAsetukset.MaanKorkeus == 0){
            this.ilmassa = false;
        }
        else {
            this.ilmassa = true;
        }

    }
}
