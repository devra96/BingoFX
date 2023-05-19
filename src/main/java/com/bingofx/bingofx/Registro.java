package com.bingofx.bingofx;

public class Registro {
    private int numpartida;
    private String nombrejugador;
    private String tiradaslinea;
    private String tiradasbingo;

    /**
     * CONSTRUCTOR CON TODOS LOS PARAMETROS
     * @param numpartida
     * @param nombrejugador
     * @param tiradaslinea
     * @param tiradasbingo
     */
    public Registro(int numpartida, String nombrejugador, String tiradaslinea, String tiradasbingo) {
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
    public Registro(String nombrejugador, String tiradaslinea, String tiradasbingo) {
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

    public String getTiradaslinea() {
        return tiradaslinea;
    }

    public void setTiradaslinea(String tiradaslinea) {
        this.tiradaslinea = tiradaslinea;
    }

    public String getTiradasbingo() {
        return tiradasbingo;
    }

    public void setTiradasbingo(String tiradasbingo) {
        this.tiradasbingo = tiradasbingo;
    }
}
