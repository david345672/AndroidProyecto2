package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment.DiasAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;


public class ColoresBotonesAdapter extends RecyclerView.Adapter<ColoresBotonesAdapter.ViewHolder>
{
    private Context context;
    private int[] colores;
    private MainActivity activity;

    public ColoresBotonesAdapter(Context context, int[] colores, MainActivity activity) {
        this.context = context;
        this.colores = colores;
        this.activity = activity;

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        Button btnColor;


        public ViewHolder(View item) {
            super(item);
            btnColor = item.findViewById(R.id.btnColor);
        }

        void bindColor(int color, int pos)
        {
            btnColor.setBackgroundColor(color);

            btnColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(context, activity.coloresGraficos[pos], new AmbilWarnaDialog.OnAmbilWarnaListener() {
                        @Override
                        public void onCancel(AmbilWarnaDialog dialog) {

                        }

                        @Override
                        public void onOk(AmbilWarnaDialog dialog, int colorSelected) {
                            btnColor.setBackgroundColor(colorSelected);
                            activity.coloresGraficos[pos] = colorSelected;
                        }
                    });
                    colorPicker.show();
                }
            });


        }

    }


    @Override
    public ColoresBotonesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.btn_color_grafico_item,parent,false);

        return new ColoresBotonesAdapter.ViewHolder(item);
    }


    public void onBindViewHolder(ColoresBotonesAdapter.ViewHolder holder, int position)
    {
        holder.bindColor(colores[position],position);

    }



    public int getItemCount()
    {
        return colores.length;
    }


}
