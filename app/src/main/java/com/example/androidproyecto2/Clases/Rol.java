package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.List;

public class Rol implements Serializable
{
    private List<Usuari> usuaris;
    private int id;
    private String nom;
    private Boolean actiu;
    private Boolean gestionarKPIs;
    private Boolean gestionarListaSkills;
    private Boolean gestionarSkills;
    private Boolean gestionarUsuarios;
    private Boolean gestionarPerfiles;
    private Boolean gestionarGrupos;
    private Boolean gestionarCursos;
    private Boolean gestionarRelacionesGrupos;

    public Rol() {
    }

    public Rol(List<Usuari> usuaris, int id, String nom, Boolean actiu, Boolean gestionarKPIs, Boolean gestionarListaSkills, Boolean gestionarSkills, Boolean gestionarUsuarios, Boolean gestionarPerfiles, Boolean gestionarGrupos, Boolean gestionarCursos, Boolean gestionarRelacionesGrupos) {
        this.usuaris = usuaris;
        this.id = id;
        this.nom = nom;
        this.actiu = actiu;
        this.gestionarKPIs = gestionarKPIs;
        this.gestionarListaSkills = gestionarListaSkills;
        this.gestionarSkills = gestionarSkills;
        this.gestionarUsuarios = gestionarUsuarios;
        this.gestionarPerfiles = gestionarPerfiles;
        this.gestionarGrupos = gestionarGrupos;
        this.gestionarCursos = gestionarCursos;
        this.gestionarRelacionesGrupos = gestionarRelacionesGrupos;
    }

    public List<Usuari> getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(List<Usuari> usuaris) {
        this.usuaris = usuaris;
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
        return gestionarKPIs;
    }

    public void setGestionarKPIs(Boolean gestionarKPIs) {
        this.gestionarKPIs = gestionarKPIs;
    }

    public Boolean getGestionarListaSkills() {
        return gestionarListaSkills;
    }

    public void setGestionarListaSkills(Boolean gestionarListaSkills) {
        this.gestionarListaSkills = gestionarListaSkills;
    }

    public Boolean getGestionarSkills() {
        return gestionarSkills;
    }

    public void setGestionarSkills(Boolean gestionarSkills) {
        this.gestionarSkills = gestionarSkills;
    }

    public Boolean getGestionarUsuarios() {
        return gestionarUsuarios;
    }

    public void setGestionarUsuarios(Boolean gestionarUsuarios) {
        this.gestionarUsuarios = gestionarUsuarios;
    }

    public Boolean getGestionarPerfiles() {
        return gestionarPerfiles;
    }

    public void setGestionarPerfiles(Boolean gestionarPerfiles) {
        this.gestionarPerfiles = gestionarPerfiles;
    }

    public Boolean getGestionarGrupos() {
        return gestionarGrupos;
    }

    public void setGestionarGrupos(Boolean gestionarGrupos) {
        this.gestionarGrupos = gestionarGrupos;
    }

    public Boolean getGestionarCursos() {
        return gestionarCursos;
    }

    public void setGestionarCursos(Boolean gestionarCursos) {
        this.gestionarCursos = gestionarCursos;
    }

    public Boolean getGestionarRelacionesGrupos() {
        return gestionarRelacionesGrupos;
    }

    public void setGestionarRelacionesGrupos(Boolean gestionarRelacionesGrupos) {
        this.gestionarRelacionesGrupos = gestionarRelacionesGrupos;
    }
}
