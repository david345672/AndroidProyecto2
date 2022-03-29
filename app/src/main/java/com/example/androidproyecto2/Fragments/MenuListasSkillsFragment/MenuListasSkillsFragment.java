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
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Grups_has_alumnes;
import com.example.androidproyecto2.Clases.Grups_has_llistes_skills;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
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

    Grup DadesGrup;
    private RecyclerView ListUsuarisGrup;
    View view;
    ViewPager vpLlistes;

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

        //gb = new globales();
        //Grups_has_alumnes test = new Grups_has_alumnes();
        //cargarUsuarios(gb);
        ListUsuarisGrup = view.findViewById(R.id.ListUsuarisGrup);

        cargarUsuariosListasSills();

        Button btnAtras = activity.toolbar.findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.VISIBLE);






        //Toast.makeText(activity, gb.grupsHasAlumnes.toString(), Toast.LENGTH_SHORT).show();

//        if (gb.grupsHasAlumnes != null)
//        {
//            Toast.makeText(activity, "id: " + gb.grupsHasAlumnes.get(0).getUsuaris_id(), Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(activity, "Error: ", Toast.LENGTH_LONG).show();
//        }


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

                        UsuarisAdapter usuarisAdapter = new UsuarisAdapter(usuaris);
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

                        LlistasSkillsGrupAdapterViewPager llistasSkillsGrupAdapterViewPager = new LlistasSkillsGrupAdapterViewPager(getContext(),llistaSkills);

                        vpLlistes.setAdapter(llistasSkillsGrupAdapterViewPager);

                        vpLlistes.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            public void onPageScrollStateChanged(int state) {}
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

                            public void onPageSelected(int position) {
                                Toast.makeText(activity, "id_LlistaSelect: " + llistaSkills.get(position).getId(), Toast.LENGTH_SHORT).show();
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




//    public void cargarUsuarios(globales gb)
//    {
//        Grups_has_alumnes test2 = new Grups_has_alumnes();
//        GrupsHasAlumnesService grupsHasAlumnesService = Api.getApi().create(GrupsHasAlumnesService.class);
//        Call<List<Grups_has_alumnes>> listCall = grupsHasAlumnesService.Getgrups_has_alumnes();
//
//        listCall.enqueue(new Callback<List<Grups_has_alumnes>>() {
//            @Override
//            public void onResponse(Call<List<Grups_has_alumnes>> call, Response<List<Grups_has_alumnes>> response) {
//                onResponseG(call,response,gb);
//
//            }
//
//
//            public void onResponseG(Call<List<Grups_has_alumnes>> call, Response<List<Grups_has_alumnes>> response, globales gb) {
//                switch (response.code())
//                {
//                    case 200:
//                        gb.setGrupsHasAlumnes(response.body());
//
////                        grupsHasAlumnes = response.body();
////
////                        Toast.makeText(activity, Integer.toString(gb.grupsHasAlumnes.get(0).getUsuaris_id()), Toast.LENGTH_LONG).show();
////                        Grups_has_alumnes test = new Grups_has_alumnes(1,2,3);
////                        test = gb.grupsHasAlumnes.get(2);
//
//
//                        break;
//                    default:
//                        break;
//                }
//
//            }
//
//
//            @Override
//            public void onFailure(Call<List<Grups_has_alumnes>> call, Throwable t) {
//                //Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//    }


//    public void cargarUsuariosDeApi()
//    {
//        UsuarisService usuarisService = Api.getApi().create(UsuarisService.class);
//        Call<List<Usuari>> listCall = usuarisService.Getusuaris();
//
//        listCall.enqueue(new Callback<List<Usuari>>() {
//            @Override
//            public void onResponse(Call<List<Usuari>> call, Response<List<Usuari>> response) {
//                    switch (response.code())
//                    {
//                        case 200:
//                            usuarisApi = response.body();
//
//                            //Toast.makeText(activity, usuarisApi.get(0).getNom(), Toast.LENGTH_SHORT).show();
//
//
//
//                            break;
//                        default:
//                            break;
//                    }
//            }
//
//            @Override
//            public void onFailure(Call<List<Usuari>> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//    }



//                    switch (response.code())
//    {
//        case 200:
//            gb.setGrupsHasAlumnes(response.body());
//
////                        grupsHasAlumnes = response.body();
////
////                        Toast.makeText(activity, Integer.toString(gb.grupsHasAlumnes.get(0).getUsuaris_id()), Toast.LENGTH_LONG).show();
////                        Grups_has_alumnes test = new Grups_has_alumnes(1,2,3);
////                        test = gb.grupsHasAlumnes.get(2);
//
//
//            break;
//        default:
//            break;





}