package com.example.androidproyecto2.Fragments.MenuListasSkillsFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.List;

public class UsuarisAdapter extends RecyclerView.Adapter<UsuarisAdapter.ViewHolder>
{
    private Usuari usuari;
    private Context context;
    private List<Usuari> usuaris;
    private  Boolean isRadioButtonCheched = false;
    private  int SelectedPosition = -1;


    public UsuarisAdapter(Context context, List<Usuari> usuaris, Usuari usuari) {
        this.context = context;
        this.usuaris = usuaris;
        this.usuari = usuari;
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
                    usuari = usuaris.get(SelectedPosition);

                }
            });


        }

//        void bindUsuari(Usuari usuari)
//        {
//            rdbUsuari.setText(usuari.getNom());
//        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.usuario_item,parent,false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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





}
