package com.example.androidproyecto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Clases.Ventana;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment.ColoresGraficosFragment;
import com.example.androidproyecto2.Fragments.LoginFragment.LoginFragment;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.MenuListasSkillsFragment;
import com.example.androidproyecto2.Fragments.MenuPrincipalFragment.MenuPrincipalFragment;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.example.androidproyecto2.api.apiServices.ValoracionsService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public View toolbar;

    //Variables de seleccio
    public List<Grup> grups;
    public Skill skillSelected;
    public int idUsuariSelected;
    public LlistaSkills llistaSkillSelected;
    public Usuari usuariLogin;
    public Usuari usuariValorat;
    public Usuari usuariSeleccionat;
    public String layout = "Login";
    public Boolean esDocent = false;
    FragmentManager mgr;
    FragmentTransaction fragmentTransaction;
    public int idGrupo = -1;

    public LinearLayout fondo;

    //Array de Colores Para Graficos
    public int [] coloresGraficos;

    public List<Valoracio> valoracions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ocultarBarrasDispositivo();
        toolbar = findViewById(R.id.toolbar);
        fondo = findViewById(R.id.fondo);
        //loadTheme();

        //CargarUsuarioLogin();
    
        Button btnAtras = toolbar.findViewById(R.id.btnAtras);
        Button btnCerrarSession = toolbar.findViewById(R.id.btnLogout);
        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        coloresGraficos = llenarColoresGraficos();

        LoginFragment loginFragment = new LoginFragment();
        ColoresGraficosFragment coloresGraficosFragment = new ColoresGraficosFragment();

        fragmentTransaction.replace(R.id.FrContent, loginFragment);
        fragmentTransaction.commit();



        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        //CogerTodasLasValoraciones();
        //MenuPrincipalFragment menuPrincipalFragment = new MenuPrincipalFragment();

       // fragmentTransaction.replace(R.id.FrContent, menuPrincipalFragment);
       // fragmentTransaction.commit();

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (layout.equals("MenuListaSkills"))
                {
                    VolverAMenu();
                    btnAtras.setVisibility(View.INVISIBLE);

                }
                else if(layout.equals("HacerValoracion"))
                {
                    VolverAMenuListasSkills();
                }
                else if(layout.equals("VerValoraciones"))
                {
                    VolverAMenu();
                    btnAtras.setVisibility(View.INVISIBLE);
                }

            }
        });


        btnCerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irALogin();
                btnAtras.setVisibility(View.INVISIBLE);
                btnCerrarSession.setVisibility(View.INVISIBLE);
            }
        });



    }


    private int[] llenarColoresGraficos()
    {
        int []colores = {
        Color.parseColor("#FF0800"),Color.parseColor("#00FF40"),Color.parseColor("#EDF400"),Color.parseColor("#FFC500"),
        Color.parseColor("#0044FF"),Color.parseColor("#389EA2"),Color.parseColor("#BE00FF"),Color.parseColor("#6F4C00"),
        Color.parseColor("#0F9900"),Color.parseColor("#83945A"),Color.parseColor("#00F9FF"),Color.parseColor("#FF00B9"),
        Color.parseColor("#00FFB4"),Color.parseColor("#ABFF00"),Color.parseColor("#08AA7C"),Color.parseColor("#848180"),
        };

        return colores;
    }


    public void irALogin()
    {
        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        LoginFragment loginFragment = new LoginFragment();

        fragmentTransaction.replace(R.id.FrContent, loginFragment);
        fragmentTransaction.commit();
    }


    public void VolverAMenu()
    {
        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        MenuPrincipalFragment menuPrincipalFragment = new MenuPrincipalFragment();

        fragmentTransaction.replace(R.id.FrContent, menuPrincipalFragment);
        fragmentTransaction.commit();

    }


    public void VolverAMenuListasSkills()
    {
        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        MenuListasSkillsFragment menuListasSkillsFragment = new MenuListasSkillsFragment();

        fragmentTransaction.replace(R.id.FrContent, menuListasSkillsFragment);
        fragmentTransaction.commit();
    }

    public void ocultarBarrasDispositivo()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(Ventana.WINDOW_SETTINGS);
    }



    public void loadTheme()
    {
        SharedPreferences loadPreferences = getSharedPreferences("config_theme", MODE_PRIVATE);
        String actualTema = loadPreferences.getString("theme","DEFAULT");

        if(actualTema.equals("DEFAULT"))
        {
            updateTheme("DEFAULT", "#212121", "#37474f");
        }
        else if(actualTema.equals("DARK"))
        {
            updateTheme("DARK", "#FFFFFF", "#4C6BFF");
        }

    }


    public void updateTheme(String key, String c1, String c2)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("config_theme", MODE_PRIVATE);
        SharedPreferences.Editor ObjEditor = sharedPreferences.edit();
        ObjEditor.putString("theme",key);
        ObjEditor.commit();

        fondo.setBackgroundColor(Color.parseColor(c1));
        toolbar.setBackgroundColor(Color.parseColor(c2));

    }



}
