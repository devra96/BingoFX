package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BingoController implements Initializable {

    @FXML
    private Button btnJugar1;

    @FXML
    private Button btnJugar2;

    @FXML
    private Button btnVolver;

    private String nombrejugador;

    private String hizolinea;

    private String hizobingo;

    private int tiradaslinea;

    private int tiradasbingo;

    private boolean lineanull;

    private boolean bingonull;

    private Pantalla pantalla = new Pantalla();

    @FXML
    void JugarUnJugador(ActionEvent event) {
//        cerrarVentana(event);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuUnJugador.fxml"));
//        try {
//            Parent root = fxmlLoader.load();
//            MenuUnJugadorController controlador = fxmlLoader.getController();
//
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("UN JUGADOR");
//            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
////            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
////                @Override
////                public void handle(WindowEvent windowEvent) {
////                    System.exit(0);
////                }
////            });
//            stage.show();
//
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        pantalla.CerrarVentanaActual();
        pantalla.IrJuegoUnJugador();
    }

    @FXML
    void JugarDosJugadores(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuDosJugadores();
    }

    @FXML
    void VolverInicio(ActionEvent event) {
//        cerrarVentana(event);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuInicio.fxml"));
//        try {
//            Parent root = fxmlLoader.load();
//            MenuInicioController controlador = fxmlLoader.getController();
//
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("BINGOFX (Por Raúl Sastre)");
//            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
//            stage.show();
//
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuInicio();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //UN JUGADOR
        if(MenuUnJugadorController.nombrejugador != null){
            nombrejugador = MenuUnJugadorController.nombrejugador;
            hizolinea = JuegoUnJugadorController.hizolinea;
            hizobingo = JuegoUnJugadorController.hizobingo;
            if(JuegoUnJugadorController.contadorlinea != 0){
                tiradaslinea = JuegoUnJugadorController.contadorlinea;
            }
            if(JuegoUnJugadorController.contadorbingo != 0){
                tiradasbingo = JuegoUnJugadorController.contadorbingo;
            }
        }
        //DOS JUGADORES
        else{
            nombrejugador = JuegoDosJugadoresController.nombreganador;

        }



        System.out.println("Jugador: " + nombrejugador);
        System.out.println("¿Hizo linea?: " +  hizolinea);
//        if(tiradaslinea != 0){
            System.out.println("Tiradas linea: " + tiradaslinea);
//        }
        System.out.println("¿Hizo bingo?: " + hizobingo);
//        if(tiradasbingo != 0){
            System.out.println("Tiradas bingo: " + tiradasbingo);
//        }
    }

    public void RestaurarRegistros(){

    }

//    public static void cerrarVentana(ActionEvent event){
//        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
//        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
//        stage.close();                          //Me cierra la ventana
//    }

}
