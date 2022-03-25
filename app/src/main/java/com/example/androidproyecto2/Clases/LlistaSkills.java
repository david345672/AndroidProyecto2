package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.ArrayList;

public class LlistaSkills implements Serializable
{
    private int id;
    private String nom;
    private Boolean actiu;
    private ArrayList<Skill> skills;

    public LlistaSkills() {
    }

    public LlistaSkills(int id, String nom, Boolean actiu) {
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
    }

    public LlistaSkills(int id, String nom, Boolean actiu, ArrayList<Skill> skills) {
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
        this.skills = skills;
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

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
}
