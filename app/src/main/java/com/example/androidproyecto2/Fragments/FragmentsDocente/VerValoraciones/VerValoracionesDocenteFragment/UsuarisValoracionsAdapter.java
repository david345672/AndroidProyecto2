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

import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ArrayList;
import java.util.List;

public class UsuarisValoracionsAdapter extends RecyclerView.Adapter<UsuarisValoracionsAdapter.ViewHolder>
{
    private MainActivity activity;
    private VerValoracionesDocenteFragment verValoracionesDocenteFragment;
    private int idUsuariSelected;
    private Context context;
    private List<Usuari> usuaris;
    private ViewPager vpMesesAño;


    private  Boolean isRadioButtonCheched = false;
    private  int SelectedPosition = -1;


    public UsuarisValoracionsAdapter(Context context, List<Usuari> usuaris,MainActivity activity,VerValoracionesDocenteFragment verValoracionesDocenteFragment) {
        this.context = context;
        this.usuaris = usuaris;
        this.activity = activity;
        this.verValoracionesDocenteFragment = verValoracionesDocenteFragment;
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
                    activity.usuariSeleccionat = usuaris.get(SelectedPosition);
                    List<Valoracio> valoraciones = new ArrayList<>();
                    
                    valoraciones = cargarValoracionesUsuario();
                    Toast.makeText(activity, valoraciones.get(0).toString(), Toast.LENGTH_SHORT).show();

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




    public List<Valoracio> cargarValoracionesUsuario()
    {
        List<Valoracio> valoracionsUsuari = new ArrayList<>();

        for (Valoracio val: activity.valoracions) {
            if (val.getUsuari_valorat_id() == activity.usuariSeleccionat.getId())
            {
                valoracionsUsuari.add(val);
            }
        }

        return valoracionsUsuari;
    }




}


