package com.example.temperocalor;

public class Releve {
    protected String idReleve;
    protected String Date;
    protected String temp6h;
    protected String temp12h;
    protected String temp18h;
    protected String temp24h;
    protected String idLac;

    public Releve(String idReleve, String Date, String temp6h, String temp12h, String temp18h, String temp24h, String idLac) {
        this.idReleve = idReleve;
        this.Date = Date;
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

    public String getDate() {return Date;}

    public void setDate(String Date) {this.Date = Date;}

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

    public String getIdLac() {
        return idLac;
    }

    public void setIdLac(String idLac) {
        this.idLac = idLac;
    }

}
