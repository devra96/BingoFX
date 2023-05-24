package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuUnJugador();
    }

    @FXML
    void JugarDosJugadores(ActionEvent event) {
        pantalla.CerrarVentanaActual();
        pantalla.IrMenuDosJugadores();
    }

    @FXML
    void VolverInicio(ActionEvent event) {
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
            tiradaslinea = JuegoUnJugadorController.contadorlinea;
            tiradasbingo = JuegoUnJugadorController.contadorbingo;
            insertRegistro(nombrejugador,hizolinea,tiradaslinea,hizobingo,tiradasbingo);
            RestaurarRegistros();
        }
        //DOS JUGADORES
        else{
            if(JuegoDosJugadoresController.nombreganador.equals(MenuDosJugadoresController.nombrejugador1)){
                nombrejugador = JuegoDosJugadoresController.nombreganador;
                hizolinea = JuegoDosJugadoresController.hizolineajugador1;
                tiradaslinea = JuegoDosJugadoresController.contadorlineajugador1;
                hizobingo = JuegoDosJugadoresController.hizobingojugador1;
                tiradasbingo = JuegoDosJugadoresController.contadorbingojugador1;
            }
            else{
                nombrejugador = JuegoDosJugadoresController.nombreganador;
                hizolinea = JuegoDosJugadoresController.hizolineajugador2;
                tiradaslinea = JuegoDosJugadoresController.contadorlineajugador2;
                hizobingo = JuegoDosJugadoresController.hizobingojugador2;
                tiradasbingo = JuegoDosJugadoresController.contadorbingojugador2;
            }
            if(insertRegistro(nombrejugador,hizolinea,tiradaslinea,hizobingo,tiradasbingo)){
                //setText se han guardado los datos
            }
            else{
                //setText no se han podido guardar los datos
            }
            RestaurarRegistros();
        }

        System.out.println("Jugador: " + nombrejugador);
        System.out.println("¿Hizo linea?: " +  hizolinea);
        System.out.println("Tiradas linea: " + tiradaslinea);
        System.out.println("¿Hizo bingo?: " + hizobingo);
        System.out.println("Tiradas bingo: " + tiradasbingo);

    }

    public void RestaurarRegistros(){
        MenuUnJugadorController.nombrejugador = null;
        JuegoUnJugadorController.hizolinea = null;
        JuegoUnJugadorController.contadorlinea = 0;
        JuegoUnJugadorController.hizobingo = null;
        JuegoUnJugadorController.contadorbingo = 0;

        MenuDosJugadoresController.nombrejugador1 = null;
        MenuDosJugadoresController.nombrejugador2 = null;
        JuegoDosJugadoresController.hizolineajugador1 = null;
        JuegoDosJugadoresController.contadorlineajugador1 = 0;
        JuegoDosJugadoresController.hizolineajugador2 = null;
        JuegoDosJugadoresController.contadorlineajugador2 = 0;
        JuegoDosJugadoresController.hizobingojugador1 = null;
        JuegoDosJugadoresController.contadorbingojugador1 = 0;
        JuegoDosJugadoresController.hizobingojugador2 = null;
        JuegoDosJugadoresController.contadorbingojugador2 = 0;
    }

    public boolean insertRegistro(String nombrejugador,String hizolinea,int tiradaslinea,String hizobingo,int tiradasbingo){
        try{
            // CONEXION A LA BASE DE DATOS. SI HAY ALGUN ERROR, LO CAPTARA LA EXCEPCION
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/devra1?serverTimezone=UTC","devra96","n6CKKs8GUz");
            // PRUEBAS EN CLASE
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingofx?serverTimezone=UTC","root","root");
            System.out.println("Conexion al servidor establecida correctamente.");
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM bingofx";
            ResultSet rs = st.executeQuery(sql);

            rs.moveToInsertRow();
            rs.updateString(2,nombrejugador);
            rs.updateString(3,hizolinea);
            rs.updateInt(4,tiradaslinea);
            rs.updateString(5,hizobingo);
            rs.updateInt(6,tiradasbingo);
            rs.insertRow();

            con.close();
            return true;
        }
        catch(SQLException e){
//            System.out.println("Error en la consulta: " + e.getErrorCode() + " - " + e.getMessage());

            Label error = new Label("HA OCURRIDO UN ERROR CON LA BASE DE DATOS\n\nCausa del error: " + e.getErrorCode() + " - " + e.getMessage());
            Insets pad = new Insets(20);
            error.setPadding(pad);
//            error.setLayoutX(37);
//            error.setLayoutY(108);

            Pane p = new Pane();
            p.getChildren().addAll(error);
            p.prefHeight(232.0);
            p.prefWidth(348.0);
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.setScene(scene);
            stage.setResizable(false); //IMPEDIR QUE SE PUEDA MODIFICAR LA RESOLUCION DE LA VENTANA
            stage.setTitle("ERROR");
            stage.show();
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
