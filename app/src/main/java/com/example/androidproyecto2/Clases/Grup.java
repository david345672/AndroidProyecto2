package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Grup
{
//    private  Grups_has_alumnes [] grups_has_alumnes;
//    private Grups_has_docents [] grups_has_docents;
//    private Grups_has_llistes_skills [] grups_has_llistes_skills;
    private int id;
    private String nom;
    private Boolean actiu;

    public Grup() {
    }

    public Grup(int id, String nom, Boolean actiu) {
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
    }
//
//    public Grup(Grups_has_alumnes[] grups_has_alumnes, Grups_has_docents[] grups_has_docents, Grups_has_llistes_skills[] grups_has_llistes_skills, int id, String nom, Boolean actiu) {
//        this.grups_has_alumnes = grups_has_alumnes;
//        this.grups_has_docents = grups_has_docents;
//        this.grups_has_llistes_skills = grups_has_llistes_skills;
//        this.id = id;
//        this.nom = nom;
//        this.actiu = actiu;
//    }
//
//
//    public Grups_has_alumnes[] getGrups_has_alumnes() {
//        return grups_has_alumnes;
//    }
//
//    public void setGrups_has_alumnes(Grups_has_alumnes[] grups_has_alumnes) {
//        this.grups_has_alumnes = grups_has_alumnes;
//    }
//
//    public Grups_has_docents[] getGrups_has_docents() {
//        return grups_has_docents;
//    }
//
//    public void setGrups_has_docents(Grups_has_docents[] grups_has_docents) {
//        this.grups_has_docents = grups_has_docents;
//    }
//
//    public Grups_has_llistes_skills[] getGrups_has_llistes_skills() {
//        return grups_has_llistes_skills;
//    }
//
//    public void setGrups_has_llistes_skills(Grups_has_llistes_skills[] grups_has_llistes_skills) {
//        this.grups_has_llistes_skills = grups_has_llistes_skills;
//    }

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
