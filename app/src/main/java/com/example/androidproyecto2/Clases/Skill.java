package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.List;

public class Skill implements Serializable
{
    private List<Kpi> Kpis;
    private LlistaSkills llistes_skills;
    private int id;
    private String nom;
    private int llistes_skills_id;
    private Boolean actiu;
    private int colorFondo;
    private int colorTexto;

    public Skill() {
    }

    public Skill(int id, String nom, int llistes_skills_id, Boolean actiu, int colorFondo, int colorTexto) {
        this.id = id;
        this.nom = nom;
        this.llistes_skills_id = llistes_skills_id;
        this.actiu = actiu;
        this.colorFondo = colorFondo;
        this.colorTexto = colorTexto;
    }


    public Skill(List<Kpi> kpis, LlistaSkills llistes_skills, int id, String nom, int llistes_skills_id, Boolean actiu, int colorFondo, int colorTexto) {
        this.Kpis = kpis;
        this.llistes_skills = llistes_skills;
        this.id = id;
        this.nom = nom;
        this.llistes_skills_id = llistes_skills_id;
        this.actiu = actiu;
        this.colorFondo = colorFondo;
        this.colorTexto = colorTexto;
    }


    public List<Kpi> getKpis() {
        return Kpis;
    }

    public void setKpis(List<Kpi> kpis) {
        Kpis = kpis;
    }

    public LlistaSkills getLlistes_skills() {
        return llistes_skills;
    }

    public void setLlistes_skills(LlistaSkills llistes_skills) {
        this.llistes_skills = llistes_skills;
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

    public int getLlistes_skills_id() {
        return llistes_skills_id;
    }

    public void setLlistes_skills_id(int llistes_skills_id) {
        this.llistes_skills_id = llistes_skills_id;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public int getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(int colorFondo) {
        this.colorFondo = colorFondo;
    }

    public int getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(int colorTexto) {
        this.colorTexto = colorTexto;
    }
}
