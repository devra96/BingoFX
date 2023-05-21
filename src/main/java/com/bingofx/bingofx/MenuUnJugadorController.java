package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MenuUnJugadorController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnJugar;

    @FXML
    private TextField txtNombre;

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
            // COMO PASO EL NOMBRE A LA CLASE "JuegoUnJugadorController??"
            cerrarVentana(event);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoUnJugador.fxml"));
            try {
                Parent root = fxmlLoader.load();
                JuegoUnJugadorController controlador = fxmlLoader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
                stage.show();

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
        cerrarVentana(event);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuInicio.fxml"));
        try {
            Parent root = fxmlLoader.load();
            MenuInicioController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cerrarVentana(ActionEvent event){
        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();                          //Me cierra la ventana
    }

}

