package com.example.androidproyecto2.Clases.CustomCalendar;

import java.util.ArrayList;

public class Mes
{
    private int Año;
    private String nombre;
    private ArrayList<Dia> dias;


    public Mes(int Año, String nombre,ArrayList<Dia> dias) {
        this.Año = Año;
        this.nombre = nombre;
        this.dias = dias;
    }

    public int getAño() {
        return Año;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Dia> getDias() {
        return dias;
    }

}
