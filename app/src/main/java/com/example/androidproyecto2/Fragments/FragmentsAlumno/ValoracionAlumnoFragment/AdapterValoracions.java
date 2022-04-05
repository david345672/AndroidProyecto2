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

import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.R;

public class AdapterValoracions extends RecyclerView.Adapter<AdapterValoracions.ViewHolder>{

    private String[] mDataSet;
    private View.OnClickListener listener;

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
        public ViewHolder(@NonNull View item) {
            super(item);
            //this.setIsRecyclable(false);
            texto = item.findViewById(R.id.texto);

        }
        public void bindValoracio(String pr){
            texto.setText(pr);
        }
    }


    public AdapterValoracions(String[] myDataSet) {
        setHasStableIds(true);mDataSet = myDataSet;
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
                Toast.makeText(parent.getContext(), "Quitada la valoracion del KPI", Toast.LENGTH_SHORT).show();
                CardView card = item.findViewById(R.id.cardItem);
                //Toast.makeText(parent.getContext(),"hola"+ (CharSequence) card.getCardBackgroundColor(), Toast.LENGTH_SHORT).show();
                /*if(card.getCardBackgroundColor() == ColorStateList.valueOf(Color.GREEN)){
x
                }*/
                card.setBackgroundColor(Color.WHITE);

            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                System.out.println("TextSwipeRight");
                CardView card = item.findViewById(R.id.cardItem);
                setCardColorTran(card);
                //card.setBackgroundColor(Color.GREEN);
                Toast.makeText(parent.getContext(), "KPI Valorada", Toast.LENGTH_SHORT).show();
                //Valoracio v = new Valoracio()
            }
            public void setCardColorTran(CardView card) {
                ColorDrawable[] color = {new ColorDrawable(Color.WHITE), new ColorDrawable(Color.GREEN)};
                TransitionDrawable trans = new TransitionDrawable(color);
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                    card.setBackground(trans);
                } else {
                    card.setBackgroundDrawable(trans);
                }
                trans.startTransition(3000);
            }
        });
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindValoracio(mDataSet[position]);

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
        return mDataSet.length;
    }
}
