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
import java.util.Arrays;
import java.util.ResourceBundle;

public class JuegoUnJugadorController implements Initializable {

    @FXML
    private Button btngenerar;

    //CELDAS CARTON JUGADOR
    @FXML
    private Label
            c1x1,c1x2,c1x3,c1x4,c1x5,c1x6,c1x7,c1x8,c1x9,
            c2x1,c2x2,c2x3,c2x4,c2x5,c2x6,c2x7,c2x8,c2x9,
            c3x1,c3x2,c3x3,c3x4,c3x5,c3x6,c3x7,c3x8,c3x9;

    //CELDAS CARTON MAQUINA
    @FXML
    private Label
            c1x11,c1x21,c1x31,c1x41,c1x51,c1x61,c1x71,c1x81,c1x91,
            c2x11,c2x21,c2x31,c2x41,c2x51,c2x61,c2x71,c2x81,c2x91,
            c3x11,c3x21,c3x31,c3x41,c3x51,c3x61,c3x71,c3x81,c3x91;

    @FXML
    private Pane cartonJugador,cartonJugador2;

    private Label[][] carton1;
    private Label[][] carton2;

    boolean cartongenerado;

    int[] numerospronunciados;

    int indicenumerospronunciados;

    int contadorlinea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // QUITAMOS HUECOS A TODAS LAS CASILLAS
        quitarIdsCasillas();

        // CARTON JUGADOR CON HUECOS
        cartongenerado = false;
        do{
            carton1 = new Label[][]{
                    {c1x1, c1x2, c1x3, c1x4, c1x5, c1x6, c1x7, c1x8, c1x9},
                    {c2x1, c2x2, c2x3, c2x4, c2x5, c2x6, c2x7, c2x8, c2x9},
                    {c3x1, c3x2, c3x3, c3x4, c3x5, c3x6, c3x7, c3x8, c3x9}
            };
            generarHuecosCarton(carton1);
            cartongenerado = comprobarHuecosCarton(carton1);
        }while(!cartongenerado);
        // -------------------------------------------------------------------------------------------------------------

        // CARTON MAQUINA CON HUECOS
        cartongenerado = false;
        do{
            carton2 = new Label[][]{
                    {c1x11, c1x21, c1x31, c1x41, c1x51, c1x61, c1x71, c1x81, c1x91},
                    {c2x11, c2x21, c2x31, c2x41, c2x51, c2x61, c2x71, c2x81, c2x91},
                    {c3x11, c3x21, c3x31, c3x41, c3x51, c3x61, c3x71, c3x81, c3x91}
            };
            generarHuecosCarton(carton2);
            cartongenerado = comprobarHuecosCarton(carton2);
        }while(!cartongenerado);
        // -------------------------------------------------------------------------------------------------------------

        // GENERAMOS NUMEROS PARA AMBOS CARTONES
        generarNumerosCarton(carton1);
        generarNumerosCarton(carton2);

        numerospronunciados = new int[90];
        indicenumerospronunciados = 0;
        contadorlinea = 0;

        // HACER HILO EN BUCLE

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
//        int x = 0;
//        for(int i=0;i<4;i++){
//            x = (int)(Math.random() * 9);
//            if(x==9){
//                System.out.println(x);
//            }
//        }

        cartongenerado = false;

        do{
            quitarIdsCasillas();
            carton1 = new Label[][]{
                    {c1x1, c1x2, c1x3, c1x4, c1x5, c1x6, c1x7, c1x8, c1x9},
                    {c2x1, c2x2, c2x3, c2x4, c2x5, c2x6, c2x7, c2x8, c2x9},
                    {c3x1, c3x2, c3x3, c3x4, c3x5, c3x6, c3x7, c3x8, c3x9}
            };
            generarHuecosCarton(carton1);
            cartongenerado = comprobarHuecosCarton(carton1);
        }while(!cartongenerado);
        generarNumerosCarton(carton1);

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

