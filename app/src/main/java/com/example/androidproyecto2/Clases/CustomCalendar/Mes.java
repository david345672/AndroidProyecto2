package com.example.androidproyecto2.Clases.CustomCalendar;

public class Mes
{
    private int Año;
    private String nombre;
    private int[] dias;


    public Mes(int año, String nombre, int[] dias) {
        Año = año;
        this.nombre = nombre;
        this.dias = dias;
    }

    public int getAño() {
        return Año;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getDias() {
        return dias;
    }

}
