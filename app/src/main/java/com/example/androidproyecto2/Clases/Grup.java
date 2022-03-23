package com.example.androidproyecto2.Clases;

import java.io.Serializable;

public class Grup implements Serializable
{
    private int id;
    private String nom;
    private Boolean actiu;

    public Grup(int id, String nom, Boolean actiu) {
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }


}
