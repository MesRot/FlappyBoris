package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Pane ruutu = new Pane();
        ruutu.setPrefSize(PelinAsetukset.RuudunLeveys, PelinAsetukset.RuudunKorkeus);
        Boris bobo = new Boris();
        Esteet esteet = new Esteet();
        ruutu.getChildren().add(esteet.getUusinHahmo());
        ruutu.getChildren().add(bobo.getHahmo());

        Map<KeyCode, Boolean> painetutNapit = new HashMap<>();

        ruutu.setOnKeyPressed(event -> {
            painetutNapit.put(event.getCode(), Boolean.TRUE);
        });
        ruutu.setOnKeyReleased(event -> {
            painetutNapit.put(event.getCode(), Boolean.FALSE);
        });


        new AnimationTimer() {
            @Override
            public void handle(long nykyhetki) {
                if(painetutNapit.getOrDefault(KeyCode.SPACE, false)) {
                    bobo.hyppaa();
                }
                bobo.paivita();
                esteet.paivita(ruutu);
            }
        }.start();

        Scene scene = new Scene(ruutu);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Flappy BOBO");
        stage.show();;
        ruutu.requestFocus();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
