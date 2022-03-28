package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LlistaSkills implements Serializable
{
    private List<Grups_has_llistes_skills> grups_has_llistes_skills;
    private List<Skill> skills;
    private int id;
    private String nom;
    private Boolean actiu;

    public LlistaSkills() {
    }

    public LlistaSkills(int id, String nom, Boolean actiu) {
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
    }


    public LlistaSkills(List<Grups_has_llistes_skills> grups_has_llistes_skills, List<Skill> skills, int id, String nom, Boolean actiu) {
        this.grups_has_llistes_skills = grups_has_llistes_skills;
        this.skills = skills;
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
    }


    public List<Grups_has_llistes_skills> getGrups_has_llistes_skills() {
        return grups_has_llistes_skills;
    }

    public void setGrups_has_llistes_skills(List<Grups_has_llistes_skills> grups_has_llistes_skills) {
        this.grups_has_llistes_skills = grups_has_llistes_skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
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
}
