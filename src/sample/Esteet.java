package sample;

import java.util.ArrayList;

public class Esteet {
    private ArrayList<Este> esteet = new ArrayList<>();

    public Esteet(){
        esteet.add(new Este());
    }

    public void poistaEka(){
        esteet.remove(0);
    }
    public void lisaaUusi(){
        esteet.add(new Este());
    }
    public boolean ekaUlosNaytosta(){
        return esteet.get(0).getX() < -200;
    }
    public void paivitaKaikki(){
        if(ekaUlosNaytosta()){
            this.poistaEka();
            this.lisaaUusi();
        }
        for(Este e: esteet){
            e.liiku();
        }
    }
}
