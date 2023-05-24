package com.bingofx.bingofx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;

public class DBManager {
    // Conexión a la base de datos
    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "db4free";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "devra1";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    private static final String DB_USER = "devra96";
    private static final String DB_PASS = "n6CKKs8GUz";

    public static void cargarDriver(){
        try{
            System.out.print("Cargando Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean conectar(){
        try{
            // CONEXION A LA BASE DE DATOS. SI HAY ALGUN ERROR, LO CAPTARA LA EXCEPCION
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/devra1?serverTimezone=UTC","devra96","n6CKKs8GUz");
            // PRUEBAS EN CLASE
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingofx?serverTimezone=UTC","root","root");
            System.out.println("Conexion al servidor establecida correctamente.");
            return true;
        }
        catch(SQLException e){
            Label error = new Label("HA OCURRIDO UN ERROR CON LA BASE DE DATOS\n\nCausa del error: " + e.getErrorCode() + " - " + e.getMessage());
            Insets pad = new Insets(20);
            error.setPadding(pad);

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

    public static void cerrar(){
        try{
            System.out.print("Cerrando la conexión...");
            conn.close();
            System.out.println("OK!");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
