package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuUnJugadorController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnJugar;

    @FXML
    private TextField txtNombre;

    static String nombrejugador;

    private Pantalla pantalla = new Pantalla();

    @FXML
    void JugarPartida(ActionEvent event) {
        if(txtNombre.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setHeaderText("Ha ocurrido un error.");
            a.setContentText("Debes escribir tu nombre para continuar.");
            a.showAndWait();
        }
        else if(txtNombre.getLength() > 20){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setHeaderText("Ha ocurrido un error.");
            a.setContentText("El nombre no debe superar los 20 caracteres.");
            a.showAndWait();
        }
        else{
            nombrejugador = txtNombre.getText();
            pantalla.CerrarVentanaActual();
            pantalla.IrJuegoUnJugador();
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuInicio();
    }

}

