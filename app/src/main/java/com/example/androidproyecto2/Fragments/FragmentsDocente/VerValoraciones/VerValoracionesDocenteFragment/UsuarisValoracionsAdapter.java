package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

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
import java.util.List;

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
                    //activity.usuariSeleccionat = usuaris.get(SelectedPosition);
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
        holder.rdbUsuari.setText(usuaris.get(position).getNomUsuari());

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

                        //Coger las valoraciones que ha hecho el usuario
                        userSelected.getValoracions();

                        vpMesesAño.setClipToPadding(false);
                        CalendarMesesAdapter calendarMesesAdapter = new CalendarMesesAdapter(context,meses, activity, userSelected.getValoracions());
                        vpMesesAño.setAdapter(calendarMesesAdapter);



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






}


