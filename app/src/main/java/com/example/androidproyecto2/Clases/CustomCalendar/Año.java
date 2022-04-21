package com.example.androidproyecto2.Clases.CustomCalendar;

public class Año
{
    private int año;
    private Mes[] meses;

    public Año(int año, Mes[] meses) {
        this.año = año;
        this.meses = meses;
    }


    public int getAño() {
        return año;
    }

    public Mes[] getMeses() {
        return meses;
    }
}
