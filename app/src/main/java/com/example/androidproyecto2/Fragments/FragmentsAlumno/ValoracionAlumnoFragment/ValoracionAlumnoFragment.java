package com.example.androidproyecto2.Fragments.FragmentsAlumno.ValoracionAlumnoFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.sql.Array;
import java.util.ArrayList;

public class ValoracionAlumnoFragment extends Fragment {

    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_valoracion_alumno, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        activity.layout = "HacerValoracion";
        TextView tx = activity.findViewById(R.id.titlekpi);
        tx.setText("EJEMPLO");

        String[] prova = {"hola","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka"};
        LinearLayout layout = activity.findViewById(R.id.layout);


        RecyclerView rv = activity.findViewById(R.id.kpisRecycler);
        String[] provaS = prova;
        AdapterValoracions adapter = new AdapterValoracions(provaS);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        rv.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                TextView LabelName = activity.findViewById(R.id.texto);
                LabelName.setText(prova[(rv.getChildAdapterPosition(view))]);

            }
        });


    }


}

class OnSwipeTouchListener implements View.OnTouchListener {
    private GestureDetector gestureDetector;
    OnSwipeTouchListener(Context c) {
        gestureDetector = new GestureDetector(c, new GestureListener());
    }
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }
    private final class GestureListener extends
            GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onClick();
            return super.onSingleTapUp(e);
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }
        @Override
        public void onLongPress(MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                }
                else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeDown();
                        } else {
                            onSwipeUp();
                        }
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }
    public void onSwipeRight() {
    }
    public void onSwipeLeft() {
    }
    private void onSwipeUp() {
    }
    private void onSwipeDown() {
    }
    private void onClick() {
    }
    private void onDoubleClick() {
    }
    private void onLongClick() {
    }
}
class AdapterValoracions extends RecyclerView.Adapter<AdapterValoracions.ViewHolder> implements View.OnClickListener{

    private String[] mDataSet;
    private View.OnClickListener listener;



    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView texto;
        public ViewHolder(View item) {
            super(item);
            texto = item.findViewById(R.id.texto);
        }
        public void bindValoracio(String pr){
            texto.setText(pr);
        }
    }


    public AdapterValoracions(String[] myDataSet) {
        mDataSet = myDataSet;
    }


    @Override
    public AdapterValoracions.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_valoracion_alumno_single,parent,false);
        item.setOnClickListener(this);
        item.setOnTouchListener(new OnSwipeTouchListener(parent.getContext()) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(parent.getContext(), "Swipe Left gesture detected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(parent.getContext(), "Swipe Right gesture detected", Toast.LENGTH_SHORT).show();
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
    public void onClick(View view){
        if (listener != null) listener.onClick(view);
        System.out.println("CLicked");
    }



    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}