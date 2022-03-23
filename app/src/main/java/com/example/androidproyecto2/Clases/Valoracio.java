package com.example.androidproyecto2.Clases;

import java.io.Serializable;
import java.util.Date;

public class Valoracio implements Serializable
{
    private int kpis_id;
    private int usuari_valorat_id;
    private int usuari_pp_id;
    private Date data;
    private int nota;

    public Valoracio() {
    }

    public Valoracio(int kpis_id, int usuari_valorat_id, int usuari_pp_id, Date data, int nota) {
        this.kpis_id = kpis_id;
        this.usuari_valorat_id = usuari_valorat_id;
        this.usuari_pp_id = usuari_pp_id;
        this.data = data;
        this.nota = nota;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }


}
