package com.example.androidproyecto2.Clases.CustomCalendar;

import com.example.androidproyecto2.Clases.Valoracio;

import java.util.ArrayList;

public class Dia implements Comparable<Dia>
{
    private int num;
    private String nombre;

    public Dia(int num, String nombre) {
        this.num = num;
        this.nombre = nombre;
    }


    public int getNum() {
        return num;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(Dia dia) {
        int comparenum = ((Dia)dia).getNum();

        //  For Ascending order
        return this.num - comparenum;
    }
}
