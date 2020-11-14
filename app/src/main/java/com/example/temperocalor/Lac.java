package com.example.temperocalor;

public class Lac {

    protected String IdLac;
    protected String nom;
    protected double coordX;
    protected double coordY;

    public Lac(String nom, double coordX,double coordY) {
        this.IdLac = IdLac;
        this.nom = nom;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public String getIdLac() {
        return IdLac;
    }

    public void setIdLac(String IdLac) {
        this.IdLac = IdLac;
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

