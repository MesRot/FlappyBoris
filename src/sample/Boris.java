package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static sample.PelinAsetukset.MaanKorkeus;


public class Boris extends Esine{
    double painovoima = PelinAsetukset.PainoVoima;
    double hyppyVoima = PelinAsetukset.HyppyVoima;
    boolean ilmassa = false;
    Image juoksuKuva = new Image("file:images/running.gif");
    Image hyppyKuva = new Image("file:images/jumping.png");
    Image tippumisKuva = new Image("file:images/landing.png");
    public Boris() { //TODO: voi myös liikkua x akselilla?
        super(new Rectangle(PelinAsetukset.BorisLeveys, PelinAsetukset.BorisKorkeus), 100, PelinAsetukset.MaanKorkeus);
        super.getHahmo().setFill(new ImagePattern(juoksuKuva));
        System.out.println(juoksuKuva.getHeight() + "    " + juoksuKuva.getWidth());
    }

    public void hyppaa() {
        if(!this.ilmassa){
            super.setLiike(this.hyppyVoima);
            super.asetaKuva(hyppyKuva);
        }
    }

    public void paivita() {
        this.paivitaIlmassa();
        this.liike += this.painovoima;
        if(this.ilmassa && this.liike > 0){
            super.asetaKuva(tippumisKuva);
        }
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
            super.asetaKuva(juoksuKuva);
        }
        else {
            this.ilmassa = true;
        }

    }
}
