package com.bingofx.bingofx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JuegoUnJugadorController implements Initializable {

    @FXML
    private Button btnVolverMenuInicio;

    @FXML
    private Button pausar;

    @FXML
    private Button reanudar;

    @FXML
    private Button btnsacarbola;

    @FXML
    private Button btnBingo;

    @FXML
    private Button btnLinea;

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

    //CELDAS NUMEROS PRONUNCIADOS
    @FXML
    private Label
            p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,
            p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,
            p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,
            p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,
            p41,p42,p43,p44,p45,p46,p47,p48,p49,p50,
            p51,p52,p53,p54,p55,p56,p57,p58,p59,p60,
            p61,p62,p63,p64,p65,p66,p67,p68,p69,p70,
            p71,p72,p73,p74,p75,p76,p77,p78,p79,p80,
            p81,p82,p83,p84,p85,p86,p87,p88,p89,p90;

    @FXML
    private Pane cartonJugador,cartonJugador2,NumerosPronunciados;

    @FXML
    private Label marcador;

    @FXML
    private Label fraseLineaBingo;

    @FXML
    private Label fraseLineaBingoMaquina;

    @FXML
    private Label txtNombreJugador;

    @FXML
    private HBox filaBolas;

    @FXML
    private Label txtBola1;

    @FXML
    private Label txtBola2;

    @FXML
    private Label txtBola3;

    @FXML
    private Label txtBola4;

    @FXML
    private Label txtBola5;

    @FXML
    private Label txtBola6;

    @FXML
    private Label txtContadorBolas;

    private Label[][] carton1;
    private Label[][] carton2;

    private Label[] pronunciados;

    private boolean cartongenerado;

    private int[] numerospronunciados;

    private int indicenumerospronunciados;

    static int contadorlinea;
    static int contadorbingo;
    static String hizolinea;
    static String hizobingo;

    private boolean maquinatienelinea;

    private volatile boolean pausado = false;
    private ScheduledExecutorService executor;

    private Pantalla pantalla = new Pantalla();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // CARTON JUGADOR CON HUECOS
        do{
            carton1 = new Label[][]{
                    {c1x1, c1x2, c1x3, c1x4, c1x5, c1x6, c1x7, c1x8, c1x9},
                    {c2x1, c2x2, c2x3, c2x4, c2x5, c2x6, c2x7, c2x8, c2x9},
                    {c3x1, c3x2, c3x3, c3x4, c3x5, c3x6, c3x7, c3x8, c3x9}
            };
            // QUITAMOS HUECOS A TODAS LAS CASILLAS
            QuitarIdsCasillas(carton1);
            GenerarHuecosCarton(carton1);
            cartongenerado = ComprobarHuecosCarton(carton1);
            System.out.println("BUCLE INFINITO JUGADOR???");
        }while(!cartongenerado);

        // -------------------------------------------------------------------------------------------------------------

        // CARTON MAQUINA CON HUECOS
        do{
            carton2 = new Label[][]{
                    {c1x11, c1x21, c1x31, c1x41, c1x51, c1x61, c1x71, c1x81, c1x91},
                    {c2x11, c2x21, c2x31, c2x41, c2x51, c2x61, c2x71, c2x81, c2x91},
                    {c3x11, c3x21, c3x31, c3x41, c3x51, c3x61, c3x71, c3x81, c3x91}
            };
            // QUITAMOS HUECOS A TODAS LAS CASILLAS
            QuitarIdsCasillas(carton2);
            GenerarHuecosCarton(carton2);
            cartongenerado = ComprobarHuecosCarton(carton2);
            System.out.println("BUCLE INFINITO MAQUINA???");
        }while(!cartongenerado);

        // -------------------------------------------------------------------------------------------------------------

        // GENERAMOS NUMEROS PARA AMBOS CARTONES
        GenerarNumerosCarton(carton1);
        GenerarNumerosCarton(carton2);

        numerospronunciados = new int[90];
        indicenumerospronunciados = 0;
        pronunciados = new Label[]{
                p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,
                p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,
                p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,
                p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,
                p41,p42,p43,p44,p45,p46,p47,p48,p49,p50,
                p51,p52,p53,p54,p55,p56,p57,p58,p59,p60,
                p61,p62,p63,p64,p65,p66,p67,p68,p69,p70,
                p71,p72,p73,p74,p75,p76,p77,p78,p79,p80,
                p81,p82,p83,p84,p85,p86,p87,p88,p89,p90
        };

        hizolinea = "No";
        hizobingo = "No";
        maquinatienelinea = false;
        txtNombreJugador.setText(MenuUnJugadorController.nombrejugador);

        txtBola1.setOpacity(0);
        txtBola2.setOpacity(0);
        txtBola3.setOpacity(0);
        txtBola4.setOpacity(0);
        txtBola5.setOpacity(0);
        txtBola6.setOpacity(0);

        txtContadorBolas.setText("Bola: " + indicenumerospronunciados + "/90");

        btnsacarbola.setDisable(true);
        reanudar.setDisable(true);

        // -------------------------------------------------------------------------------------------------------------

        executor = Executors.newScheduledThreadPool(1);
        Runnable tarea = () -> {
            if (!pausado) {
                SacarBola();
            }
        };
        executor.scheduleAtFixedRate(tarea, 0, 5, TimeUnit.SECONDS);
    }

    private void pausarHilo() {
        pausado = true;
        btnsacarbola.setDisable(false);

        pausar.setDisable(true);
        reanudar.setDisable(false);

        System.out.println("Hilo pausado");
    }

    private void reanudarHilo() {
        pausado = false;
        btnsacarbola.setDisable(true);

        pausar.setDisable(false);
        reanudar.setDisable(true);
        System.out.println("Hilo reanudado");
    }

