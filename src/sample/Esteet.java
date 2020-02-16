package sample;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

public class Esteet {
    Image skeletonWalk = new Image("file:images/skeletonWalk.gif");
    Image snakeWalk = new Image("file:images/snakeWalk.gif");
    Image batWalk = new Image("file:images/batWalk.gif");
    private ArrayList<Este> esteet = new ArrayList<>();

    public Esteet(){
        this.lisaaUusi();
    }
    public void paivita(Pane ruutu){
        liikutaKaikkia();
        if(ekaUlosNaytosta()){
            poistaEka();
            lisaaUusi();
            ruutu.getChildren().add(this.getEkaEste().getHahmo());
        }
    }

    public void poistaEka(){
        esteet.remove(0);
    }
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
    public boolean ekaUlosNaytosta(){
        return esteet.get(0).getX() < -200;
    }
    public void liikutaKaikkia(){
        for(Este e: esteet){
            e.liiku();
        }
    }
    public Este getEkaEste(){
        return esteet.get(0);
    }
}
