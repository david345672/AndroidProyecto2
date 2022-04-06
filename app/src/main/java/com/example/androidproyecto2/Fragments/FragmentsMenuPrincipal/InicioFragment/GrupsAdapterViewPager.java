package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Fragments.FragmentsAlumno.VerValoracionAlumnoFragment.VerValoracionAlumnoFragment;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.MenuListasSkillsFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.List;

public class GrupsAdapterViewPager extends PagerAdapter
{
    Context context;
    List<Grup> grups;
    //ArrayList<Grup> grups;
    FragmentManager mg;
    FragmentTransaction fragmentTransaction;


    public GrupsAdapterViewPager(Context context, List grups)
    {
        this.context = context;
        this.grups = grups;
    }

//    public GrupsAdapterViewPager(Context context, ArrayList grups)
//    {
//        this.context = context;
//        this.grups = grups;
//    }

    @Override
    public int getCount() {
        return grups.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.grupo_item,container,false);

        TextView lblNombreGrupo = view.findViewById(R.id.lblNombreGrupo);
        Button btnHacerObservaciones = view.findViewById(R.id.btnHacerObservaciones);
        Button btnVerValoraciones = view.findViewById(R.id.btnVerValoraciones);

        Grup grup = grups.get(position);
        String nombre = grup.getNom();

        lblNombreGrupo.setText(nombre);

        btnHacerObservaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAMenuSkills();
                MainActivity activity = (MainActivity) context;
                activity.idGrupo = grup.getId();
            }
        });


        btnVerValoraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) context;
                activity.idGrupo = grup.getId();

                //activity.layout = "MenuListaSkills";
                mg = activity.getSupportFragmentManager();
                fragmentTransaction = mg.beginTransaction();
                VerValoracionAlumnoFragment vervaloracionalumno = new VerValoracionAlumnoFragment();
                fragmentTransaction.replace(R.id.FrContent,vervaloracionalumno);
                fragmentTransaction.commit();


            }
        });



        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    private void irAMenuSkills()
    {

        MainActivity activity = (MainActivity) context;
        //activity.layout = "MenuListaSkills";
        mg = activity.getSupportFragmentManager();
        fragmentTransaction = mg.beginTransaction();
        MenuListasSkillsFragment menuListasSkillsFragment = new MenuListasSkillsFragment();
        fragmentTransaction.replace(R.id.FrContent,menuListasSkillsFragment);
        fragmentTransaction.commit();

    }


}
