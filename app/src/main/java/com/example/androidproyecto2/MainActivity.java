package com.example.androidproyecto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Ventana;
import com.example.androidproyecto2.Fragments.LoginFragment.LoginFragment;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.MenuListasSkillsFragment;
import com.example.androidproyecto2.Fragments.MenuPrincipalFragment.MenuPrincipalFragment;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.google.gson.Gson;

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

    public String layout = "Login";
    public Boolean esDocent = true;
    FragmentManager mgr;
    FragmentTransaction fragmentTransaction;
    public int idGrupo = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ocultarBarrasDispositivo();
        toolbar = findViewById(R.id.toolbar);

        //CargarUsuarioLogin();
        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();


        LoginFragment loginFragment = new LoginFragment();

        fragmentTransaction.replace(R.id.FrContent, loginFragment);
        fragmentTransaction.commit();


        Button btnAtras = toolbar.findViewById(R.id.btnAtras);
        Button btnCerrarSession = toolbar.findViewById(R.id.btnLogout);

        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

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



}
