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
import javafx.scene.image.ImageView;
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

public class JuegoDosJugadoresController implements Initializable {

    @FXML
    private Button btnVolverMenuInicio;

    @FXML
    private Button pausar;

    @FXML
    private Button reanudar;

    @FXML
    private Button btnsacarbola;

    @FXML
    private Button btnBingo1;

    @FXML
    private Button btnBingo2;

    @FXML
    private Button btnLinea1;

    @FXML
    private Button btnLinea2;

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
    private Label fraseLineaBingo1;

    @FXML
    private Label fraseLineaBingo2;

    @FXML
    private Label txtNombreJugador1;

    @FXML
    private Label txtNombreJugador2;

    @FXML
    private Label txtCantarBingo;

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

    @FXML
    private HBox filaBolas;

    @FXML
    private ImageView imgOkLinea1;

    @FXML
    private ImageView imgOkLinea2;

    //MATRICES DONDE SE GUARDAN LOS NUMEROS DE LOS CARTONES
    private Label[][] carton1;
    private Label[][] carton2;

    //ARRAY QUE GUARDA LOS LABELS DEL RECUADRO DE NUMEROS PRONUNCIADOS
    private Label[] pronunciados;

    //TRUE O FALSE SEGUN SI SE HA GENERADO UN CARTON CORRECTAMENTE
    private boolean cartongenerado;

    //ARRAY QUE GUARDA LOS NUMEROS PRONUNCIADOS EN LA PARTIDA
    private int[] numerospronunciados;

    //INDICE QUE INDICA CUANTOS NUMEROS SE HAN PRONUNCIADO
    private int indicenumerospronunciados;

    //VARIABLES ESTATICAS QUE GUARDAN LOS REGISTROS DE LOS JUGADORES
    static String nombreganador;
    static int contadorlineajugador1;
    static int contadorlineajugador2;
    static int contadorbingojugador1;
    static int contadorbingojugador2;
    static String hizolineajugador1;
    static String hizolineajugador2;
    static String hizobingojugador1;
    static String hizobingojugador2;

    //PARA PAUSAR O REANUDAR EL HILO (MODO MANUAL/AUTOMATICO)
    private volatile boolean pausado = false;

    //OBJETO QUE EJECUTA EL HILO
    private ScheduledExecutorService executor;

    //OBJETO PARA LLAMAR A METODOS PARA CAMBIAR DE PANTALLA
    private Pantalla pantalla = new Pantalla();

