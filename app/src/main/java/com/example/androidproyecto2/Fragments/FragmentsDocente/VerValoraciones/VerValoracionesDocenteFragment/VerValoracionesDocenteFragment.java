package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.CustomCalendar.Año;
import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Grups_has_alumnes;
import com.example.androidproyecto2.Clases.Grups_has_docents;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment.GrupsAdapterViewPager;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;
import com.example.androidproyecto2.api.apiServices.ValoracionsService;
import com.google.gson.Gson;
import com.google.type.DateTime;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerValoracionesDocenteFragment extends Fragment {

    private MainActivity activity;
    private Grup DadesGrup;
    private List<Grups_has_alumnes> grupsHasAlumnes = new ArrayList<>();
    private List<Grups_has_docents> grupsHasDocents = new ArrayList<>();
    private List<Usuari> alumnes = new ArrayList<>();
    private List<Usuari> docents = new ArrayList<>();
    private RecyclerView LstUsuarisGrup;
    private ViewPager vpMesesAño;

    private ArrayList<Mes> meses;



    private VerValoracionesDocenteFragment verValoracionesDocenteFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_valoraciones_docente, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        verValoracionesDocenteFragment = (VerValoracionesDocenteFragment) getParentFragment();

        activity.layout = "VerValoraciones";
        LstUsuarisGrup = view.findViewById(R.id.LstUsuarisGrup);
        cargarUsuariosGrupo();


        Button btnAtras = activity.toolbar.findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.VISIBLE);

        meses = getMeses();
        vpMesesAño = view.findViewById(R.id.vpMesesAño);


    }



    public void cargarUsuariosGrupo()
    {
        GrupService grupService = Api.getApi().create(GrupService.class);
        Call<Grup> grupCall = grupService.GetgrupsById(activity.idGrupo);

        grupCall.enqueue(new Callback<Grup>() {
            @Override
            public void onResponse(Call<Grup> call, Response<Grup> response) {
                switch (response.code())
                {
                    case 200:
                        DadesGrup = response.body();
                        grupsHasAlumnes = DadesGrup.getGrups_has_alumnes();
                        grupsHasDocents = DadesGrup.getGrups_has_docents();

                        //Coger todos los alumnos
                        for (Grups_has_alumnes gH: grupsHasAlumnes) {
                            alumnes.add(gH.getUsuaris());
                        }

                        //Coger todos los profesores
                        for (Grups_has_docents gH: grupsHasDocents) {
                            docents.add(gH.getUsuaris());
                        }

                        UsuarisValoracionsAdapter usuarisValoracionsAdapter = new UsuarisValoracionsAdapter(getContext(),alumnes,activity, vpMesesAño,meses);
                        LstUsuarisGrup.setHasFixedSize(true);
                        LstUsuarisGrup.setLayoutManager(new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL,
                                false));

                        LstUsuarisGrup.setAdapter(usuarisValoracionsAdapter);



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
            public void onFailure(Call<Grup> call, Throwable t) {
                Toast.makeText(activity,"error: "+ t.getMessage(),Toast.LENGTH_LONG).show();
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



    public int[] cogerColoresRandom(int cantidad)
    {
        // Esta variable se usará para llenar el array en la posición correspondiente
        int index = 0;

        // array que guarda los números aleatorios
        int [] coloresRandom = new int[cantidad];

        // Nuestro primer bucle que se ejecutará hasta que hayamos llenado el arrary
        while(index < cantidad) {
            // Variable que guarda el número aleatorio del array de colores del main
            int RandomColor = activity.coloresGraficos[new Random().nextInt(activity.coloresGraficos.length)];
            // Variable que indica si el RandomColor está repetido
            // asumimos que aún no está repetido y la establecemos a false
            boolean repetido = false;
            //Segundo bucle que se ejecutará siempre que el número no esté repetido
            while(!repetido) {
                // Bucle que recorre el array comparando el RandomColor con
                // cada uno de los items del array
                for(int i=0; i<index; i++) {
                    //realizamos la comparación
                    if(RandomColor == coloresRandom[i]) {
                        // si el número se repite, establecemos repetido=true
                        repetido = true;
                    }
                }
                // verificamos el estado del valor repetido. Si es false, significa
                // que hemos recorrido el array hasta la posición index sin encontrar
                // coincidencias
                if(!repetido) {
                    // almacenamos el valor propuesto ya que no está repetido
                    // incrementamos el índice
                    coloresRandom[index] = RandomColor;
                    index++;
                }
            }

        }


        return coloresRandom;

    }





}