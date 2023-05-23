package com.bingofx.bingofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MenuRecordsController implements Initializable {

    @FXML
    private Button btnFiltroBingo;

    @FXML
    private Button btnFiltroLinea;

    @FXML
    private Button btnVerHistorial;

    @FXML
    private Label txtCargando;

    @FXML
    private TableColumn<?, ?> colnombrejugador;

    @FXML
    private TableColumn<?, ?> colnumpartida;

    @FXML
    private TableColumn<?, ?> coltiradasbingo;

    @FXML
    private TableColumn<?, ?> coltiradaslinea;

    @FXML
    private TableView<Registro> tabla;

    private ObservableList<Registro> registros;

    @FXML
    void FiltroHistorial(ActionEvent event) {
        tabla.getItems().clear();

        try{
            // CONEXION A LA BASE DE DATOS. SI HAY ALGUN ERROR, LO CAPTARA LA EXCEPCION
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/devra1?serverTimezone=UTC","devra96","n6CKKs8GUz");
            // PRUEBAS EN CLASE
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingofx?serverTimezone=UTC","root","root");
            System.out.println("Conexion al servidor establecida correctamente.");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM bingofx";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                int numpartida = rs.getInt("numpartida");
                String nombrejugador = rs.getString("nombrejugador");
                String tiradaslinea = rs.getString("tiradaslinea");
                String tiradasbingo = rs.getString("tiradasbingo");
                Registro r = new Registro(numpartida,nombrejugador,tiradaslinea,tiradasbingo);
                registros.add(r);
                tabla.setItems(registros);
            }
            con.close();
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
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void FiltroLinea(ActionEvent event) {
        tabla.getItems().clear();
        try{
            // CONEXION A LA BASE DE DATOS. SI HAY ALGUN ERROR, LO CAPTARA LA EXCEPCION
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/devra1?serverTimezone=UTC","devra96","n6CKKs8GUz");
            // PRUEBAS EN CLASE
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingofx?serverTimezone=UTC","root","root");
            System.out.println("Conexion al servidor establecida correctamente.");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM bingofx ORDER BY tiradaslinea ASC LIMIT 10";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                int numpartida = rs.getInt("numpartida");
                String nombrejugador = rs.getString("nombrejugador");
                String tiradaslinea = rs.getString("tiradaslinea");
                String tiradasbingo = rs.getString("tiradasbingo");
                Registro r = new Registro(numpartida,nombrejugador,tiradaslinea,tiradasbingo);
                registros.add(r);
                tabla.setItems(registros);
            }
            con.close();
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
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void FiltroBingo(ActionEvent event) {
        tabla.getItems().clear();
        try{
            // CONEXION A LA BASE DE DATOS. SI HAY ALGUN ERROR, LO CAPTARA LA EXCEPCION
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/devra1?serverTimezone=UTC","devra96","n6CKKs8GUz");
            // PRUEBAS EN CLASE
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingofx?serverTimezone=UTC","root","root");
            System.out.println("Conexion al servidor establecida correctamente.");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM bingofx ORDER BY tiradasbingo ASC LIMIT 10";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                int numpartida = rs.getInt("numpartida");
                String nombrejugador = rs.getString("nombrejugador");
                String tiradaslinea = rs.getString("tiradaslinea");
                String tiradasbingo = rs.getString("tiradasbingo");
                Registro r = new Registro(numpartida,nombrejugador,tiradaslinea,tiradasbingo);
                registros.add(r);
                tabla.setItems(registros);
            }
            con.close();
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
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registros = FXCollections.observableArrayList();

        this.colnumpartida.setCellValueFactory(new PropertyValueFactory("numpartida"));
        this.colnombrejugador.setCellValueFactory(new PropertyValueFactory("nombrejugador"));
        this.coltiradaslinea.setCellValueFactory(new PropertyValueFactory("tiradaslinea"));
        this.coltiradasbingo.setCellValueFactory(new PropertyValueFactory("tiradasbingo"));

        try{
            // CONEXION A LA BASE DE DATOS. SI HAY ALGUN ERROR, LO CAPTARA LA EXCEPCION
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/devra1?serverTimezone=UTC","devra96","n6CKKs8GUz");
            // PRUEBAS EN CLASE
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingofx?serverTimezone=UTC","root","root");
            System.out.println("Conexion al servidor establecida correctamente.");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM bingofx";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                int numpartida = rs.getInt("numpartida");
                String nombrejugador = rs.getString("nombrejugador");
                String tiradaslinea = rs.getString("tiradaslinea");
                String tiradasbingo = rs.getString("tiradasbingo");
                Registro r = new Registro(numpartida,nombrejugador,tiradaslinea,tiradasbingo);
                registros.add(r);
                tabla.setItems(registros);
            }
            con.close();
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
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
