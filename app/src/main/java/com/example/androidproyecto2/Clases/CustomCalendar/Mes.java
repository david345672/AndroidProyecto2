package com.example.androidproyecto2.Clases.CustomCalendar;

import java.util.ArrayList;

public class Mes implements Comparable<Mes>
{
    private int Año;
    private String nombre;
    private int num;
    private ArrayList<Dia> dias;


    public Mes(int Año, String nombre,int num,ArrayList<Dia> dias) {
        this.Año = Año;
        this.nombre = nombre;
        this.num = num;
        this.dias = dias;
    }

    public int getAño() {
        return Año;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNum() {
        return num;
    }

    public ArrayList<Dia> getDias() {
        return dias;
    }

    @Override
    public int compareTo(Mes mes) {
        int comparenum = ((Mes)mes).getNum();

        //  For Ascending order
        return this.num - comparenum;

    }
}
