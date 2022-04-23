package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarisValoracionsAdapter extends RecyclerView.Adapter<UsuarisValoracionsAdapter.ViewHolder>
{
    private MainActivity activity;
    private Context context;
    private List<Usuari> usuaris;
    private ViewPager vpMesesAño;
    private Usuari userSelected;
    private ArrayList<Mes> meses;


    private  Boolean isRadioButtonCheched = false;
    private  int SelectedPosition = -1;


    public UsuarisValoracionsAdapter(Context context,List<Usuari> usuaris,MainActivity activity, ViewPager vpMesesAño, ArrayList<Mes> meses) {
        this.context = context;
        this.usuaris = usuaris;
        this.activity = activity;
        this.vpMesesAño = vpMesesAño;
        this.meses = meses;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        RadioButton rdbUsuari;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rdbUsuari = itemView.findViewById(R.id.rdbUsuari);


            rdbUsuari.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();


                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                    LayoutInflater inflater = activity.getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.usuario_ver_valoracion_docente_item, null);
                    dialogBuilder.setView(dialogView);
                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();


                    getUsuario(usuaris.get(SelectedPosition).getId(), view);

                }
            });


        }

    }


    @Override
    public UsuarisValoracionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.usuario_item,parent,false);

        return new UsuarisValoracionsAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(UsuarisValoracionsAdapter.ViewHolder holder, int position) {
        holder.rdbUsuari.setText(usuaris.get(position).getNom() + " " + usuaris.get(position).getCognoms());

        holder.rdbUsuari.setChecked(position == SelectedPosition);

        holder.rdbUsuari.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    SelectedPosition = holder.getAdapterPosition();

                }
            }
        });

    }

    public int getItemCount()
    {
        return usuaris.size();
    }



    public void getUsuario(int id, View view)
    {

        UsuarisService userService = Api.getApi().create(UsuarisService.class);
        Call<Usuari> userCall = userService.Getusuaris(id);

        userCall.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                switch (response.code())
                {
                    case 200:
                        userSelected = response.body();

                        //Coger las valoraciones que le han hecho al usuario de toda la gente incluido profesores
                        userSelected.getValoracions();

                        cargarVPagerMesesValoraciones(userSelected.getValoracions());



                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(activity, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(activity,"Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {

            }
        });

    }


    //Coger todos los meses sin repedidos de la valoraciones
    public HashSet<Mes> cogerMesesDeValoraciones(ArrayList<Mes> meses, List<Valoracio> valoracions)
    {
        HashSet<Mes> mesesValoracion = new HashSet<>();

        for (int i = 0; i < valoracions.size(); i++)
        {
            char [] numFecha = valoracions.get(i).getData().toCharArray();
            String mesStr = new StringBuilder(numFecha[4]).append(numFecha[5]).toString();
            int mes = Integer.parseInt(mesStr);

            for (int j = 0; j < meses.size(); j++)
            {
                if (mes - 1 == j)
                {
                    mesesValoracion.add(meses.get(j));
                }
            }

        }


       return mesesValoracion;
    }


    public void cargarVPagerMesesValoraciones(List<Valoracio> valoracions)
    {
        if (valoracions.size() != 0)
        {

            HashSet<Mes> mesesValoracion = cogerMesesDeValoraciones(meses,valoracions);

            //ArrayList<Valoracio> valoracionesDeMes = cogerValoracionesDeMes(meses,userSelected.getValoracions());
            //De los meses repetidos convertirlo a un arrayList Normal
            ArrayList<Mes> mesesDeValoraciones = new ArrayList<>(mesesValoracion);
            Collections.sort(mesesDeValoraciones);
            vpMesesAño.setClipToPadding(false);
            CalendarMesesAdapter calendarMesesAdapter = new CalendarMesesAdapter(context,mesesDeValoraciones, activity, valoracions);
            vpMesesAño.setAdapter(calendarMesesAdapter);

        }
        else
        {
            vpMesesAño.setAdapter(null);
        }
    }



}


