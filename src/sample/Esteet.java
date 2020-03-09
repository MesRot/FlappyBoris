package sample;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

/**
 * Pitää hallussaan erilaisista vihollisista ja listan esteistä
 */
public class Esteet {
    Image skeletonWalk = new Image("file:images/skeletonWalk.gif");
    Image snakeWalk = new Image("file:images/snakeWalk.gif");
    Image batWalk = new Image("file:images/batWalk.gif");
    private ArrayList<Este> esteet = new ArrayList<>();

    /**
     * Lisää ensimmäisen esteen esteisiin
     */
    public Esteet(){
        this.lisaaUusi();
    }

    /**Kutsuu metodia joka liikuttaa kaikkia olioita
     * Jos ensimmäinen este on ruudusta ulkona, niin poistaa sen ja lisää uuden (Vaatii pane olion että voi lisätä)
     * @param ruutu
     */
    public void paivita(Pane ruutu){
        liikutaKaikkia();
        if(ekaUlosNaytosta()){
            this.poistaEsteJaLisaaUusi(ruutu);
        }
    }

    /**
     * Poistaa ensimmäisen esteen listasta
     */
    public void poistaEka(){
        esteet.remove(0);
    }

    /**
     * Lisää uuden vihollisen ruudulle
     * Käytetään satunnaisuutta vihollisten valinnassa
     * Erilaisia vihollistyyppejä on lueteltu VIHOLLISET Enum luokassa
     */
    public void lisaaUusi(){
        Random rng = new Random();
        VIHOLLISET vihollinen = VIHOLLISET.values()[rng.nextInt(VIHOLLISET.values().length)];  //arpoo ENUM korkeuksista yhden
        switch (vihollinen){
            case LUURANKO:
                esteet.add(new Este(skeletonWalk, PelinAsetukset.MaanKorkeus-20));
                break;
            case KAARME:
                esteet.add(new Este(snakeWalk, PelinAsetukset.MaanKorkeus-20));
                break;
            case LEPAKKO:
                esteet.add(new Este(batWalk, PelinAsetukset.MaanKorkeus- 100));
        }

    }

    /**
     * Jos ensimmäinen hahmo on pois ruudusta niin palauttaa True arvon
     * @return boolean
     */
    public boolean ekaUlosNaytosta(){
        return esteet.get(0).getX() < -200;
    }

    /**
     * Liikuttaa kaikkia hahmoja
     */
    public void liikutaKaikkia(){
        for(Este e: esteet){
            e.liiku();
        }
    }

    /**Palauttaa ensimmäisen esteen
     * @return Este
     */

    public void poistaEsteJaLisaaUusi(Pane ruutu){
        ruutu.getChildren().remove(this.getEkaEste().getHahmo());
        poistaEka();
        lisaaUusi();
        ruutu.getChildren().add(this.getEkaEste().getHahmo());
    }
    public Este getEkaEste(){
        return esteet.get(0);
    }
}
