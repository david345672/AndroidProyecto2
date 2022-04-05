package com.example.androidproyecto2.Clases;

import com.google.type.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Valoracio implements Serializable
{
    private int kpis_id;
    private int usuari_valorat_id;
    private int usuari_pp_id;
    private Timestamp data;
    private int nota;
    private int llistes_skills_id;
    private int skills_id;
    private String observacions;

    public Valoracio() {
    }


    public Valoracio(int kpis_id, int usuari_valorat_id, int usuari_pp_id, Timestamp data, int nota, int llistes_skills_id, int skills_id, String observacions) {
        this.kpis_id = kpis_id;
        this.usuari_valorat_id = usuari_valorat_id;
        this.usuari_pp_id = usuari_pp_id;
        this.data = data;
        this.nota = nota;
        this.llistes_skills_id = llistes_skills_id;
        this.skills_id = skills_id;
        this.observacions = observacions;
    }



    public int getKpis_id() {
        return kpis_id;
    }

    public void setKpis_id(int kpis_id) {
        this.kpis_id = kpis_id;
    }

    public int getUsuari_valorat_id() {
        return usuari_valorat_id;
    }

    public void setUsuari_valorat_id(int usuari_valorat_id) {
        this.usuari_valorat_id = usuari_valorat_id;
    }

    public int getUsuari_pp_id() {
        return usuari_pp_id;
    }

    public void setUsuari_pp_id(int usuari_pp_id) {
        this.usuari_pp_id = usuari_pp_id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getLlistes_skills_id() {
        return llistes_skills_id;
    }

    public void setLlistes_skills_id(int llistes_skills_id) {
        this.llistes_skills_id = llistes_skills_id;
    }

    public int getSkills_id() {
        return skills_id;
    }

    public void setSkills_id(int skills_id) {
        this.skills_id = skills_id;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }


    @Override
    public String toString() {
        return "Valoracio{" +
                "kpis_id=" + kpis_id +
                ", usuari_valorat_id=" + usuari_valorat_id +
                ", usuari_pp_id=" + usuari_pp_id +
                ", data=" + data +
                ", nota=" + nota +
                ", llistes_skills_id=" + llistes_skills_id +
                ", skills_id=" + skills_id +
                ", observacions='" + observacions + '\'' +
                '}';
    }
}
