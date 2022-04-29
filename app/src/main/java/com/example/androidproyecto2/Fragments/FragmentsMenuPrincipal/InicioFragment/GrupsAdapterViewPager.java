package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Fragments.FragmentsAlumno.VerValoracionAlumnoFragment.VerValoracionAlumnoFragment;
import com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment.VerValoracionesDocenteFragment;
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
    Animation buttonUp, buttonDown;

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

        buttonUp = AnimationUtils.loadAnimation(context,R.anim.button_up);
        buttonDown = AnimationUtils.loadAnimation(context,R.anim.button_down);

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


        btnHacerObservaciones.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        btnHacerObservaciones.startAnimation(buttonUp);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        btnHacerObservaciones.startAnimation(buttonDown);
                        break;
                }
                return false;
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

                //MainActivity activity = (MainActivity) context;
                activity.idGrupo = grup.getId();
                //Si es docente ire a un menu diferente
                if (activity.esDocent)
                {
                    irAMenuVerValoracionesDocente();
                } else
                {
                    irAMenuVerValoracionesAlumno();
                }

            }
        });


        btnVerValoraciones.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        btnVerValoraciones.startAnimation(buttonUp);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        btnVerValoraciones.startAnimation(buttonDown);
                        break;
                }
                return false;
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


    private void irAMenuVerValoracionesDocente()
    {

        MainActivity activity = (MainActivity) context;
        mg = activity.getSupportFragmentManager();
        fragmentTransaction = mg.beginTransaction();
        VerValoracionesDocenteFragment verValoracionesDocenteFragment = new VerValoracionesDocenteFragment();
        fragmentTransaction.replace(R.id.FrContent,verValoracionesDocenteFragment);
        fragmentTransaction.commit();

    }

    private void irAMenuVerValoracionesAlumno()
    {
        MainActivity activity = (MainActivity) context;
        mg = activity.getSupportFragmentManager();
        fragmentTransaction = mg.beginTransaction();
        VerValoracionAlumnoFragment verValoracionAlumnoFragment = new VerValoracionAlumnoFragment();
        fragmentTransaction.replace(R.id.FrContent,verValoracionAlumnoFragment);
        fragmentTransaction.commit();
    }



}
