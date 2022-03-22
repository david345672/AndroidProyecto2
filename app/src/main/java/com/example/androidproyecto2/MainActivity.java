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

import com.example.androidproyecto2.Fragments.MenuPrincipalFragment.MenuPrincipalFragment;

public class MainActivity extends AppCompatActivity {

    View toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("")
                        .setTitle("¿Seguro que quieres cerrar la session?");

                AlertDialog dialog = builder.create();
                dialog.show();

                 */
            }
        });



    }

}