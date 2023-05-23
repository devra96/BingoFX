package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MenuDosJugadoresController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnJugar;

    @FXML
    private TextField txtNombreUno;

    @FXML
    private TextField txtNombreDos;

    static String nombrejugador1;
    static String nombrejugador2;

    private Pantalla pantalla = new Pantalla();

    @FXML
    void JugarPartida(ActionEvent event) {
        if(txtNombreUno.getText().equals("") || txtNombreDos.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setHeaderText("Ha ocurrido un error.");
            a.setContentText("Ambos jugadores debeis escribir vuestro nombre para continuar.");
            a.showAndWait();
        }
        else if(txtNombreUno.getLength() > 20 || txtNombreDos.getLength() > 20){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setHeaderText("Ha ocurrido un error.");
            a.setContentText("Ambos nombres no deben superar los 20 caracteres.");
            a.showAndWait();
        }
        else{
//            cerrarVentana(event);
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoDosJugadores.fxml"));
//            try {
//                Parent root = fxmlLoader.load();
//                JuegoDosJugadoresController controlador = fxmlLoader.getController();
//
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
//                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                    @Override
//                    public void handle(WindowEvent windowEvent) {
//                        System.exit(0);
//                    }
//                });
//                stage.show();
//
//            }
//            catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            nombrejugador1 = txtNombreUno.getText();
            nombrejugador2 = txtNombreDos.getText();
            pantalla.CerrarVentanaActual();
            pantalla.IrJuegoDosJugadores();
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
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

//    public static void cerrarVentana(ActionEvent event){
//        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
//        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
//        stage.close();                          //Me cierra la ventana
//    }

}

