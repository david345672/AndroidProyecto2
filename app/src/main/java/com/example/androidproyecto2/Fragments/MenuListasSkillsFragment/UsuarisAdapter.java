package com.example.androidproyecto2.Fragments.MenuListasSkillsFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.R;

import java.util.List;

public class UsuarisAdapter extends RecyclerView.Adapter<UsuarisAdapter.ViewHolder>
{
    private List<Usuari> usuaris;

    public UsuarisAdapter(List<Usuari> usuaris) {
        this.usuaris = usuaris;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        RadioButton rdbUsuari;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rdbUsuari = itemView.findViewById(R.id.rdbUsuari);
        }

        void bindUsuari(Usuari usuari)
        {
            rdbUsuari.setText(usuari.getNom());
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.usuario_item,parent,false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindUsuari(usuaris.get(position));
    }

    public int getItemCount()
    {
        return usuaris.size();
    }





}
