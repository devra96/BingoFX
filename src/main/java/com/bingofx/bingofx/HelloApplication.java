package com.bingofx.bingofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MenuInicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BINGOFX (Por Raúl Sastre)");
        stage.setScene(scene);
        stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
        stage.show();
    }

    public static void main(String[] args){
//        reproducirMusica("casinoluigi");
        launch();
    }

    public static void reproducirMusica(String archivo){
        try{
            String url = "src/main/resources/com/bingofx/bingofx/music/" + archivo + ".wav";
            AudioInputStream a = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
            Clip c = AudioSystem.getClip();
            c.open(a);
            c.start();
            c.loop(1000);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e.getMessage());
        }
    }
}