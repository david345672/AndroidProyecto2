package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.CustomCalendar.Año;
import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Grups_has_alumnes;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment.GrupsAdapterViewPager;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerValoracionesDocenteFragment extends Fragment {

    private MainActivity activity;
    private Grup DadesGrup;
    private List<Grups_has_alumnes> grupsHasAlumnes = new ArrayList<>();
    private List<Usuari> usuaris = new ArrayList<>();
    private RecyclerView LstUsuarisGrup;
    private ViewPager vpMesesAño;

    private ArrayList<Mes> meses;

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
        activity.layout = "VerValoraciones";
        LstUsuarisGrup = view.findViewById(R.id.LstUsuarisGrup);
        cargarUsuariosListasSills();

        Button btnAtras = activity.toolbar.findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.VISIBLE);

        meses = getMeses();
        vpMesesAño = view.findViewById(R.id.vpMesesAño);

        vpMesesAño.setClipToPadding(false);

        CalendarMesesAdapter calendarMesesAdapter = new CalendarMesesAdapter(getContext(),meses);
        vpMesesAño.setAdapter(calendarMesesAdapter);



    }



    public void cargarUsuariosListasSills()
    {
        GrupService grupService = Api.getApi().create(GrupService.class);
        Call<Grup> grupCall = grupService.GetgrupsById(2);

        grupCall.enqueue(new Callback<Grup>() {
            @Override
            public void onResponse(Call<Grup> call, Response<Grup> response) {
                switch (response.code())
                {
                    case 200:
                        DadesGrup = response.body();
                        grupsHasAlumnes = DadesGrup.getGrups_has_alumnes();

                        for (Grups_has_alumnes gH: grupsHasAlumnes) {
                            usuaris.add(gH.getUsuaris());
                        }

                        UsuarisAdapter usuarisAdapter = new UsuarisAdapter(getContext(),usuaris,activity);
                        LstUsuarisGrup.setHasFixedSize(true);
                        LstUsuarisGrup.setLayoutManager(new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.VERTICAL,
                                false));

                        LstUsuarisGrup.setAdapter(usuarisAdapter);



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


    public ArrayList<Mes> getMeses()
    {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);

        int [] dias = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        int [] dias2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};
        int [] dias3 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        int [] dias4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        int [] dias5 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        int [] dias6 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        int [] dias7 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        int [] dias8 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        int [] dias9 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        int [] dias10 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        int [] dias11 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        int [] dias12 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

        ArrayList<Mes> meses = new ArrayList<Mes>();

        meses.add(new Mes(anio,"Enero",dias));
        meses.add(new Mes(anio,"Febrero",dias2));
        meses.add(new Mes(anio,"Marzo",dias3));
        meses.add(new Mes(anio,"Abril",dias4));
        meses.add(new Mes(anio,"Mayo",dias5));
        meses.add(new Mes(anio,"Junio",dias6));
        meses.add(new Mes(anio,"Julio",dias7));
        meses.add(new Mes(anio,"Agosto",dias8));
        meses.add(new Mes(anio,"Septiembre",dias9));
        meses.add(new Mes(anio,"Octubre",dias10));
        meses.add(new Mes(anio,"Noviembre",dias11));
        meses.add(new Mes(anio,"Diciembre",dias12));


        return meses;
    }




}