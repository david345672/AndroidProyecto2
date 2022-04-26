package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.R;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class NotificacionesFragment extends Fragment {

    private ViewPager vpMesesAñoNotificaciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notificaciones, container, false);
        return view;
    }


    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        


    }



    //Coger los meses del año actual
    public ArrayList<Mes> getMeses()
    {
        //coger año actual
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);

        //Crear arrayList de meses y coger los nombres de los meses del año en un array normal
        ArrayList<Mes> meses = new ArrayList<Mes>();
        String[] months = new DateFormatSymbols().getMonths();

        //como siempre son 12, recorrer con un for 12 veces para llenar el arrayList de meses
        for (int i = 0; i < months.length; i++)
        {
            //Los meses empiezan en 0, coger todos los dias del año actual, del mes i donde Enero sera 0, Febrero 1,...
            Calendar mycal = new GregorianCalendar(anio, i,1);
            int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

            //Crear ArrayList de dias donde guardaremos el numero y el nombre, Ej: 2 y Lunes
            ArrayList<Dia> dias = new ArrayList<>();

            //recorremos cada uno de los dias iniciando en 1 para tener el dia exacto
            for (int d = 1; d <= daysInMonth; d++)
            {
                //Crear un String con la fecha del año actual, el mes que en este caso no empieza en 0 sino en 1, y el dia
                String dateString = String.format("%d-%d-%d", anio, i + 1, d);
                Date date = null;
                try {
                    //Creamos un objeto Date con los datos del dateString y cogemos el nombre del dia de la semana
                    //Le ponemos el idioma que queremos en mi caso pongo Español
                    date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
                    String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(date);


                    //Una vez cogido el dia y el nombre, crear un objeto Dia para añadir lo al array de dias
                    Dia dia = new Dia(d,dayOfWeek);
                    dias.add(dia);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            //Despues de llenar el arrayList de dias, Crear un objeto Mes con el año actual, el nombre del mes que en nuestro caso sera
            //la iteracion del array de meses, el numero del mes, Ej Enero es 1, Abril es 4,... y el arrayList de dias, Finalmente añadimos el mes en el arrayList de meses
            Mes mes = new Mes(anio,months[i],i + 1,dias);

            meses.add(mes);

        }


        return meses;
    }





}