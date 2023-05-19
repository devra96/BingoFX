package com.bingofx.bingofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class JuegoUnJugadorController implements Initializable {

    @FXML
    private Button btngenerar;

    @FXML
    private Label c1x1;

    @FXML
    private Label c1x2;

    @FXML
    private Label c1x3;

    @FXML
    private Label c1x4;

    @FXML
    private Label c1x5;

    @FXML
    private Label c1x6;

    @FXML
    private Label c1x7;

    @FXML
    private Label c1x8;

    @FXML
    private Label c1x9;

    @FXML
    private Label c2x1;

    @FXML
    private Label c2x2;

    @FXML
    private Label c2x3;

    @FXML
    private Label c2x4;

    @FXML
    private Label c2x5;

    @FXML
    private Label c2x6;

    @FXML
    private Label c2x7;

    @FXML
    private Label c2x8;

    @FXML
    private Label c2x9;

    @FXML
    private Label c3x1;

    @FXML
    private Label c3x2;

    @FXML
    private Label c3x3;

    @FXML
    private Label c3x4;

    @FXML
    private Label c3x5;

    @FXML
    private Label c3x6;

    @FXML
    private Label c3x7;

    @FXML
    private Label c3x8;

    @FXML
    private Label c3x9;

    @FXML
    private Pane cartonJugador;

    private String[][] carton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        c1x1.setId("hueco");
//        c1x2.setId("");
//        c1x3.setId("");
//        c1x4.setId("");
//        c1x5.setId("");
//        c1x6.setId("");
//        c1x7.setId("");
//        c1x8.setId("");
//        c1x9.setId("");
//
//        c2x1.setId("");
//        c2x2.setId("");
//        c2x3.setId("");
//        c2x4.setId("");
//        c2x5.setId("");
//        c2x6.setId("");
//        c2x7.setId("");
//        c2x8.setId("");
//        c2x9.setId("");
//
//        c3x1.setId("");
//        c3x2.setId("");
//        c3x3.setId("");
//        c3x4.setId("");
//        c3x5.setId("");
//        c3x6.setId("");
//        c3x7.setId("");
//        c3x8.setId("");
//        c3x9.setId("");
        boolean cartongenerado = false;

        do{
            carton = new String[3][9];
            generarHuecosCarton(carton);
            cartongenerado = comprobarHuecosCarton(carton);
        }while(!cartongenerado);
    }

    @FXML
    void MarcarNumero(MouseEvent event) {
        Node source = (Node) event.getSource();
        if(!source.getId().equals("hueco")){
            if(!source.getId().equals("pulsado")){
                source.setId("pulsado");
            }
            else{
                source.setId("");
            }
        }
    }

    @FXML
    void generar(ActionEvent event) {
//        int x = 1;
//        int y = 1;
//        Object a = "c1x1";
//        Object c = c1x1;
//        Node source = (Node) c;
//
//        if(c1x1.getId().equals("c" + x + "x" + y)){
//            System.out.println(c1x1.getId());
//        }
//
//        System.out.println(c1x1.getId());
//        generarHuecosCarton(carton);
        int x = 0;
        for(int i=0;i<4;i++){
            x = (int)(Math.random() * 9);
            if(x==9){
                System.out.println(x);
            }
        }
    }

    public void generarHuecosCarton(String[][] carton){
        int[] huecos = new int[4];
        int hueco;
        boolean repe;

        // INTRODUCIR 0´s EN TODO EL CARTON

        for(int i=0;i<carton.length;i++){
            for(int j=0;j<carton[i].length;j++){
                carton[i][j] = "0";
            }
        }

        // INTRODUCIR HUECOS EN INDICES GENERADOS ALEATORIAMENTE

        for(int i=0;i<carton.length;i++){
            for(int k=0;k<4;k++){
                do{
                    repe = false;
                    hueco = (int)(Math.random() * 9);
                    for(int j=0;j<huecos.length;j++){
                        if(huecos[j] == hueco){
                            repe = true;
                        }
                    }
                }while(repe);
                huecos[k] = hueco;
            }
            // INTRODUCIR ASTERISCOS EN HUECOS

            for(int j=0;j<4;j++){
                carton[i][huecos[j]] = "*";

                if(c1x1.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x1.setId("hueco");
                }
                if(c1x2.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x2.setId("hueco");
                }
                if(c1x3.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x3.setId("hueco");
                }
                if(c1x4.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x4.setId("hueco");
                }
                if(c1x5.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x5.setId("hueco");
                }
                if(c1x6.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x6.setId("hueco");
                }
                if(c1x7.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x7.setId("hueco");
                }
                if(c1x8.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x8.setId("hueco");
                }
                if(c1x9.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c1x9.setId("hueco");
                }

                if(c2x1.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x1.setId("hueco");
                }
                if(c2x2.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x2.setId("hueco");
                }
                if(c2x3.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x3.setId("hueco");
                }
                if(c2x4.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x4.setId("hueco");
                }
                if(c2x5.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x5.setId("hueco");
                }
                if(c2x6.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x6.setId("hueco");
                }
                if(c2x7.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x7.setId("hueco");
                }
                if(c2x8.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x8.setId("hueco");
                }
                if(c2x9.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c2x9.setId("hueco");
                }

                if(c3x1.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x1.setId("hueco");
                }
                if(c3x2.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x2.setId("hueco");
                }
                if(c3x3.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x3.setId("hueco");
                }
                if(c3x4.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x4.setId("hueco");
                }
                if(c3x5.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x5.setId("hueco");
                }
                if(c3x6.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x6.setId("hueco");
                }
                if(c3x7.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x7.setId("hueco");
                }
                if(c3x8.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x8.setId("hueco");
                }
                if(c3x9.getId().equals("c" + (i+1) + "x" + huecos[j])){
                    c3x9.setId("hueco");
                }
            }
        }
    }

    public boolean comprobarHuecosCarton(String[][] carton){
        int cn;     // CONTADOR NUMEROS
        int ch;     // CONTADOR HUECOS

        for(int i=0;i<carton[0].length;i++){
            cn = 0;
            ch = 0;
            for(int j=0;j<carton.length;j++){
                if(carton[j][i].equals("0")){
                    cn++;
                }

                if(carton[j][i].equals("*")){
                    ch++;
                }
            }

            if(cn == 3 || ch == 3){
                return false;
            }
        }
        return true;
    }

    public void quitarIdsCasillas(){


    }
}

