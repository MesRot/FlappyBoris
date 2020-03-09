package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 *
 */
public class Boris extends Esine{
    boolean ilmassa = false;
    boolean tormannyt = false;
    Image juoksuKuva = new Image("file:images/running.gif");
    Image hyppyKuva = new Image("file:images/jumping.png");
    Image tippumisKuva = new Image("file:images/landing.png");

    /**
     * Pelin "Juoksija" luokka boris, Esineen aliluokka
     * Asettaa juoksemisanimaation ja paikan
     */
    public Boris() { //TODO: voi myös liikkua x akselilla?
        super(new Rectangle(PelinAsetukset.BorisLeveys, PelinAsetukset.BorisKorkeus), 100, PelinAsetukset.MaanKorkeus);
        super.getHahmo().setFill(new ImagePattern(juoksuKuva));
    }

    /**
     * Jos hahmo on maassa niin asettaa hahmon liikkeeksi hyppyvoiman ja asettaa kuvaksi hyppykuvan
     */
    public void hyppaa() {

        if(!this.ilmassa){
            super.setLiike(PelinAsetukset.HyppyVoima);
            super.asetaKuva(hyppyKuva);
        }
    }

    /**
     * Paivittaa hahmom. Kutsutaan main metodissa joka framella
     * Päivittää hahmon ilmassa olon ja lisää liikkeeseen painovoiman
     * Jos hahmon liike on ylöspäin niin lisää ylöspin hyppäävän kuvan
     * Jos hahmon liike on alaspäin niin lisää alaspäin putoavan kuvan
     * Pitää myös huolta ettei hahmo tipu maan läpi
     */
    public void paivita() {

        this.paivitaIlmassa();
        this.liike += PelinAsetukset.PainoVoima;
        if(this.ilmassa && this.liike > 0){
            super.asetaKuva(tippumisKuva);
        }
        super.setY(super.getY() + this.liike);
        if(this.liike >= PelinAsetukset.MaxPutoamisNopeus){  //max alaspäin putoamisvauhti
            this.liike = PelinAsetukset.MaxPutoamisNopeus;
        }
        if(super.getHahmo().getTranslateY() >= PelinAsetukset.MaanKorkeus){
            super.getHahmo().setTranslateY(PelinAsetukset.MaanKorkeus);
        }

    }

    /**
     * Paivittaa hahmon ilmassa olon Booleanin
     * Jos hahmon jalat ovat maassa niin muuttaa ilmassa = False ja asettaa juoksuanimaation
     */
    public void paivitaIlmassa(){
        if(this.getHahmo().getTranslateY() - PelinAsetukset.MaanKorkeus == 0){
            this.ilmassa = false;
            super.asetaKuva(juoksuKuva);
        }
        else {
            this.ilmassa = true;
        }
    }

    /**
     * Päivittää hahmon törmäys statuksen
     * @param esine Toinen esine jonka kanssa tarkastetaan törmäys
     *
     */
    public void tarkistaTormays(Este esine){

        Shape tormaysAlue = Shape.intersect(super.getHahmo(), esine.getHahmo());
        if(tormaysAlue.getBoundsInLocal().getWidth() > PelinAsetukset.HeittoHitDetecionissa){
            this.tormannyt = true;
        }
    }
}
