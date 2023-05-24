package com.bingofx.bingofx;

public class Registro {
    private int numpartida;
    private String nombrejugador;
    private String hizolinea;
    private int tiradaslinea;
    private String hizobingo;
    private int tiradasbingo;

    /**
     * CONSTRUCTOR CON TODOS LOS PARAMETROS
     * @param numpartida
     * @param nombrejugador
     * @param hizolinea
     * @param tiradaslinea
     * @param hizobingo
     * @param tiradasbingo
     */
    public Registro(int numpartida, String nombrejugador, String hizolinea, int tiradaslinea, String hizobingo, int tiradasbingo) {
        this.numpartida = numpartida;
        this.nombrejugador = nombrejugador;
        this.hizolinea = hizolinea;
        this.tiradaslinea = tiradaslinea;
        this.hizobingo = hizobingo;
        this.tiradasbingo = tiradasbingo;
    }

    /**
     * CONSTRUCTOR SIN ATRIBUTO "numpartida" (PORQUE ES AUTO-INCREMENTABLE)
     * @param nombrejugador
     * @param hizolinea
     * @param tiradaslinea
     * @param hizobingo
     * @param tiradasbingo
     */
    public Registro(String nombrejugador, String hizolinea, int tiradaslinea, String hizobingo, int tiradasbingo) {
        this.nombrejugador = nombrejugador;
        this.hizolinea = hizolinea;
        this.tiradaslinea = tiradaslinea;
        this.hizobingo = hizobingo;
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

    public String getHizolinea() {
        return hizolinea;
    }

    public void setHizolinea(String hizolinea) {
        this.hizolinea = hizolinea;
    }

    public int getTiradaslinea() {
        return tiradaslinea;
    }

    public void setTiradaslinea(int tiradaslinea) {
        this.tiradaslinea = tiradaslinea;
    }

    public String getHizobingo() {
        return hizobingo;
    }

    public void setHizobingo(String hizobingo) {
        this.hizobingo = hizobingo;
    }

    public int getTiradasbingo() {
        return tiradasbingo;
    }

    public void setTiradasbingo(int tiradasbingo) {
        this.tiradasbingo = tiradasbingo;
    }
}
