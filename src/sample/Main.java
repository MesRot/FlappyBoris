package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
////images from https://jesse-m.itch.io/skeleton-pack, https://jesse-m.itch.io/jungle-pack, https://goglilol.itch.io/cute-knight
//music from https://www.playonloop.com/2018-music-loops/star-way/#free-download, https://nicolemariet.itch.io/spy-8-bit-16-bit?download
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Pisteet pistehallinta = new Pisteet();
        Media sound = new Media(new File("music/forest.wav").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Pane ruutu = new Pane();
        ruutu.setPrefSize(PelinAsetukset.RuudunLeveys, PelinAsetukset.RuudunKorkeus);
        Boris bobo = new Boris();
        Esteet esteet = new Esteet();
        teeTausta(ruutu);

        ruutu.getChildren().add(new Rectangle(0, PelinAsetukset.MaanKorkeus+PelinAsetukset.BorisKorkeus, PelinAsetukset.RuudunLeveys, PelinAsetukset.MaanKorkeus));
        ruutu.getChildren().addAll(esteet.getEkaEste().getHahmo(), bobo.getHahmo());


        Map<KeyCode, Boolean> painetutNapit = new HashMap<>();

        ruutu.setOnKeyPressed(event -> {
            painetutNapit.put(event.getCode(), Boolean.TRUE);
        });
        ruutu.setOnKeyReleased(event -> {
            painetutNapit.put(event.getCode(), Boolean.FALSE);
        });


        new AnimationTimer() {
            int pisteet = 0;
            @Override
            public void handle(long nykyhetki) {//Todo: korjaa score teksti animaatio
                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.seek(Duration.ZERO);
                    }
                });
                if(nykyhetki % 100 == 0){
                    pisteet++;
                    PelinAsetukset.kasvataEsteNopeutta();
                }
                if(painetutNapit.getOrDefault(KeyCode.SPACE, false)) {
                    bobo.hyppaa();
                }
                bobo.paivita();
                esteet.paivita(ruutu);
                bobo.tarkistaTormays(esteet.getEkaEste());
                if(bobo.tormannyt){
                    this.stop();
                    try {
                        asetaPeliOhi(ruutu, pisteet, pistehallinta);
                    } catch (SQLException e) {

                    }
                    try {
                        pistehallinta.tallenna(pisteet);
                    } catch (SQLException e) {
                        System.out.println("Pisteiden tallennus ei toiminut");
                    }

                    //TODO: lisaa idle animaatiot kun pelaaja tormaa ja lisaa game over naytto
                }
            }
        }.start();


        Scene scene = new Scene(ruutu);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Flappy BOBO");
        stage.show();;
        ruutu.requestFocus();

    }

    public void teeTausta(Pane ruutu){
        ArrayList<Image> taustat = new ArrayList<>();
        Image background1 = new Image("file:images/plx-1.png");
        Image background2 = new Image("file:images/plx-2.png");
        Image background3 = new Image("file:images/plx-3.png");
        Image background4 = new Image("file:images/plx-4.png");
        Image background5 = new Image("file:images/plx-5.png");
        taustat.add(background1);
        taustat.add(background2);
        taustat.add(background3);
        taustat.add(background4);
        taustat.add(background5);
        for(Image i: taustat){
            Rectangle tausta = new Rectangle(0, 0, PelinAsetukset.RuudunLeveys, PelinAsetukset.MaanKorkeus + PelinAsetukset.BorisKorkeus);
            tausta.setFill(new ImagePattern(i));
            ruutu.getChildren().add(tausta);
        }

    }

    public void asetaPeliOhi(Pane ruutu, int pisteet, Pisteet pistehallinta) throws SQLException {
        Rectangle peitto = new Rectangle(0, 0, PelinAsetukset.RuudunLeveys, PelinAsetukset.RuudunKorkeus);
        Text tamanpelinpisteet = new Text(200, 150, "Pisteet tästä pelikierroksesta: " + pisteet);
        Text peliohiteksti = new Text(200 , 100, "Peli ohi :(");
        peliohiteksti.setFont(new Font(20));
        peitto.setFill(Color.ORANGE);
        ruutu.getChildren().addAll(peitto, peliohiteksti, tamanpelinpisteet);

        try{
            Text top3 = new Text(200, 185, "TOP 3 pisteet: ");
            ruutu.getChildren().add(top3);
            for(int i = 0; i < 3; i++){
                Text piste = new Text(200, 220 + i * 50, Integer.toString(pistehallinta.getTop3().get(i)));
                ruutu.getChildren().add(piste);
            }
        }
        catch (Exception e){
            System.out.println("Ei tarpeeksi pisteitä järjestelmässä");
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
