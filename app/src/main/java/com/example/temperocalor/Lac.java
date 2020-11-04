package com.example.temperocalor;

public class Lac {

    protected String idLac;
    protected String nom;
    protected double coordX;
    protected double coordY;

    public Lac( String idLac, String nom, double coordX,double coordY) {
        this.idLac = idLac;
        this.nom = nom;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public String getIdLac() {
        return idLac;
    }

    public void setIdLac(String idLac) {
        this.idLac = idLac;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

}

