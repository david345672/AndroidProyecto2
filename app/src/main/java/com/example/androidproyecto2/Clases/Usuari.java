package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.List;

public class Usuari implements Serializable
{
    private List<Grups_has_alumnes> grups_has_alumnes;
    private List<Grups_has_docents> grups_has_docents;
    private List<Valoracio> valoracions;
    private List<Valoracio> valoracions1;
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

    public Usuari(List<Grups_has_alumnes> grups_has_alumnes, List<Grups_has_docents> grups_has_docents, List<Valoracio> valoracions, List<Valoracio> valoracions1, int id, String nom, int rols_id, Boolean actiu, String correo, String contrasenya, String cognoms, String nomUsuari, String imagen) {
        this.grups_has_alumnes = grups_has_alumnes;
        this.grups_has_docents = grups_has_docents;
        this.valoracions = valoracions;
        this.valoracions1 = valoracions1;
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

    public List<Valoracio> getValoracions() {
        return valoracions;
    }

    public void setValoracions(List<Valoracio> valoracions) {
        this.valoracions = valoracions;
    }

    public List<Valoracio> getValoracions1() {
        return valoracions1;
    }

    public void setValoracions1(List<Valoracio> valoracions1) {
        this.valoracions1 = valoracions1;
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
