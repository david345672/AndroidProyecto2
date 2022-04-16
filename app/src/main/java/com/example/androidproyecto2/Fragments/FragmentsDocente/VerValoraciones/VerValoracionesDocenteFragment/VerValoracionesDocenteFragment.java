package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

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
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment.GrupsAdapterViewPager;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;
import com.google.gson.Gson;

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

        CalendarMesesAdapter calendarMesesAdapter = new CalendarMesesAdapter(getContext(),meses, activity);
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


        ArrayList<Mes> meses = new ArrayList<Mes>();
        String[] months = new DateFormatSymbols().getMonths();
//        Calendar mycal = new GregorianCalendar(anio, 1,2);
//        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < months.length; i++)
        {
            Calendar mycal = new GregorianCalendar(anio, i,1);
            int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

            ArrayList<Dia> dias = new ArrayList<>();

            for (int d = 1; d <= daysInMonth; d++)
            {

                String dateString = String.format("%d-%d-%d", anio, i + 1, d);
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
                    String dayOfWeek = new SimpleDateFormat("EEEE", new Locale("es", "ES")).format(date);

                    Dia dia = new Dia(d,dayOfWeek);
                    dias.add(dia);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            Mes mes = new Mes(anio,months[i],dias);

            meses.add(mes);

        }


        return meses;
    }




}