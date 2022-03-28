package com.example.androidproyecto2.Clases;

public class Grups_has_alumnes
{
    private Curs cursos;
    private Grup grups;
    private Usuari usuaris;
    private int grups_id;
    private int usuaris_id;
    private int curs_id;

    public Grups_has_alumnes() {
    }


    public Grups_has_alumnes(int grups_id, int usuaris_id, int curs_id) {
        this.grups_id = grups_id;
        this.usuaris_id = usuaris_id;
        this.curs_id = curs_id;
    }


    public Grups_has_alumnes(Curs cursos, Grup grups, Usuari usuaris, int grups_id, int usuaris_id, int curs_id) {
        this.cursos = cursos;
        this.grups = grups;
        this.usuaris = usuaris;
        this.grups_id = grups_id;
        this.usuaris_id = usuaris_id;
        this.curs_id = curs_id;
    }

    public Curs getCursos() {
        return cursos;
    }

    public void setCursos(Curs cursos) {
        this.cursos = cursos;
    }

    public Grup getGrups() {
        return grups;
    }

    public void setGrups(Grup grups) {
        this.grups = grups;
    }

    public Usuari getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(Usuari usuaris) {
        this.usuaris = usuaris;
    }

    public int getGrups_id() {
        return grups_id;
    }

    public void setGrups_id(int grups_id) {
        this.grups_id = grups_id;
    }

    public int getUsuaris_id() {
        return usuaris_id;
    }

    public void setUsuaris_id(int usuaris_id) {
        this.usuaris_id = usuaris_id;
    }

    public int getCurs_id() {
        return curs_id;
    }

    public void setCurs_id(int curs_id) {
        this.curs_id = curs_id;
    }
}
