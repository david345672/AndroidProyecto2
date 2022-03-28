package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.List;

public class Curs implements Serializable
{
    private List<Grups_has_alumnes> grups_has_alumnes;
    private List<Grups_has_docents> grups_has_docents;
    private List<Grups_has_llistes_skills> grups_has_llistes_skills;
    private int id;
    private int curs_inici;
    private int curs_fi;
    private Boolean actiu;
    private String nom;

    public Curs() {
    }

    public Curs(int id, int curs_inici, int curs_fi, Boolean actiu, String nom) {
        this.id = id;
        this.curs_inici = curs_inici;
        this.curs_fi = curs_fi;
        this.actiu = actiu;
        this.nom = nom;
    }

    public Curs(List<Grups_has_alumnes> grups_has_alumnes, List<Grups_has_docents> grups_has_docents, List<Grups_has_llistes_skills> grups_has_llistes_skills, int id, int curs_inici, int curs_fi, Boolean actiu, String nom) {
        this.grups_has_alumnes = grups_has_alumnes;
        this.grups_has_docents = grups_has_docents;
        this.grups_has_llistes_skills = grups_has_llistes_skills;
        this.id = id;
        this.curs_inici = curs_inici;
        this.curs_fi = curs_fi;
        this.actiu = actiu;
        this.nom = nom;
    }

    public List<Grups_has_alumnes> getGrups_has_alumnes() {
        return grups_has_alumnes;
    }

    public void setGrups_has_alumnes(List<Grups_has_alumnes> grups_has_alumnes) {
        this.grups_has_alumnes = grups_has_alumnes;
    }

    public List<Grups_has_docents> getGrups_has_docents() {
        return grups_has_docents;
    }

    public void setGrups_has_docents(List<Grups_has_docents> grups_has_docents) {
        this.grups_has_docents = grups_has_docents;
    }

    public List<Grups_has_llistes_skills> getGrups_has_llistes_skills() {
        return grups_has_llistes_skills;
    }

    public void setGrups_has_llistes_skills(List<Grups_has_llistes_skills> grups_has_llistes_skills) {
        this.grups_has_llistes_skills = grups_has_llistes_skills;
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
