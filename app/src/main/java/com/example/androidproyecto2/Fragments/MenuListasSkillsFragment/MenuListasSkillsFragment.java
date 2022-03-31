package com.example.androidproyecto2.Fragments.MenuListasSkillsFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Grups_has_alumnes;
import com.example.androidproyecto2.Clases.Grups_has_llistes_skills;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.globales;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment.GrupsAdapterViewPager;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;
import com.example.androidproyecto2.api.apiServices.GrupsHasAlumnesService;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuListasSkillsFragment extends Fragment{

    public globales gb;
    MainActivity activity;

    List<Grups_has_alumnes> grupsHasAlumnes = new ArrayList<>();
    List<Grups_has_llistes_skills> grupsHasLlistesSkills = new ArrayList<>();
    List<Usuari> usuarisApi;
    List<Usuari> usuaris = new ArrayList<>();
    List<LlistaSkills> llistaSkills = new ArrayList<>();
    List<Skill> Skills = new ArrayList<>();

    int idListaSelcted;

    Grup DadesGrup;
    private RecyclerView ListUsuarisGrup;
    View view;
    ViewPager vpLlistes;
    ViewPager vpSkills;
    RadioGroup rdgUsuaris;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu_listas_skills, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        activity.layout = "MenuListaSkills";



        ListUsuarisGrup = view.findViewById(R.id.ListUsuarisGrup);

        cargarUsuariosListasSills();

        Button btnAtras = activity.toolbar.findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.VISIBLE);



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

                        UsuarisAdapter usuarisAdapter = new UsuarisAdapter(usuaris,activity);
                        ListUsuarisGrup.setHasFixedSize(true);
                        ListUsuarisGrup.setLayoutManager(new LinearLayoutManager(getActivity(),
                                LinearLayoutManager.HORIZONTAL,
                                false));

                        ListUsuarisGrup.setAdapter(usuarisAdapter);

                        grupsHasLlistesSkills = DadesGrup.getGrups_has_llistes_skills();

                        for (Grups_has_llistes_skills gHLlistes: grupsHasLlistesSkills) {
                            llistaSkills.add(gHLlistes.getLlistes_skills());
                        }
                        vpLlistes = view.findViewById(R.id.VpLlistesSkills);
                        vpLlistes.setClipToPadding(false);
                        vpLlistes.setPadding(100, 0, 100, 0);
                        vpLlistes.setPageMargin(100);
                        LlistasSkillsGrupAdapterViewPager llistasSkillsGrupAdapterViewPager = new LlistasSkillsGrupAdapterViewPager(getContext(),llistaSkills,activity.esDocent,activity,activity.usuariValorat);
                        vpLlistes.setAdapter(llistasSkillsGrupAdapterViewPager);

                        idListaSelcted = llistaSkills.get(0).getId();
                        CargarSkills(0);

                        vpLlistes.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            public void onPageScrollStateChanged(int state) {}
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

                            public void onPageSelected(int position) {

                                idListaSelcted = llistaSkills.get(position).getId();
                                CargarSkills(position);
                            }
                        });



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


    public void CargarSkills(int pos)
    {
        vpSkills = view.findViewById(R.id.VpSkills);
        vpSkills.setClipToPadding(false);
        vpSkills.setPadding(300, 0, 300, 0);
        vpSkills.setPageMargin(300);

        SkillsListsSkillAdpterViewPager skillsListsSkillAdpterViewPager = new SkillsListsSkillAdpterViewPager(getContext(),llistaSkills.get(pos).getSkills(),activity.esDocent,activity,idListaSelcted,activity.usuariValorat);
        vpSkills.setAdapter(skillsListsSkillAdpterViewPager);
    }






}