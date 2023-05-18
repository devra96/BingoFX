package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuUnJugadorController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnJugar;

    @FXML
    private TextField txtNombre;

    @FXML
    void JugarPartida(ActionEvent event) {

    }

    @FXML
    void Cancelar(ActionEvent event) {
        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();
    }

}

