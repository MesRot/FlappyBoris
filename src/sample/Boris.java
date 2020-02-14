package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

import static sample.PelinAsetukset.MaanKorkeus;


public class Boris extends Esine{
    double painovoima = PelinAsetukset.PainoVoima;
    double hyppyVoima = PelinAsetukset.HyppyVoima;
    boolean ilmassa = false;
    public Boris() { //TODO: voi myös liikkua x akselilla?
        super(new Polygon(-5, -5, 10, 0, -5, 5), 100, PelinAsetukset.MaanKorkeus);
    }

    public void hyppaa() {
        if(!this.ilmassa){
            super.setLiike(this.hyppyVoima);
        }
    }

    public void paivita() {
        this.paivitaIlmassa();
        this.liike += this.painovoima;
        super.getHahmo().setTranslateY(super.getHahmo().getTranslateY() + this.liike);
        if(this.liike >= PelinAsetukset.MaxPutoamisNopeus){  //max alaspäin putoamisvauhti
            this.liike = PelinAsetukset.MaxPutoamisNopeus;
        }
        if(this.liike <= -3.0){
            this.liike = -3.0;
        }
        if(super.getHahmo().getTranslateY() >= MaanKorkeus){
            super.getHahmo().setTranslateY(MaanKorkeus);
        }
        /***
        if (this.y >= height - this.height / 2) {
            this.y = height - this.height / 2;
            this.velocity = 0;
        }

        if (this.y <= this.height / 2) {
            this.y = this.height / 2;
            this.velocity = 0;
        }*/

    }
    public void paivitaIlmassa(){
        if(Math.abs(this.getHahmo().getTranslateY() - PelinAsetukset.MaanKorkeus) < 10){
            this.ilmassa = false;
        }
        else {
            this.ilmassa = true;
        }

    }
}