    /**
     * Generamos cartones, se comprueba que estan bien hechos y se
     * inicializan algunas variables y el hilo
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // CARTON JUGADOR CON HUECOS
        do{
            carton1 = new Label[][]{
                    {c1x1, c1x2, c1x3, c1x4, c1x5, c1x6, c1x7, c1x8, c1x9},
                    {c2x1, c2x2, c2x3, c2x4, c2x5, c2x6, c2x7, c2x8, c2x9},
                    {c3x1, c3x2, c3x3, c3x4, c3x5, c3x6, c3x7, c3x8, c3x9}
            };
            quitarIdsCasillas(carton1);
            generarHuecosCarton(carton1);
            cartongenerado = comprobarHuecosCarton(carton1);
        }while(!cartongenerado);
        // -------------------------------------------------------------------------------------------------------------

        // CARTON MAQUINA CON HUECOS
        do{
            carton2 = new Label[][]{
                    {c1x11, c1x21, c1x31, c1x41, c1x51, c1x61, c1x71, c1x81, c1x91},
                    {c2x11, c2x21, c2x31, c2x41, c2x51, c2x61, c2x71, c2x81, c2x91},
                    {c3x11, c3x21, c3x31, c3x41, c3x51, c3x61, c3x71, c3x81, c3x91}
            };
            quitarIdsCasillas(carton2);
            generarHuecosCarton(carton2);
            cartongenerado = comprobarHuecosCarton(carton2);
        }while(!cartongenerado);
        // -------------------------------------------------------------------------------------------------------------

        // GENERAMOS NUMEROS PARA AMBOS CARTONES
        generarNumerosCarton(carton1);
        generarNumerosCarton(carton2);


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

        //INICIALIZAMOS VARIABLES ESTATICAS
        contadorlineajugador1 = 0;
        contadorlineajugador2 = 0;
        contadorbingojugador1 = 0;
        contadorbingojugador2 = 0;
        hizolineajugador1 = "No";
        hizolineajugador2 = "No";
        hizobingojugador1 = "No";
        hizobingojugador2 = "No";
        txtNombreJugador1.setText(MenuDosJugadoresController.nombrejugador1);
        txtNombreJugador2.setText(MenuDosJugadoresController.nombrejugador2);

        //OCULTAMOS LAS BOLAS PRONUNCIADAS (FILA DE BOLAS)
        txtBola1.setOpacity(0);
        txtBola2.setOpacity(0);
        txtBola3.setOpacity(0);
        txtBola4.setOpacity(0);
        txtBola5.setOpacity(0);
        txtBola6.setOpacity(0);
        imgOkLinea1.setOpacity(0);
        imgOkLinea2.setOpacity(0);

        //INICIALIZAMOS EL TEXTO DEL CONTADOR DE BOLAS
        txtContadorBolas.setText("Bola: " + indicenumerospronunciados + "/90");

        // BOTON SACAR BOLA Y MODO AUTOMATICO DESACTIVADOS
        btnsacarbola.setDisable(true);
        reanudar.setDisable(true);

        //INICIALIZAMOS HILO QUE SACARA UN NUMERO CADA 5 SEGUNDOS (MODO AUTOMATICO)
        executor = Executors.newScheduledThreadPool(1);
        Runnable tarea = () -> {
            if (!pausado) {
                SacarBola();
            }
        };
        executor.scheduleAtFixedRate(tarea, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * Pausa el hilo (activa el modo manual)
     */
    private void pausarHilo() {
        pausado = true;
        btnsacarbola.setDisable(false);

        pausar.setDisable(true);
        reanudar.setDisable(false);

        System.out.println("Hilo pausado");
    }

    /**
     * Reanuda el hilo (activa el modo automatico)
     */
    private void reanudarHilo() {
        pausado = false;
        btnsacarbola.setDisable(true);

        pausar.setDisable(false);
        reanudar.setDisable(true);

        System.out.println("Hilo reanudado");
    }

    /**
     * Boton modo manual
     * @param event
     */
    @FXML
    void pausar(ActionEvent event) {
        pausarHilo();
    }

    /**
     * Boton modo automatico
     * @param event
     */
    @FXML
    void reanudar(ActionEvent event) {
        reanudarHilo();
    }

