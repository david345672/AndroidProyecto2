package com.example.androidproyecto2.Clases;

import java.io.Serializable;

public class Notificacio implements Serializable
{
    private Usuari usuaris;
    private int id;
    private int usuaris_id;
    private String missatge;
    private String data;

    public Notificacio(Usuari usuaris, int id, int usuaris_id, String missatge, String data) {
        this.usuaris = usuaris;
        this.id = id;
        this.usuaris_id = usuaris_id;
        this.missatge = missatge;
        this.data = data;
    }

    public Notificacio(Usuari usuaris, int usuaris_id, String missatge, String data) {
        this.usuaris = usuaris;
        this.usuaris_id = usuaris_id;
        this.missatge = missatge;
        this.data = data;
    }

    public Usuari getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(Usuari usuaris) {
        this.usuaris = usuaris;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuaris_id() {
        return usuaris_id;
    }

    public void setUsuaris_id(int usuaris_id) {
        this.usuaris_id = usuaris_id;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }





}
