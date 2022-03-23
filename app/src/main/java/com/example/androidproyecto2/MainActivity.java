package com.example.androidproyecto2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Ventana;
import com.example.androidproyecto2.Fragments.MenuPrincipalFragment.MenuPrincipalFragment;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    View toolbar;
    List<Grup> grups;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ocultarBarrasDispositivo();
        toolbar = findViewById(R.id.toolbar);
        Button btnCerrarSession = toolbar.findViewById(R.id.btnLogout);





        FragmentManager mgr = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = mgr.beginTransaction();

        MenuPrincipalFragment menuPrincipalFragment = new MenuPrincipalFragment();

        fragmentTransaction.replace(R.id.FrContent, menuPrincipalFragment);
        fragmentTransaction.commit();


        btnCerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }


    public void ocultarBarrasDispositivo()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(Ventana.WINDOW_SETTINGS);
    }


    public void llenarGrupos()
    {
        Grup grup1 = new Grup(1,"Alumnat",true);
        Grup grup2 = new Grup(2,"Docent",true);
        Grup grup3 = new Grup(3,"DAW",true);
        Grup grup4 = new Grup(4,"DAM",true);

        grups.add(grup1);
        grups.add(grup2);
        grups.add(grup3);
        grups.add(grup4);

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