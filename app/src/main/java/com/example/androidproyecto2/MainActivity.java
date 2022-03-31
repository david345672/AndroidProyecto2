package com.example.androidproyecto2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.biometrics.BiometricManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.androidproyecto2.Clases.CalendarioAdapter;
import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Ventana;
import com.example.androidproyecto2.Fragments.LoginFragment.LoginFragment;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.MenuListasSkillsFragment;
import com.example.androidproyecto2.Fragments.MenuPrincipalFragment.MenuPrincipalFragment;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.time.LocalDateTime;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CalendarioAdapter.OnItemListener{

    public View toolbar;
    List<Grup> grups;
    public String layout = "Login";
    FragmentManager mgr;
    FragmentTransaction fragmentTransaction;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ocultarBarrasDispositivo();
        toolbar = findViewById(R.id.toolbar);
        Button btnAtras = toolbar.findViewById(R.id.btnAtras);
        Button btnCerrarSession = toolbar.findViewById(R.id.btnLogout);
        initWidgets();

        selectedDate = LocalDate.now();
        setMonthView();


        mgr = getSupportFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        MenuPrincipalFragment menuPrincipalFragment = new MenuPrincipalFragment();

        fragmentTransaction.replace(R.id.FrContent, menuPrincipalFragment);
        fragmentTransaction.commit();


        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (layout.equals("MenuListaSkills"))
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarioAdapter calendarioAdapter = new CalendarioAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarioAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++)
        {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){

                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRV);
        monthYearText = findViewById(R.id.monthyearTV);

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


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void  previousMonthAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view){
        selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText)
    {
        if(dayText.equals(""))
        {
            String message = "Selected Date" + dayText + "" + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}