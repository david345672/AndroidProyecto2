package com.example.androidproyecto2.Clases;

import java.io.Serializable;

public class Usuari implements Serializable
{
    private int id;
    private String nom;
    private int rols_id;
    private Boolean actiu;
    private String correo;
    private String contrasenya;
    private String cognoms;
    private String nomUsuari;
    private String imagen;

    public Usuari() {
    }

    public Usuari(int id, String nom, int rols_id, Boolean actiu, String correo, String contrasenya, String cognoms, String nomUsuari, String imagen) {
        this.id = id;
        this.nom = nom;
        this.rols_id = rols_id;
        this.actiu = actiu;
        this.correo = correo;
        this.contrasenya = contrasenya;
        this.cognoms = cognoms;
        this.nomUsuari = nomUsuari;
        this.imagen = imagen;
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

    public int getRols_id() {
        return rols_id;
    }

    public void setRols_id(int rols_id) {
        this.rols_id = rols_id;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }





}
