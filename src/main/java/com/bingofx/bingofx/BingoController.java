package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class BingoController {

    @FXML
    private Button btnJugar;

    @FXML
    private Button btnVolver;

    @FXML
    void Jugar(ActionEvent event) {
        cerrarVentana(event);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuUnJugador.fxml"));
        try {
            Parent root = fxmlLoader.load();
            MenuUnJugadorController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent windowEvent) {
//                    System.exit(0);
//                }
//            });
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void VolverInicio(ActionEvent event) {
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
