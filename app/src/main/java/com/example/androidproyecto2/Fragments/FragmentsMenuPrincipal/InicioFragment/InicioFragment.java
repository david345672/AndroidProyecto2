package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.ViewPagerListener;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.TutorialFragment.TutorialFragment;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.MenuListasSkillsFragment;
import com.example.androidproyecto2.Fragments.MenuPrincipalFragment.MenuPrincipalFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment
{
    List<Grup> grups;
    ArrayList<Grup> Grups;
    FragmentManager mg;
    FragmentTransaction fragmentTransaction;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Grups = llenarGrupos();
        mg = getActivity().getSupportFragmentManager();
        ViewPager VpGrups = view.findViewById(R.id.VpGrups);
        VpGrups.setClipToPadding(false);
        VpGrups.setPadding(100, 0, 100, 0);
        VpGrups.setPageMargin(100);

        GrupsAdapterViewPager grupsAdapterViewPager = new GrupsAdapterViewPager(getContext(),Grups);
        VpGrups.setAdapter(grupsAdapterViewPager);

        Button btnHacerObservacionPropia = view.findViewById(R.id.btnHacerObservacionPropia);
        Button btnVerValoracionesPropias = view.findViewById(R.id.btnVerValoracionesPropias);


        btnHacerObservacionPropia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(),"dfsd",Toast.LENGTH_LONG).show();
                IrAMenuLS();

            }
        });






    }

    private void IrAMenuLS() {

        fragmentTransaction = mg.beginTransaction();
        MenuListasSkillsFragment menuListasSkillsFragment = new MenuListasSkillsFragment();
        fragmentTransaction.replace(R.id.FrContent,menuListasSkillsFragment);
        fragmentTransaction.commit();
    }



    public ArrayList<Grup> llenarGrupos()
    {
        ArrayList<Grup> grups1 = new ArrayList<Grup>();

        Grup grup1 = new Grup(1,"Grupo 1",true);
        Grup grup2 = new Grup(2,"Grupo 2",true);
        Grup grup3 = new Grup(3,"Grupo 3",true);
        Grup grup4 = new Grup(4,"Grupo 4",true);

        grups1.add(grup1);
        grups1.add(grup2);
        grups1.add(grup3);
        grups1.add(grup4);

        return grups1;

//        GrupService grupService = Api.getApi().create(GrupService.class);
//        Call<List<Grup>> listCall = grupService.GetGrups();
//
//        listCall.enqueue(new Callback<List<Grup>>() {
//            @Override
//            public void onResponse(Call<List<Grup>> call, Response<List<Grup>> response) {
//                switch (response.code())
//                {
//                    case 200:
//                        grups = response.body();
//                        Toast.makeText(MainActivity.this,grups.get(0).getNom(),Toast.LENGTH_LONG).show();
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Grup>> call, Throwable t) {
//
//            }
//        });

    }




}