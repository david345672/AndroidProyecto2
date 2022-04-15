package com.example.androidproyecto2.Clases.CustomCalendar;

public class Mes
{
    private int Año;
    private String nombre;
    private int numMes;
    private int[] dias;


    public Mes(int Año, String nombre, int numMes, int[] dias) {
        this.Año = Año;
        this.nombre = nombre;
        this.numMes = numMes;
        this.dias = dias;
    }

    public int getAño() {
        return Año;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumMes() {
        return numMes;
    }

    public int[] getDias() {
        return dias;
    }

}
