package com.example.androidproyecto2.Clases;

import java.io.Serializable;

public class Kpi implements Serializable
{
    private int id;
    private String nom;
    private int skills_id;
    private Boolean actiu;

    public Kpi() {
    }

    public Kpi(int id, String nom, int skills_id, Boolean actiu) {
        this.id = id;
        this.nom = nom;
        this.skills_id = skills_id;
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

    public int getSkills_id() {
        return skills_id;
    }

    public void setSkills_id(int skills_id) {
        this.skills_id = skills_id;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }



}
