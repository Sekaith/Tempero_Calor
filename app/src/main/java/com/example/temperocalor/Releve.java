package com.example.temperocalor;

public class Releve {
    protected String idReleve;
    protected int jour;
    protected String mois;
    protected String temp6h;
    protected String temp12h;
    protected String temp18h;
    protected String temp24h;
    protected Lac idLac;

    public Releve(String idReleve, int jour, String mois, String temp6h, String temp12h, String temp18h, String temp24h, Lac idLac) {
        this.idReleve = idReleve;
        this.jour = jour;
        this.mois = mois;
        this.temp6h = temp6h;
        this.temp12h = temp12h;
        this.temp18h = temp18h;
        this.temp24h = temp24h;
        this.idLac = idLac;
    }

    public String getIdReleve() {
        return idReleve;
    }

    public void setIdReleve(String idReleve) {
        this.idReleve = idReleve;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getTemp6h() {
        return temp6h;
    }

    public void setTemp6h(String temp6h) {
        this.temp6h = temp6h;
    }

    public String getTemp12h() {
        return temp12h;
    }

    public void setTemp12h(String temp12h) {
        this.temp12h = temp12h;
    }

    public String getTemp18h() {
        return temp18h;
    }

    public void setTemp18h(String temp18h) {
        this.temp18h = temp18h;
    }

    public String getTemp24h() {
        return temp24h;
    }

    public void setTemp24h(String temp24h) {
        this.temp24h = temp24h;
    }

    public Lac getIdLac() {
        return idLac;
    }

    public void setIdLac(Lac idLac) {
        this.idLac = idLac;
    }

}
