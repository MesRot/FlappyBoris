package sample;

public class PelinAsetukset {
    public static final int RuudunLeveys = 600;
    public static final int RuudunKorkeus = 400;
    public static double EsteenNopeus = -2.0; //TODO: Esteen nopeus kasvaa pelin edetessä
    public static final double PainoVoima = 0.05;
    public static final double HyppyVoima = -3;
    public static final int MaanKorkeus = RuudunKorkeus - (RuudunKorkeus / 4);
    public static final double MaxPutoamisNopeus = 2.0;


    public static void kasvataEsteNopeutta(){ //TODO: Esteen nopeus kasvaa liian nopeasti, sido se esim framecounttiin
        EsteenNopeus -= 0.1;
    }

    //Älä koske näihin
    public static final int HahmonKorkeus = 20;
}
