package com.example.androidproyecto2.Fragments.MenuListasSkillsFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Fragments.FragmentsAlumno.ValoracionAlumnoFragment.ValoracionAlumnoFragment;
import com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment.ValoracionDocenteFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.List;

public class LlistasSkillsGrupAdapterViewPager extends PagerAdapter
{
    MainActivity activity;
    Context context;
    List<LlistaSkills> llistaSkills;
    Boolean esDocent;
    Usuari usuariValorat;
    FragmentManager mg;
    FragmentTransaction fragmentTransaction;

    public LlistasSkillsGrupAdapterViewPager(Context context, List<LlistaSkills> llistaSkills, Boolean esDocent, MainActivity activity, Usuari usuariValorat) {
        this.activity = activity;
        this.context = context;
        this.llistaSkills = llistaSkills;
        this.esDocent = esDocent;
        this.usuariValorat = usuariValorat;
    }




    @Override
    public int getCount() {
        return llistaSkills.size();
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.llistaskill_vpager_item,container,false);

        Button btnLlistaSkill = view.findViewById(R.id.btnLlistaSkill);
        LlistaSkills LS = llistaSkills.get(position);
        btnLlistaSkill.setText(LS.getNom());

        btnLlistaSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usuariValorat != null)
                {
                    activity.listaSkillsSelected = llistaSkills;

                    if (esDocent)
                    {
                        irAValoracionTipoProfesor();
                    }
                    else
                    {
                        irAValoracionTipoAlumno();
                    }
                }
                else
                {
                    Toast.makeText(context, "Selecciona un usuario a valorar", Toast.LENGTH_LONG).show();
                }


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


    private void irAValoracionTipoProfesor()
    {

        MainActivity activity = (MainActivity) context;
        mg = activity.getSupportFragmentManager();
        fragmentTransaction = mg.beginTransaction();
        ValoracionDocenteFragment valoracionDocenteFragment = new ValoracionDocenteFragment();
        fragmentTransaction.replace(R.id.FrContent,valoracionDocenteFragment);
        fragmentTransaction.commit();

    }


    private void irAValoracionTipoAlumno()
    {
        MainActivity activity = (MainActivity) context;
        mg = activity.getSupportFragmentManager();
        fragmentTransaction = mg.beginTransaction();
        ValoracionAlumnoFragment valoracionDocenteFragment = new ValoracionAlumnoFragment();
        fragmentTransaction.replace(R.id.FrContent,valoracionDocenteFragment);
        fragmentTransaction.commit();
    }




}