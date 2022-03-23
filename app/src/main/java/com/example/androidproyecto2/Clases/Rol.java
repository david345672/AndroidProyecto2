package com.example.androidproyecto2.Clases;

import java.io.Serializable;

public class Rol implements Serializable
{
    private int id;
    private String nom;
    private Boolean actiu;
    private Boolean GestionarKPIs;
    private Boolean GestionarListaSkills;
    private Boolean GestionarSkills;
    private Boolean GestionarUsuarios;
    private Boolean GestionarPerfiles;
    private Boolean GestionarGrupos;

    public Rol() {
    }

    public Rol(int id, String nom, Boolean actiu, Boolean gestionarKPIs, Boolean gestionarListaSkills, Boolean gestionarSkills, Boolean gestionarUsuarios, Boolean gestionarPerfiles, Boolean gestionarGrupos) {
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
        this.GestionarKPIs = gestionarKPIs;
        this.GestionarListaSkills = gestionarListaSkills;
        this.GestionarSkills = gestionarSkills;
        this.GestionarUsuarios = gestionarUsuarios;
        this.GestionarPerfiles = gestionarPerfiles;
        this.GestionarGrupos = gestionarGrupos;
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

    public Boolean getGestionarKPIs() {
        return GestionarKPIs;
    }

    public void setGestionarKPIs(Boolean gestionarKPIs) {
        this.GestionarKPIs = gestionarKPIs;
    }

    public Boolean getGestionarListaSkills() {
        return GestionarListaSkills;
    }

    public void setGestionarListaSkills(Boolean gestionarListaSkills) {
        this.GestionarListaSkills = gestionarListaSkills;
    }

    public Boolean getGestionarSkills() {
        return GestionarSkills;
    }

    public void setGestionarSkills(Boolean gestionarSkills) {
        this.GestionarSkills = gestionarSkills;
    }

    public Boolean getGestionarUsuarios() {
        return GestionarUsuarios;
    }

    public void setGestionarUsuarios(Boolean gestionarUsuarios) {
        this.GestionarUsuarios = gestionarUsuarios;
    }

    public Boolean getGestionarPerfiles() {
        return GestionarPerfiles;
    }

    public void setGestionarPerfiles(Boolean gestionarPerfiles) {
        this.GestionarPerfiles = gestionarPerfiles;
    }

    public Boolean getGestionarGrupos() {
        return GestionarGrupos;
    }

    public void setGestionarGrupos(Boolean gestionarGrupos) {
        this.GestionarGrupos = gestionarGrupos;
    }



}
