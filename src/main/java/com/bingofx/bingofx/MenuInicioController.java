package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class MenuInicioController {

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnVerRecords;

    @FXML
    private Button btndosjugadores;

    @FXML
    private Button btnunjugador;

    @FXML
    private Button btnManual;

    private Pantalla pantalla = new Pantalla();

    @FXML
    void JugarUnJugador(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuUnJugador();
    }

    @FXML
    void JugarDosJugadores(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuDosJugadores();
    }

    @FXML
    void VerRecords(ActionEvent event) {
        pantalla.IrMenuRecords();
    }

    @FXML
    void VerManual(ActionEvent event) {
//        VER ARCHIVO MODO LOCAL
//        try{
//            File f = new File("src/main/resources/com/bingofx/bingofx/BINGOFX - MANUAL DE USUARIO.pdf");
//            Desktop.getDesktop().open(f);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

//        VER ARCHIVO MODO WEB
        if(java.awt.Desktop.isDesktopSupported()){
            java.awt.Desktop d = java.awt.Desktop.getDesktop();
            if(d.isSupported(Desktop.Action.BROWSE)){
                try{
                    java.net.URI uri = new java.net.URI("https://educajcyl-my.sharepoint.com/:b:/g/personal/raul_sasmar_educa_jcyl_es/EQJKlvlug4xIs00K9pBZTVEBs8FMhudmNyWb6ajpyAvTgA?e=dF38pK");
                    d.browse(uri);
                }
                catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void Salir(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Salir del juego");
        a.setContentText("¿Quieres salir?");
        Optional<ButtonType> r = a.showAndWait();
        if(r.get() == ButtonType.OK){
            System.exit(0);
        }
    }
}