package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.InputFilterMinMax;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Notificacio;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment.CalendarMesesAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.NotificacionsService;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.google.gson.Gson;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificacionesFragment extends Fragment {

    private MainActivity activity;
    private Button btnNuevaNotificacio;
    private ViewPager vpMesesAñoNotificaciones;
    private ArrayList<Mes> meses;
    private Dialog diaogNotificaciones;

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

        activity = (MainActivity) getActivity();

        meses = getMeses();

        btnNuevaNotificacio = view.findViewById(R.id.btnNuevaNotificacio);
        vpMesesAñoNotificaciones = view.findViewById(R.id.vpMesesAñoNotificaciones);

        recargarNotificacionesUsuario(activity.usuariLogin.getId());

        //components de diaogNotificaciones
        diaogNotificaciones = new Dialog(activity);
        diaogNotificaciones.requestWindowFeature(Window.FEATURE_NO_TITLE);
        diaogNotificaciones.setContentView(R.layout.dialog_notificacion);

        EditText txtNouMissatge = diaogNotificaciones.findViewById(R.id.txtNouMissatge);
        Spinner spnrMeses = diaogNotificaciones.findViewById(R.id.spnrMeses);
        Spinner spnrDias = diaogNotificaciones.findViewById(R.id.spnrDias);

        EditText txtHora = diaogNotificaciones.findViewById(R.id.txtHora);
        txtHora.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "23")});

        EditText txtMin = diaogNotificaciones.findViewById(R.id.txtMin);
        txtMin.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});

        Button btnGuardar = diaogNotificaciones.findViewById(R.id.btnGuardar);





        ArrayList<String> nombreMeses = llenarMeses(meses);

        ArrayAdapter<String> CantiadAdapter = new ArrayAdapter<String>(activity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombreMeses);
        spnrMeses.setAdapter(CantiadAdapter);

        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);

        String currentanio = Integer.toString(anio) ;
        btnNuevaNotificacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaogNotificaciones.show();

            }
        });



        spnrMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int Cantdias = meses.get(i).getDias().size();
                Integer [] diasMes = new Integer[Cantdias];

                int j = 0;
                for (Dia dia: meses.get(i).getDias()) {
                    diasMes[j] = dia.getNum();
                    j++;
                }


                ArrayAdapter<Integer> CantiadAdapter = new ArrayAdapter<Integer>(activity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, diasMes);
                spnrDias.setAdapter(CantiadAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String mensaje = txtNouMissatge.getText().toString();

               String mes = cogerMes(spnrMeses);
               String dia = cogerDia(spnrDias);

               String hora = cogerHora(txtHora.getText().toString());
               String min = cogerMin(txtMin.getText().toString());

               String date = currentanio + mes + dia + "_" + hora + min + 20;


               Notificacio notificacio = new Notificacio(activity.usuariLogin.getId(),mensaje,date);


               insertNotificaion(notificacio);



            }
        });





    }


    public void cargarViewPagerNotificaciones(List<Notificacio> notificacions)
    {


        HashSet<Mes> mesesNotificacion = cogerMesesDeNotificaciones(meses,notificacions);
        ArrayList<Mes> mesesDeNotificaciones = new ArrayList<>(mesesNotificacion);
        Collections.sort(mesesDeNotificaciones);

        vpMesesAñoNotificaciones.setClipToPadding(false);
        CalendarMesesAdapterNotificaciones calendarMesesAdapter = new CalendarMesesAdapterNotificaciones(getContext(),mesesDeNotificaciones, activity, notificacions);
        vpMesesAñoNotificaciones.setAdapter(calendarMesesAdapter);

    }


    public void recargarNotificacionesUsuario(int id)
    {
        UsuarisService userService = Api.getApi().create(UsuarisService.class);
        Call<Usuari> userCall = userService.Getusuaris(id);

        userCall.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                switch (response.code())
                {
                    case 200:

                        Usuari user = response.body();

                        cargarViewPagerNotificaciones(user.getNotificacions());


                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(activity, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(activity,"Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {

            }
        });

    }



    public String cogerMes(Spinner spnrMeses)
    {
        int mesPos = spnrMeses.getSelectedItemPosition();

        int mes = mesPos + 1;
        String numMes;

        if (mes > 0 && mes < 10)
        {
            numMes = "0"+mes;
        }
        else
        {
            numMes = Integer.toString(mes);
        }

        return numMes;
    }

    public String cogerDia(Spinner spnrDias)
    {
        int mesPos = spnrDias.getSelectedItemPosition();

        int mes = mesPos + 1;
        String numDia;

        if (mes > 0 && mes < 10)
        {
            numDia = "0"+mes;
        }
        else
        {
            numDia = Integer.toString(mes);
        }

        return numDia;
    }


    public String cogerHora(String hora)
    {

        int horaStr = Integer.parseInt(hora);

        String numHora;

        if (horaStr > 0 && horaStr < 10)
        {
            numHora = "0"+horaStr;
        }
        else
        {
            numHora = Integer.toString(horaStr);
        }

        return numHora;
    }


    public String cogerMin(String min)
    {

        int minStr = Integer.parseInt(min);

        String numint;

        if (minStr > 0 && minStr < 10)
        {
            numint = "0"+minStr;
        }
        else
        {
            numint = Integer.toString(minStr);
        }

        return numint;
    }



    public void insertNotificaion(Notificacio notificacio)
    {
        NotificacionsService notificacionsService = Api.getApi().create(NotificacionsService.class);
        Call<Notificacio> notificacioCall = notificacionsService.Postnotificacions(notificacio);

        notificacioCall.enqueue(new Callback<Notificacio>() {
            @Override
            public void onResponse(Call<Notificacio> call, Response<Notificacio> response) {
                switch (response.code())
                {
                    case 201:
                        Toast.makeText(activity, "Notificacion Añadida", Toast.LENGTH_LONG).show();
                        recargarNotificacionesUsuario(activity.usuariLogin.getId());
                        diaogNotificaciones.dismiss();
                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(activity, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Notificacio> call, Throwable t) {
                Toast.makeText(activity, t.getCause() + " - " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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




    //Coger todos los meses sin repedidos de las notificaciones
    public HashSet<Mes> cogerMesesDeNotificaciones(ArrayList<Mes> meses, List<Notificacio> notificacions)
    {
        HashSet<Mes> mesesNotificacion = new HashSet<>();

        for (int i = 0; i < notificacions.size(); i++)
        {
            char [] numFecha = notificacions.get(i).getData().toCharArray();
            String mesStr = new StringBuilder(numFecha[4]).append(numFecha[5]).toString();
            int mes = Integer.parseInt(mesStr);

            for (int j = 0; j < meses.size(); j++)
            {
                if (mes - 1 == j)
                {
                    mesesNotificacion.add(meses.get(j));
                }
            }

        }


        return mesesNotificacion;
    }



    public ArrayList<String> llenarMeses(ArrayList<Mes> meses)
    {
        ArrayList<String> NomMeses = new ArrayList<>();

        for (Mes mes: meses) {
            NomMeses.add(mes.getNombre());
        }


        return NomMeses;
    }



}