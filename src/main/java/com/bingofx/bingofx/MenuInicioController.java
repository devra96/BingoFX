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

    //OBJETO PARA LLAMAR A METODOS PARA CAMBIAR DE PANTALLA
    private Pantalla pantalla = new Pantalla();

    /**
     * Boton jugar modo 1 jugador
     * @param event
     */
    @FXML
    void JugarUnJugador(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuUnJugador();
    }

    /**
     * Boton jugar modo 2 jugadores
     * @param event
     */
    @FXML
    void JugarDosJugadores(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuDosJugadores();
    }

    /**
     * Boton ver records
     * @param event
     */
    @FXML
    void VerRecords(ActionEvent event) {
        pantalla.IrMenuRecords();
    }

    /**
     * Boton ver manual de usuario. Abre un enlace web al manual.
     * @param event
     */
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
                    java.net.URI uri = new java.net.URI("https://educajcyl-my.sharepoint.com/:b:/g/personal/raul_sasmar_educa_jcyl_es/EaKrbldO-XJIqsSLwy8E170BmaNNhD8HD-gAj_oQmEF-Hw?e=J7oGtq");
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

    /**
     * Salir del juego
     * @param event
     */
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