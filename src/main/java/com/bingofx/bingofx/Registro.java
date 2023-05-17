package com.bingofx.bingofx;

public class Registro {
    private int numpartida;
    private String nombrejugador;
    private int tiradaslinea;
    private int tiradasbingo;

    /**
     * CONSTRUCTOR CON TODOS LOS PARAMETROS
     * @param numpartida
     * @param nombrejugador
     * @param tiradaslinea
     * @param tiradasbingo
     */
    public Registro(int numpartida, String nombrejugador, int tiradaslinea, int tiradasbingo) {
        this.numpartida = numpartida;
        this.nombrejugador = nombrejugador;
        this.tiradaslinea = tiradaslinea;
        this.tiradasbingo = tiradasbingo;
    }

    /**
     * CONSTRUCTOR SIN ATRIBUTO "numpartida" (PORQUE ES AUTO-INCREMENTABLE)
     * @param nombrejugador
     * @param tiradaslinea
     * @param tiradasbingo
     */
    public Registro(String nombrejugador, int tiradaslinea, int tiradasbingo) {
        this.nombrejugador = nombrejugador;
        this.tiradaslinea = tiradaslinea;
        this.tiradasbingo = tiradasbingo;
    }

    public int getNumpartida() {
        return numpartida;
    }

    public void setNumpartida(int numpartida) {
        this.numpartida = numpartida;
    }

    public String getNombrejugador() {
        return nombrejugador;
    }

    public void setNombrejugador(String nombrejugador) {
        this.nombrejugador = nombrejugador;
    }

    public int getTiradaslinea() {
        return tiradaslinea;
    }

    public void setTiradaslinea(int tiradaslinea) {
        this.tiradaslinea = tiradaslinea;
    }

    public int getTiradasbingo() {
        return tiradasbingo;
    }

    public void setTiradasbingo(int tiradasbingo) {
        this.tiradasbingo = tiradasbingo;
    }
}