//    @FXML
//    void generar(ActionEvent event) {
//        SacarBola();
//    }

    @FXML
    void pausar(ActionEvent event) {
        pausarHilo();
    }

    @FXML
    void reanudar(ActionEvent event) {
        reanudarHilo();
    }

    public void SacarBola(){
        SacarNumero(carton2,pronunciados,numerospronunciados,indicenumerospronunciados);
        indicenumerospronunciados++;

        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                fraseLineaBingo.setText("");
                txtContadorBolas.setText("Bola: " + indicenumerospronunciados + "/90");
            }
        });

        if(!maquinatienelinea){
            if(comprobarLineaMaquina(carton2)){
                maquinatienelinea = true;
            }
        }

        if(comprobarBingoMaquina(carton2)){
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    BingoCorrecto();
                }
            });
        }
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
    void CantarBingo(ActionEvent event) {
        CantarBingoJugador(carton1);
    }

    @FXML
    void CantarLinea(ActionEvent event) {
        switch(CantarLineaJugador(carton1)){
            //NO HAY NINGUNA LINEA MARCADA
            case 0:
                fraseLineaBingo.setTextFill(Color.RED);
                fraseLineaBingo.setText("¡No tienes ninguna linea entera marcada!");
            break;
            //TODAS LAS LINEAS SON ERRONEAS
            case 1:
                fraseLineaBingo.setTextFill(Color.RED);
                fraseLineaBingo.setText("¡Linea(s) incorrecta(s)!");
            break;
            //ENCUENTRA UNA LINEA CORRECTA
            case 2:
                fraseLineaBingo.setTextFill(Color.GREEN);
                fraseLineaBingo.setText("¡HAS HECHO LINEA!");
                btnLinea.setDisable(true);
                fraseLineaBingoMaquina.setOpacity(0);
                contadorlinea = indicenumerospronunciados;
                hizolinea = "Si";
            break;
        }
    }

    public void GenerarHuecosCarton(Label[][] carton){
        int[] huecos;
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
            huecos = new int[]{11,11,11,11};
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

    public boolean ComprobarHuecosCarton(Label[][] carton){
        int cn = 0;     // CONTADOR NUMEROS
        int ch = 0;     // CONTADOR HUECOS

        for(int i=0;i<carton[0].length;i++){
            cn = 0;
            ch = 0;
            for(int j=0;j<carton.length;j++){
                if(carton[j][i].getText().equals("0")){
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

    public static void GenerarNumerosCarton(Label[][] carton){
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

    public void SacarNumero(Label[][] carton2, Label[] pronunciados, int[] numerospronunciados, int indicenumerospronunciados){
        int numeroaleatorio;
        boolean repe;

        /**
         * Generamos un numero del 1 al 90, comprobamos que no se repita y lo
         * introducimos en el array "numerospronunciados"
         */
        do{
            repe = false;
            numeroaleatorio = (int) (Math.random() * (90 - 1 + 1) + 1);
            for (int i = 0; i < numerospronunciados.length; i++) {
                if (numerospronunciados[i] == numeroaleatorio) {
                    repe = true;
                }
            }
        }
        while(repe);
        numerospronunciados[indicenumerospronunciados] = numeroaleatorio;

        // CODIGO PARA QUE EL HILO PUEDA MODIFICAR EL NUMERO DEL MARCADOR
        int finalNumeroaleatorio = numeroaleatorio;
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                marcador.setText(Integer.toString(finalNumeroaleatorio));
            }
        });

        //BOLAS
        switch(indicenumerospronunciados){
            case 0:
                txtBola1.setOpacity(1);
                txtBola1.setText(Integer.toString(numerospronunciados[indicenumerospronunciados]));
            break;
            case 1:
                txtBola2.setOpacity(1);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        txtBola1.setText(Integer.toString(numerospronunciados[indicenumerospronunciados]));
                        txtBola2.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-1]));
                    }
                });
            break;
            case 2:
                txtBola3.setOpacity(1);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        txtBola1.setText(Integer.toString(numerospronunciados[indicenumerospronunciados]));
                        txtBola2.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-1]));
                        txtBola3.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-2]));
                    }
                });
            break;
            case 3:
                txtBola4.setOpacity(1);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        txtBola1.setText(Integer.toString(numerospronunciados[indicenumerospronunciados]));
                        txtBola2.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-1]));
                        txtBola3.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-2]));
                        txtBola4.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-3]));
                    }
                });
            break;
            case 4:
                txtBola5.setOpacity(1);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        txtBola1.setText(Integer.toString(numerospronunciados[indicenumerospronunciados]));
                        txtBola2.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-1]));
                        txtBola3.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-2]));
                        txtBola4.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-3]));
                        txtBola5.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-4]));
                    }
                });
            break;
            case 5:
                txtBola6.setOpacity(1);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        txtBola1.setText(Integer.toString(numerospronunciados[indicenumerospronunciados]));
                        txtBola2.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-1]));
                        txtBola3.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-2]));
                        txtBola4.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-3]));
                        txtBola5.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-4]));
                        txtBola6.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-5]));
                    }
                });
            break;
            default:
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        txtBola1.setText(Integer.toString(numerospronunciados[indicenumerospronunciados]));
                        txtBola2.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-1]));
                        txtBola3.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-2]));
                        txtBola4.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-3]));
                        txtBola5.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-4]));
                        txtBola6.setText(Integer.toString(numerospronunciados[indicenumerospronunciados-5]));
                    }
                });
            break;
        }

        for(int i=0;i<pronunciados.length;i++){
            if(pronunciados[i].getText().equals(Integer.toString(numeroaleatorio))){
                pronunciados[i].setId("pronunciado");
            }
        }

        //PRUEBAS CONSOLA
        System.out.println();
        System.out.println("LA MAQUINA SACA EL " + numeroaleatorio);
        System.out.println();

        /**
         * Comprobamos si el numero esta en el carton e imprimimos un mensaje
         * concreto dependiendo de si el numero esta o no
         */
        for(int i=0;i<carton2.length;i++){
            for(int j=0;j<carton2[i].length;j++){
                if(carton2[i][j].getText().equals(Integer.toString(numeroaleatorio))) {
                    carton2[i][j].setId("acertadoMaquina");
                }
            }
        }
    }

    public void QuitarIdsCasillas(Label[][] carton){
        for(int i=0;i<carton.length;i++){
            for(int j=0;j<carton[i].length;j++){
                carton[i][j].setId("");
            }
        }
    }

    public int CantarLineaJugador(Label[][] carton1){
        int c;
        String[] numerosmarcados;
        boolean linea = false;

        /**
         * Recorremos las filas del array y si se encuentra un numero marcado,
         * suma un contador.
         * Si cuenta cinco numeros = true
         * En caso contrario = false
         */

        //BUCLE QUE RECORRE LAS 3 FILAS DEL CARTON
        for(int i=0;i<carton1.length;i++){
            c = 0;
            numerosmarcados = new String[5];
            //BUCLE QUE RECORRE LA FILA QUE TOQUE EN BUSCA DE UNA LINEA
            for(int j=0;j<carton1[i].length;j++){
                if(carton1[i][j].getId().equals("pulsado")){
                    numerosmarcados[c] = carton1[i][j].getText();
                    c++;
                }
            }
            //SI HAY LINEA EN UNA FILA
            if(c==5){
                c = 0;
                //COMPROBAMOS SI TODOS LOS NUMEROS QUE HAY EN LA LINEA SE HAN PRONUNCIADO
                for(int k=0;k<5;k++){
                    for(int j=0;j<numerospronunciados.length;j++){
                        if(numerosmarcados[k].equals(Integer.toString(numerospronunciados[j]))){
                            c++;
                        }
                    }
                }
                //SI LA LINEA ES CORRECTA
                if(c==5){
                    return 2;
                }
                //SI LA LINEA ES INCORRECTA
                else{
                    linea = true;
                }
            }
        }

        if(linea){
            return 1;
        }
        else{
            return 0;
        }
    }

    public void CantarBingoJugador(Label[][] carton1){
        int c=0;
        String[] numerosmarcados = new String[15];

        /**
         * Recorremos todo el carton y si se encuentra un numero marcado,
         * suma un contador
         * Si cuenta quince numeros = true
         * En caso contrario = false
         */
        for(int i=0;i<carton1.length;i++){
            for(int j=0;j<carton1[i].length;j++){
                if(carton1[i][j].getId().equals("pulsado")){
                    numerosmarcados[c] = carton1[i][j].getText();
                    c++;
                }
            }
        }

        if(c==15){
            c = 0;
            for(int i=0;i<15;i++){
                for(int j=0;j<numerospronunciados.length;j++){
                    if(numerosmarcados[i].equals(Integer.toString(numerospronunciados[j]))){
                        c++;
                    }
                }
            }

            if(c==15){
                hizobingo = "Si";
                contadorbingo = indicenumerospronunciados;
                BingoCorrecto();
            }
            else{
                fraseLineaBingo.setTextFill(Color.RED);
                fraseLineaBingo.setText("¡El bingo es incorrecto!");
            }
        }
        else{
            fraseLineaBingo.setTextFill(Color.RED);
            fraseLineaBingo.setText("¡No tienes todos los numeros marcados!");
        }
    }

    public boolean comprobarLineaMaquina(Label[][] carton2){
        int c;

        /**
         * Recorremos las filas del array y si se encuentra un numero marcado,
         * suma un contador.
         * Si cuenta cinco numeros = true
         * En caso contrario = false
         */
        //RECORRE LAS FILAS DEL CARTON DE LA MAQUINA
        for(int i=0;i<carton2.length;i++){
            c=0;
            //RECORRE LA FILA QUE TOQUE EN BUSCA DE NUMEROS ACERTADOS
            for(int j=0;j<carton2[i].length;j++){
                if(carton2[i][j].getId().equals("acertadoMaquina")){
                    c++;
                }
            }
            //SI HAY LINEA CORRECTA
            if(c==5){
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        fraseLineaBingoMaquina.setTextFill(Color.GREEN);
                        fraseLineaBingoMaquina.setText("¡LA MAQUINA HA HECHO LINEA!");
                    }
                });
                btnLinea.setDisable(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Recorremos todo el carton y si se encuentra un numero marcado, suma un contador
     * Si cuenta quince numeros = true
     * En caso contrario = false
     */
    public boolean comprobarBingoMaquina(Label[][] carton2){
        int c=0;

        for(int i=0;i<carton2.length;i++){
            for(int j=0;j<carton2[i].length;j++){
                if(carton2[i][j].getId().equals("acertadoMaquina")){
                    c++;
                }
            }
        }

        if(c==15){
            return true;
        }
        return false;
    }

    public void BingoCorrecto(){
        //PRUEBA CONSOLA
        System.out.println("BINGO");

        pantalla.IrBingo();

        // PARAR HILO
        executor.shutdown();

        // CERRAR VENTANA ACTUAL
        pantalla.CerrarVentanaActual();
    }

    @FXML
    void VolverMenuInicio(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("VOLVER AL MENU PRINCIPAL");
        a.setHeaderText("¡AUN HAY UNA PARTIDA EN CURSO!");
        a.setContentText("¿Estas seguro de querer volver al menu principal?");
        Optional<ButtonType> r = a.showAndWait();
        if(r.get() == ButtonType.OK){
            pantalla.CerrarVentanaActual();
            executor.shutdown();
            BingoController.RestaurarRegistros();
            pantalla.IrMenuInicio();
        }
    }
}