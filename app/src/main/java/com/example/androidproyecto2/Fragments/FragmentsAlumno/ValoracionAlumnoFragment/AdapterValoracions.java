package com.example.androidproyecto2.Fragments.FragmentsAlumno.ValoracionAlumnoFragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.Kpi;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment.KpiAdapterValoracion;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterValoracions extends RecyclerView.Adapter<AdapterValoracions.ViewHolder>{

    private List<Kpi> mDataSet;
    private View.OnClickListener listener;
    private MainActivity activity;
    private KpiAdapterValoracion adapterKPI;


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView texto;
        public TextView idKpiTexto;
        public ViewHolder(@NonNull View item) {
            super(item);
            texto = item.findViewById(R.id.texto);
            idKpiTexto = item.findViewById(R.id.idKpi);


        }
        public void bindValoracio(Kpi pr){
            texto.setText(pr.getNom());
            idKpiTexto.setText(Integer.toString(pr.getId()));

        }
    }


    public AdapterValoracions(List<Kpi> kpis, MainActivity act) {
        setHasStableIds(true);
        this.mDataSet = kpis;
        this.activity = act;
        this.adapterKPI = new KpiAdapterValoracion(act,activity.skillSelected.getKpis(), act, activity.skillSelected,null);
    }


    @Override
    public AdapterValoracions.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_valoracion_alumno_single,parent,false);
        //item.setOnClickListener(this);
        item.setOnTouchListener(new OnSwipeTouchListener(parent.getContext()) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(activity, "Quitada la valoracion del KPI", Toast.LENGTH_SHORT).show();
                System.out.println("AKFJDLÑA EW GET HEREEEEE");
                CardView card = item.findViewById(R.id.cardItem);
                TextView kpiIdTextView = item.findViewById(R.id.idKpi);
                //Toast.makeText(parent.getContext(),"hola"+ (CharSequence) card.getCardBackgroundColor(), Toast.LENGTH_SHORT).show();
                /*if(card.getCardBackgroundColor() == ColorStateList.valueOf(Color.GREEN)){
x
                }*/
                card.setBackgroundColor(Color.WHITE);


                //Valoracio v = new Valoracio(Integer.parseInt((String) kpiIdTextView.getText()),activity.usuariValorat.getId(),40, new Timestamp(new Date().getTime()), 0, activity.llistaSkillSelected.getId(), activity.skillSelected.getId(),"null");


                Valoracio v = new Valoracio(Integer.parseInt((String) kpiIdTextView.getText()), activity.usuariValorat.getId(), activity.usuariLogin.getId(), new Timestamp(System.currentTimeMillis()), 0, activity.llistaSkillSelected.getId(), activity.skillSelected.getId(),"Observacion de alumno");
                System.out.println(v.toString());
                adapterKPI.insertValoracio(v);

            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();

                CardView card = item.findViewById(R.id.cardItem);
                TextView kpiIdTextView = item.findViewById(R.id.idKpi);
                setCardColorTran(card);
                //card.setBackgroundColor(Color.GREEN);
                Toast.makeText(parent.getContext(), "KPI Valorada", Toast.LENGTH_SHORT).show();
                Valoracio v = new Valoracio(Integer.parseInt((String) kpiIdTextView.getText()), activity.usuariValorat.getId(), activity.usuariLogin.getId(), new Timestamp(System.currentTimeMillis()), 1, activity.llistaSkillSelected.getId(), activity.skillSelected.getId(),"Observacion de alumno");
                System.out.println(v.toString());
                adapterKPI.insertValoracio(v);


            }
            public void setCardColorTran(CardView card) {
                ColorDrawable[] color = {new ColorDrawable(Color.WHITE), new ColorDrawable(Color.GREEN)};
                TransitionDrawable trans = new TransitionDrawable(color);
                card.setBackground(trans);
                trans.startTransition(3000);
            }
        });
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindValoracio(mDataSet.get(position));

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    /*@Override
    public void onClick(View view){
        Toast.makeText(view.getContext(), "Clicked",Toast.LENGTH_LONG).show();
        if (listener == null) listener.onClick(view);
        System.out.println("CLicked");
    }*/



    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
