package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Pantalla {
    private Window getWindow() {
        return Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
    }

    public void CerrarVentanaActual(){
        Stage ventanaActual = (Stage) getWindow();
        ventanaActual.close();
    }

    /**
     * Metodo para cerrar la ventana donde este el boton que se ha pulsado
     * @param event
     */
    public void cerrarVentana(ActionEvent event){
        Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();                          //Me cierra la ventana
    }

    public void IrMenuInicio(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuInicio.fxml"));
        try {
            Parent root = fxmlLoader.load();
            MenuInicioController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("BINGOFX (Por Raúl Sastre)");
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void IrMenuRecords(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuRecords.fxml"));

        try {
            Parent root = fxmlLoader.load();
            MenuRecordsController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("RECORDS");
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void IrMenuUnJugador(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuUnJugador.fxml"));

        try {
            Parent root = fxmlLoader.load();
            MenuUnJugadorController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("UN JUGADOR");
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void IrMenuDosJugadores(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuDosJugadores.fxml"));

        try {
            Parent root = fxmlLoader.load();
            MenuDosJugadoresController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("DOS JUGADORES");
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void IrJuegoUnJugador(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoUnJugador.fxml"));
        try {
            Parent root = fxmlLoader.load();
            JuegoUnJugadorController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA

            // CODIGO QUE HACE QUE CUANDO CIERRE LA VENTANA JuegoUnJugadorController SE DETENGA EL HILO
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    System.exit(0);
                }
            });

            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void IrJuegoDosJugadores(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoDosJugadores.fxml"));
        try {
            Parent root = fxmlLoader.load();
            JuegoDosJugadoresController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    System.exit(0);
                }
            });
            stage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void IrBingo(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Bingo.fxml"));
        try {
            Parent root = fxmlLoader.load();
            BingoController controlador = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("¡¡¡BINGO!!!");
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
