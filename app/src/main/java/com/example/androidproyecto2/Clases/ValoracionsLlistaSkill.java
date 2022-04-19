package com.example.androidproyecto2.Clases;

import java.util.ArrayList;

public class ValoracionsLlistaSkill
{
    private int idLlistaSkill;
    private ArrayList<Valoracio> valoracions;

    public ValoracionsLlistaSkill(int idLlistaSkill, ArrayList<Valoracio> valoracions) {
        this.idLlistaSkill = idLlistaSkill;
        this.valoracions = valoracions;
    }


    public int getIdLlistaSkill() {
        return idLlistaSkill;
    }

    public ArrayList<Valoracio> getValoracios() {
        return valoracions;
    }
}
