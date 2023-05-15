package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MenuInicioController {

    @FXML
    private Button buttonJugar;

    @FXML
    private Button buttonSalir;

    @FXML
    private Button buttonVerRecords;

    @FXML
    void VerRecords(ActionEvent event) {

    }

    @FXML
    void Salir(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Salir del juego");
        a.setContentText("¿Quieres salir?");
        Optional<ButtonType> r = a.showAndWait();
        if(r.get() == ButtonType.OK){
            System.exit(0);
//            Platform.exit();
        }
    }

}