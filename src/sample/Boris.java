package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;


public class Boris extends Esine{
    double painovoima;
    double hyppyVoima;
    boolean ilmassa = false;
    public Boris(int x, int y) {
        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
        this.painovoima = 0.03;
        this.hyppyVoima = -3;

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
        if(this.liike >= 1.0){  //max alaspäin putoamisvauhti
            this.liike = 1.0;
        }
        if(this.liike <= -3.0){
            this.liike = -3.0;
        }
        if(super.getHahmo().getTranslateY() >= 300){
            super.getHahmo().setTranslateY(300);
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
        if(Math.abs(this.getHahmo().getTranslateY() - 300) < 10){
            this.ilmassa = false;
        }
        else {
            this.ilmassa = true;
        }

    }
}
