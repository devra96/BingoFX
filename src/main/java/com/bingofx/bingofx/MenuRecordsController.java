package com.bingofx.bingofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TableColumn<?, ?> colnumpartida;

    @FXML
    private TableColumn<?, ?> colnombrejugador;

    @FXML
    private TableColumn<?, ?> colhizolinea;

    @FXML
    private TableColumn<?, ?> coltiradaslinea;

    @FXML
    private TableColumn<?, ?> colhizobingo;

    @FXML
    private TableColumn<?, ?> coltiradasbingo;

    @FXML
    private TableColumn<?, ?> colcomentario;

    @FXML
    private TableView<Registro> tabla;

    private ObservableList<Registro> registros;

    @FXML
    void FiltroHistorial(ActionEvent event) {
        tabla.getItems().clear();
        getTablaRecords("SELECT * FROM bingofx ORDER BY numpartida DESC");
        btnVerHistorial.setDisable(true);
        btnFiltroLinea.setDisable(false);
        btnFiltroBingo.setDisable(false);
    }

    @FXML
    void FiltroLinea(ActionEvent event) {
        tabla.getItems().clear();
        getTablaRecords("SELECT * FROM bingofx ORDER BY hizolinea DESC, tiradaslinea ASC");
        btnVerHistorial.setDisable(false);
        btnFiltroLinea.setDisable(true);
        btnFiltroBingo.setDisable(false);
    }

    @FXML
    void FiltroBingo(ActionEvent event) {
        tabla.getItems().clear();
        getTablaRecords("SELECT * FROM bingofx ORDER BY hizobingo DESC, tiradasbingo ASC");
        btnVerHistorial.setDisable(false);
        btnFiltroLinea.setDisable(false);
        btnFiltroBingo.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registros = FXCollections.observableArrayList();

        this.colnumpartida.setCellValueFactory(new PropertyValueFactory("numpartida"));
        this.colnombrejugador.setCellValueFactory(new PropertyValueFactory("nombrejugador"));
        this.colhizolinea.setCellValueFactory(new PropertyValueFactory("hizolinea"));
        this.coltiradaslinea.setCellValueFactory(new PropertyValueFactory("tiradaslinea"));
        this.colhizobingo.setCellValueFactory(new PropertyValueFactory("hizobingo"));
        this.coltiradasbingo.setCellValueFactory(new PropertyValueFactory("tiradasbingo"));
        this.colcomentario.setCellValueFactory(new PropertyValueFactory("comentario"));

        getTablaRecords("SELECT * FROM bingofx ORDER BY numpartida DESC");
        btnVerHistorial.setDisable(true);
    }

    public void getTablaRecords(String sql){
        try{
            // CONEXION A LA BASE DE DATOS. SI HAY ALGUN ERROR, LO CAPTARA LA EXCEPCION
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/devra1?serverTimezone=UTC","devra96","n6CKKs8GUz");
            // PRUEBAS EN CLASE
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingofx?serverTimezone=UTC","root","root");
            System.out.println("Conexion al servidor establecida correctamente.");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                int numpartida = rs.getInt("numpartida");
                String nombrejugador = rs.getString("nombrejugador");
                String hizolinea = rs.getString("hizolinea");
                int tiradaslinea = rs.getInt("tiradaslinea");
                String hizobingo = rs.getString("hizobingo");
                int tiradasbingo = rs.getInt("tiradasbingo");
                String comentario = rs.getString("comentario");
                Registro r = new Registro(numpartida,nombrejugador,hizolinea,tiradaslinea,hizobingo,tiradasbingo,comentario);
                registros.add(r);
                tabla.setItems(registros);
            }
            con.close();
        }
        catch(SQLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setHeaderText("HA OCURRIDO UN ERROR CON LA BASE DE DATOS");
            a.setContentText("Causa del error: " + e.getErrorCode() + " - " + e.getMessage());
            a.showAndWait();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
