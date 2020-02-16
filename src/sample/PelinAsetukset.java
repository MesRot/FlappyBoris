package sample;

public class PelinAsetukset {
    public static final int RuudunLeveys = 600;
    public static final int RuudunKorkeus = 400;
    public static double EsteenNopeus = -2.0; //TODO: Esteen nopeus kasvaa pelin edetessä
    public static final double PainoVoima = 0.07;
    public static final double HyppyVoima = -4;
    public static final int MaanKorkeus = RuudunKorkeus - (RuudunKorkeus / 3);
    public static final double MaxPutoamisNopeus = 2.0;
    public static final int HeittoHitDetecionissa = 30;


    public static void kasvataEsteNopeutta(){ //TODO: Esteen nopeus kasvaa liian nopeasti, sido se esim framecounttiin
        EsteenNopeus -= 0.01;
    }

    //Älä koske näihin
    public static final int BorisKorkeus = 50;
    public static final int BorisLeveys = 50;

    public static final int EsteKorkeus = 70;
    public static final int EsteLeveys = 70;
}