    /**
     * Comprueba si han salido todos los numeros, saca un numero
     * aleatorio, cuenta uno mas al contador,
     */
    public void SacarBola(){
        if(indicenumerospronunciados == 89){
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    txtCantarBingo.setText("¡ALGUIEN TIENE QUE CANTAR BINGO!");
                }
            });
            btnsacarbola.setDisable(true);
            pausar.setDisable(true);
            reanudar.setDisable(true);
            executor.shutdown();
        }

        sacarNumero(pronunciados,numerospronunciados,indicenumerospronunciados);
        indicenumerospronunciados++;

        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                fraseLineaBingo1.setText("");
                fraseLineaBingo2.setText("");
                txtContadorBolas.setText("Bola: " + indicenumerospronunciados + "/90");
            }
        });
    }

    /**
     * Al pulsar los numeros del carton se marcan
     * @param event
     */
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

    /**
     * Boton de cantar linea del jugador 1 que segun si la linea
     * es correcta o no, muestra un mensaje u otro.
     * @param event
     */
    @FXML
    void CantarLinea1(ActionEvent event) {
        switch(cantarLineaJugador(carton1)){
            //NO HAY NINGUNA LINEA MARCADA
            case 0:
                fraseLineaBingo1.setTextFill(Color.RED);
                fraseLineaBingo1.setText("¡No tienes ninguna linea entera marcada!");
            break;
            //TODAS LAS LINEAS SON ERRONEAS
            case 1:
                fraseLineaBingo1.setTextFill(Color.RED);
                fraseLineaBingo1.setText("¡Linea(s) incorrecta(s)!");
            break;
            //ENCUENTRA UNA LINEA CORRECTA
            case 2:
                hizolineajugador1 = "Si";
                contadorlineajugador1 = indicenumerospronunciados;
                fraseLineaBingo1.setTextFill(Color.GREEN);
                fraseLineaBingo1.setText("¡HAS HECHO LINEA!");
                btnLinea1.setDisable(true);
                btnLinea2.setDisable(true);
                imgOkLinea1.setOpacity(1);
            break;
        }
    }

    /**
     * Boton de cantar linea del jugador 2 que segun si la linea
     * es correcta o no, muestra un mensaje u otro.
     * @param event
     */
    @FXML
    void CantarLinea2(ActionEvent event) {
        switch(cantarLineaJugador(carton2)){
            //NO HAY NINGUNA LINEA MARCADA
            case 0:
                fraseLineaBingo2.setTextFill(Color.RED);
                fraseLineaBingo2.setText("¡No tienes ninguna linea entera marcada!");
            break;
            //TODAS LAS LINEAS SON ERRONEAS
            case 1:
                fraseLineaBingo2.setTextFill(Color.RED);
                fraseLineaBingo2.setText("¡Linea(s) incorrecta(s)!");
            break;
            //ENCUENTRA UNA LINEA CORRECTA
            case 2:
                hizolineajugador2 = "Si";
                contadorlineajugador2 = indicenumerospronunciados;
                fraseLineaBingo2.setTextFill(Color.GREEN);
                fraseLineaBingo2.setText("¡HAS HECHO LINEA!");
                btnLinea1.setDisable(true);
                btnLinea2.setDisable(true);
                imgOkLinea2.setOpacity(1);
            break;
        }
    }

    /**
     * Boton de cantar bingo del jugador 1 que segun si el bingo
     * es correcto o no, se muestra un mensaje o se va a la pantalla
     * de bingo
     * @param event
     */
    @FXML
    void CantarBingo1(ActionEvent event) {
        switch(cantarBingoJugador(carton1)){
            //NO ESTAN TODOS LOS NUMEROS DEL CARTON MARCADOS
            case 0:
                fraseLineaBingo1.setTextFill(Color.RED);
                fraseLineaBingo1.setText("¡No tienes todos los numeros marcados!");
            break;
            //EL BINGO ES INCORRECTO
            case 1:
                fraseLineaBingo1.setTextFill(Color.RED);
                fraseLineaBingo1.setText("¡El bingo es incorrecto!");
            break;
            //EL BINGO ES CORRECTO
            case 2:
                nombreganador = MenuDosJugadoresController.nombrejugador1;
                hizobingojugador1 = "Si";
                contadorbingojugador1 = indicenumerospronunciados;
                BingoCorrecto();
            break;
        }
    }

    /**
     * Boton de cantar bingo del jugador 2 que segun si el bingo
     * es correcto o no, se muestra un mensaje o se va a la pantalla
     * de bingo
     * @param event
     */
    @FXML
    void CantarBingo2(ActionEvent event) {
        switch(cantarBingoJugador(carton2)){
            //NO ESTAN TODOS LOS NUMEROS DEL CARTON MARCADOS
            case 0:
                fraseLineaBingo2.setTextFill(Color.RED);
                fraseLineaBingo2.setText("¡No tienes todos los numeros marcados!");
            break;
            //EL BINGO ES INCORRECTO
            case 1:
                fraseLineaBingo2.setTextFill(Color.RED);
                fraseLineaBingo2.setText("¡El bingo es incorrecto!");
            break;
            //EL BINGO ES CORRECTO
            case 2:
                nombreganador = MenuDosJugadoresController.nombrejugador2;
                hizobingojugador2 = "Si";
                contadorbingojugador2 = indicenumerospronunciados;
                BingoCorrecto();
            break;
        }
    }

    /**
     * Metodo que genera los 4 huecos en las 3 filas del carton
     * @param carton
     */
    public void generarHuecosCarton(Label[][] carton){
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

    /**
     * Metodo que comprueba si los huecos generados para el carton
     * se repiten o no
     * @param carton
     * @return true si se repiten, false si no se repiten
     */
    public boolean comprobarHuecosCarton(Label[][] carton){
        int cn;     // CONTADOR NUMEROS
        int ch;     // CONTADOR HUECOS

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

    /**
     * Metodo que genera los 15 numeros del carton de forma aleatoria
     * y los coloca en sus posiciones
     * @param carton
     */
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

    /**
     * Metodo que saca un numero aleatorio del 1 al 90 que aun no haya
     * salido
     * @param pronunciados Array de los labels del cartel de numeros pronunciados en la partida
     * @param numerospronunciados Array de los numeros pronunciados en la partida
     * @param indicenumerospronunciados Contador de la cantidad de numeros pronunciados en la partida
     */
    public void sacarNumero(Label[] pronunciados, int[] numerospronunciados, int indicenumerospronunciados){
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


        int finalNumeroaleatorio = numeroaleatorio;
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                marcador.setText(Integer.toString(finalNumeroaleatorio));
            }
        });

        // MARCA LOS NUMEROS DEL PANEL DE NUMEROS PRONUNCIADOS
        for(int i=0;i<pronunciados.length;i++){
            if(pronunciados[i].getText().equals(Integer.toString(numeroaleatorio))){
                pronunciados[i].setId("pronunciado");
            }
        }

        // FILA BOLAS > IR MOSTRANDO LAS BOLAS AL PRINCIPIO SEGUN SE SACAN LOS PRIMEROS NUMEROS
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

        //PRUEBAS CONSOLA
        System.out.println();
        System.out.println("LA MAQUINA SACA EL " + numeroaleatorio);
        System.out.println();
    }

    /**
     * Metodo que sirve para, cuando se genera un "carton incorrecto", quite
     * todos los huecos del carton
     * @param carton
     */
    public void quitarIdsCasillas(Label[][] carton){
        for(int i=0;i<carton.length;i++){
            for(int j=0;j<carton[i].length;j++){
                carton[i][j].setId("");
            }
        }
    }

    /**
     * Metodo que comprueba la linea cantada del jugador
     * @param carton
     * @return 0 (no estan todos los numeros marcados), 1 (linea(s) incorrecta(s)), 2 (linea correcta)
     */
    public int cantarLineaJugador(Label[][] carton){
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
        for(int i=0;i<carton.length;i++){
            c=0;
            numerosmarcados = new String[5];
            //BUCLE QUE RECORRE LA FILA QUE TOQUE EN BUSCA DE UNA LINEA
            for(int j=0;j<carton[i].length;j++){
                if(carton[i][j].getId().equals("pulsado")){
                    numerosmarcados[c] = carton[i][j].getText();
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

    /**
     * Metodo que comprueba el bingo cantado del jugador
     * @param carton
     * @return 0 (no estan todos los numeros marcados), 1 (bingo incorrecto), 2 (bingo correcto)
     */
    public int cantarBingoJugador(Label[][] carton){
        int c=0;
        String[] numerosmarcados = new String[15];

        /**
         * Recorremos todo el carton y si se encuentra un numero marcado,
         * suma un contador
         * Si cuenta quince numeros = true
         * En caso contrario = false
         */
        for(int i=0;i<carton.length;i++){
            for(int j=0;j<carton[i].length;j++){
                if(carton[i][j].getId().equals("pulsado")){
                    numerosmarcados[c] = carton[i][j].getText();
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
                return 2;
            }
            else{
                return 1;
            }
        }
        else{
            return 0;
        }
    }

    /**
     * Cuando el bingo es correcto, se para el hilo, cierra la ventana actual y lleva a la pantalla
     * de resultados
     */
    public void BingoCorrecto(){
        //PRUEBAS CONSOLA
        System.out.println("BINGO");

        pantalla.IrBingo();
        // PARAR HILO
        executor.shutdown();

        // CERRAR VENTANA ACTUAL
        pantalla.CerrarVentanaActual();
    }

    /**
     * Boton para volver al menu de inicio
     * @param event
     */
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