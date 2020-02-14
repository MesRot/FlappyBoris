package sample;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Random;

public class Esteet {
    private ArrayList<Este> esteet = new ArrayList<>();

    public Esteet(){
        this.lisaaUusi();
    }
    public void paivita(Pane ruutu){
        liikutaKaikkia();
        if(ekaUlosNaytosta()){
            poistaEka();
            lisaaUusi();
            ruutu.getChildren().add(getUusinHahmo());
        }
    }

    public void poistaEka(){
        esteet.remove(0);
    }
    public void lisaaUusi(){
        Random rng = new Random();
        KORKEUDET korkeus = KORKEUDET.values()[rng.nextInt(KORKEUDET.values().length)];  //arpoo ENUM korkeuksista yhden
        switch (korkeus){ //TODO: Pystyy arpomaan myös erimuotoisia esteitä
            case MATALA:
                esteet.add(new Este(100));
                break;
            case KESKELLA:
                esteet.add(new Este(200));
                break;
            case KORKEA:
                esteet.add(new Este(300));
                break;
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
    public Polygon getUusinHahmo(){
        return esteet.get(esteet.size() - 1).getHahmo();
    }
}
