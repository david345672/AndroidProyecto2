package com.example.androidproyecto2.Clases;

import java.io.Serializable;

public class Curs implements Serializable
{
    private int id;
    private int curs_inici;
    private int curs_fi;
    private Boolean actiu;
    private String nom;

    public Curs(int id, int curs_inici, int curs_fi, Boolean actiu, String nom) {
        this.id = id;
        this.curs_inici = curs_inici;
        this.curs_fi = curs_fi;
        this.actiu = actiu;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurs_inici() {
        return curs_inici;
    }

    public void setCurs_inici(int curs_inici) {
        this.curs_inici = curs_inici;
    }

    public int getCurs_fi() {
        return curs_fi;
    }

    public void setCurs_fi(int curs_fi) {
        this.curs_fi = curs_fi;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



}
