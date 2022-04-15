package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.MenuListasSkillsFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioFragment extends Fragment
{
    List<Grup> grups;
    //ArrayList<Grup> Grups;
    FragmentManager mg;
    FragmentTransaction fragmentTransaction;
    MainActivity activity;
    ViewPager VpGrups;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        mg = getActivity().getSupportFragmentManager();

        llenarGrupos();
    }


    public void llenarGrupos()
    {
        GrupService grupService = Api.getApi().create(GrupService.class);
        Call<List<Grup>> listCall = grupService.GetGrups();

        listCall.enqueue(new Callback<List<Grup>>() {
            @Override
            public void onResponse(Call<List<Grup>> call, Response<List<Grup>> response) {
                switch (response.code())
                {
                    case 200:
                        grups = response.body();

                        VpGrups = view.findViewById(R.id.VpGrups);
                        VpGrups.setClipToPadding(false);
                        VpGrups.setPadding(100, 0, 100, 0);
                        VpGrups.setPageMargin(100);

                        GrupsAdapterViewPager grupsAdapterViewPager = new GrupsAdapterViewPager(getContext(),grups);
                        VpGrups.setAdapter(grupsAdapterViewPager);

                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Grup>> call, Throwable t) {
                Toast.makeText(activity,"error: "+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }




}