    public void generarHuecosCarton(Label[][] carton){
        int[] huecos = new int[4];
        int hueco;
        boolean repe;

        // INTRODUCIR 0´s EN TODO EL CARTON

        for(int i=0;i<carton.length;i++){
            for(int j=0;j<carton[i].length;j++){
                carton[i][j].setText("0");
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
                carton[i][huecos[j]].setId("hueco");
                carton[i][huecos[j]].setText("");
            }
        }
    }

    public boolean comprobarHuecosCarton(Label[][] carton){
        int cn;     // CONTADOR NUMEROS
        int ch;     // CONTADOR HUECOS

        for(int i=0;i<carton[0].length;i++){
            cn = 0;
            ch = 0;
            for(int j=0;j<carton.length;j++){
                if(carton[j][i].getText().equals("")){
                    cn++;
                }

                if(carton[j][i].getId().equals("hueco")){
                    ch++;
                }
            }

            if(cn == 3 || ch == 3){
                return false;
            }
        }
        return true;
    }

    public static void generarNumerosCarton(Label[][] carton){
        int ini = 1;
        int fin = 10;
        int indicenumeros = 0;
        int numeroaleatorio;
        String strnumero;
        boolean repe;
        int[] numeros = new int[15];
        String[] strnumeros = new String[15];

        /**
         * Generar numeros aleatorios del 1 al 10, del 11 al 20 y asi
         * sucesivamente, comprobar que no se repiten e introducirlos en
         * un array de numeros
         */
        for(int i=0;i<carton[0].length;i++){
            for(int j=0;j<carton.length;j++){
                if(carton[j][i].getText().equals("0")){
                    do{
                        repe = false;
                        numeroaleatorio = (int) (Math.random() * (fin - ini + 1) + ini);
                        for(int k=0;k<numeros.length;k++){
                            if(numeros[k] == numeroaleatorio){
                                repe = true;
                            }
                        }
                    }while(repe);
                    numeros[indicenumeros] = numeroaleatorio;
                    indicenumeros++;
                }
            }
            ini+=10;
            fin+=10;
        }

        /**
         * Ordenamos los numeros de menor a mayor, los convertimos a String
         * y los metemos en un array de Strings
         */
        Arrays.sort(numeros);
        for(int i=0;i<numeros.length;i++){
            strnumero = Integer.toString(numeros[i]);
            strnumeros[i] = strnumero;
        }


        /**
         * Introducimos los numeros en el carton
         */
        indicenumeros = 0;
        for(int i=0;i<carton[0].length;i++){
            for(int j=0;j<carton.length;j++){
                if(carton[j][i].getText().equals("0")){
                    carton[j][i].setText(strnumeros[indicenumeros]);
                    indicenumeros++;
                }
            }
        }
    }

    public void quitarIdsCasillas(){
        //CARTON JUGADOR
        c1x1.setId("");c1x2.setId("");c1x3.setId("");c1x4.setId("");c1x5.setId("");c1x6.setId("");c1x7.setId("");c1x8.setId("");c1x9.setId("");
        c2x1.setId("");c2x2.setId("");c2x3.setId("");c2x4.setId("");c2x5.setId("");c2x6.setId("");c2x7.setId("");c2x8.setId("");c2x9.setId("");
        c3x1.setId("");c3x2.setId("");c3x3.setId("");c3x4.setId("");c3x5.setId("");c3x6.setId("");c3x7.setId("");c3x8.setId("");c3x9.setId("");

        //CARTON MAQUINA
        c1x11.setId("");c1x21.setId("");c1x31.setId("");c1x41.setId("");c1x51.setId("");c1x61.setId("");c1x71.setId("");c1x81.setId("");c1x91.setId("");
        c2x11.setId("");c2x21.setId("");c2x31.setId("");c2x41.setId("");c2x51.setId("");c2x61.setId("");c2x71.setId("");c2x81.setId("");c2x91.setId("");
        c3x11.setId("");c3x21.setId("");c3x31.setId("");c3x41.setId("");c3x51.setId("");c3x61.setId("");c3x71.setId("");c3x81.setId("");c3x91.setId("");
    }
}