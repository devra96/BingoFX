package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuDosJugadoresController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnJugar;

    @FXML
    private TextField txtNombreDos;

    @FXML
    private TextField txtNombreUno;

    @FXML
    void JugarPartida(ActionEvent event) {
        if(txtNombreUno.getText().equals("") || txtNombreDos.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setHeaderText("Ha ocurrido un error.");
            a.setContentText("Ambos jugadores debeis escribir vuestro nombre para continuar.");
            a.showAndWait();
        }
        else{
            //PARTIDA
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();
    }

}

