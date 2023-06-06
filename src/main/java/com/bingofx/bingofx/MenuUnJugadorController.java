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

    //VARIABLE ESTATICA QUE GUARDA EL NOMBRE DEL JUGADOR
    static String nombrejugador;

    //OBJETO PARA LLAMAR A METODOS PARA CAMBIAR DE PANTALLA
    private Pantalla pantalla = new Pantalla();

    /**
     * Comprueba si se ha escrito el nombre correctamente y lleva
     * a la pantalla de juego
     * @param event
     */
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

    /**
     * Cancelar y volver al menu principal
     * @param event
     */
    @FXML
    void Cancelar(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuInicio();
    }

}