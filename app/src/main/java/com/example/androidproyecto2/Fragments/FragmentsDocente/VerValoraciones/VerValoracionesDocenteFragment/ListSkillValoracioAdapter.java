package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.SkillMedia;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListSkillValoracioAdapter extends RecyclerView.Adapter<ListSkillValoracioAdapter.ViewHolder>
{

    private Context context;
    private List<LlistaSkills> llistesSkills;
    ArrayList<Valoracio> valoracionsDia;
    private MainActivity activity;


    public ListSkillValoracioAdapter(Context context, List<LlistaSkills> llistesSkills, ArrayList<Valoracio> valoracionsDia, MainActivity activity) {
        this.context = context;
        this.llistesSkills = llistesSkills;
        this.valoracionsDia = valoracionsDia;
        this.activity = activity;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblnombreListaSkillVal;
        BarChart barChart;
        PieChart pieChart;
        RadarChart radarChart;

        public ViewHolder(View item) {
            super(item);
            lblnombreListaSkillVal = item.findViewById(R.id.lblnombreListaSkillVal);
            barChart = item.findViewById(R.id.barListaSkillValoraciones);
            pieChart = item.findViewById(R.id.pieListaSkillValoraciones);
            radarChart = item.findViewById(R.id.radarListaSkillValoraciones);

        }

        void bindLlistaSkills(LlistaSkills llistaSkills)
        {
            lblnombreListaSkillVal.setText(llistaSkills.getNom());

            ArrayList<Valoracio> valoracionsLlistesSkillsDia = getValoracionesDeListaSkill(llistaSkills.getId());

            ArrayList<SkillMedia> mediasValoracionesSkill = getMediasSkills(llistaSkills.getSkills(),valoracionsLlistesSkillsDia);
            int [] coloresRandom = cogerColoresRandom(mediasValoracionesSkill.size());

            cargarBarChartValoraciones(barChart,llistaSkills.getNom(),mediasValoracionesSkill,coloresRandom);
            cargarPieChartValoraciones(pieChart,llistaSkills.getNom(),mediasValoracionesSkill,coloresRandom);
            cargarRadarChartValoraciones(radarChart,llistaSkills.getNom(),mediasValoracionesSkill,coloresRandom);


        }

    }


    @Override
    public ListSkillValoracioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.listaskill_valoraciones_item,parent,false);

        return new ListSkillValoracioAdapter.ViewHolder(item);
    }


    public void onBindViewHolder(ListSkillValoracioAdapter.ViewHolder holder, int position)
    {
        holder.bindLlistaSkills(llistesSkills.get(position));

    }



    public int getItemCount()
    {
        return llistesSkills.size();
    }


    //coger las valoraciones de la lista de skill
    public ArrayList<Valoracio> getValoracionesDeListaSkill(int idLlista)
    {
        ArrayList<Valoracio> valoracionsLLista = new ArrayList<>();

        for (Valoracio valsDia: valoracionsDia)
        {
            if (valsDia.getLlistes_skills_id() == idLlista)
            {
                valoracionsLLista.add(valsDia);
            }
        }

        return valoracionsLLista;
    }

    //Hacer la media de las valoraciones de la lista de skill filtrando por skills y devolver un arrayList con el nombre de la Skill y su media
    public ArrayList<SkillMedia> getMediasSkills(List<Skill> skills, ArrayList<Valoracio> valoracions)
    {

        ArrayList<SkillMedia> mediasSkills = new ArrayList<>();

        int i = 0;
        for (Skill skill: skills)
        {
            int sumaNota = 0;
            int contVals = 0;
            for (Valoracio vals: valoracions)
            {
                if(skill.getId() == vals.getSkills_id())
                {
                    contVals++;
                    sumaNota = sumaNota + vals.getNota();
                }
            }

            float media = sumaNota / (float)contVals;
            mediasSkills.add(new SkillMedia(skill.getNom(), media));

            i++;
        }

        return mediasSkills;
    }



    public int[] cogerColoresRandom(int cantidad)
    {
        // Esta variable se usará para llenar el array en la posición correspondiente
        int index = 0;

        // array que guarda los números aleatorios
        int [] coloresRandom = new int[cantidad];

        // Nuestro primer bucle que se ejecutará hasta que hayamos llenado el arrary
        while(index < cantidad) {
            // Variable que guarda el número aleatorio del array de colores del main
            int RandomColor = activity.coloresGraficos[new Random().nextInt(activity.coloresGraficos.length)];
            // Variable que indica si el RandomColor está repetido
            // asumimos que aún no está repetido y la establecemos a false
            boolean repetido = false;
            //Segundo bucle que se ejecutará siempre que el número no esté repetido
            while(!repetido) {
                // Bucle que recorre el array comparando el RandomColor con
                // cada uno de los items del array
                for(int i=0; i<index; i++) {
                    //realizamos la comparación
                    if(RandomColor == coloresRandom[i]) {
                        // si el número se repite, establecemos repetido=true
                        repetido = true;
                    }
                }
                // verificamos el estado del valor repetido. Si es false, significa
                // que hemos recorrido el array hasta la posición index sin encontrar
                // coincidencias
                if(!repetido) {
                    // almacenamos el valor propuesto ya que no está repetido
                    // incrementamos el índice
                    coloresRandom[index] = RandomColor;
                    index++;
                }
            }

        }

        return coloresRandom;
    }



    public void cargarBarChartValoraciones(BarChart barChart, String nomLlista ,ArrayList<SkillMedia> mediasValoracionesSkill ,int [] coloresRandom)
    {
        ArrayList<BarEntry> valoracionesFrase = new ArrayList<>();

        int x = 2014;
        for(SkillMedia skillsM: mediasValoracionesSkill)
        {
            valoracionesFrase.add(new BarEntry(x,(float) skillsM.getMedia()));
            x++;
        }


        BarDataSet barDataSet = new BarDataSet(valoracionesFrase,nomLlista);
        barDataSet.setColors(coloresRandom);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText(nomLlista);
        barChart.animateY(2000);
    }



    public void cargarPieChartValoraciones(PieChart pieChart, String nomLlista ,ArrayList<SkillMedia> mediasValoracionesSkill ,int [] coloresRandom)
    {
        ArrayList<PieEntry> valoracionesFrase = new ArrayList<>();

        for(SkillMedia skillsM: mediasValoracionesSkill)
        {
            valoracionesFrase.add(new PieEntry((float) skillsM.getMedia(),skillsM.getNomSkill()));
        }

        PieDataSet pieDataSet = new PieDataSet(valoracionesFrase,nomLlista);
        pieDataSet.setColors(coloresRandom);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText(nomLlista);
        pieChart.animate();
    }



    public void cargarRadarChartValoraciones(RadarChart radarChart, String nomLlista ,ArrayList<SkillMedia> mediasValoracionesSkill ,int [] coloresRandom)
    {
        ArrayList<RadarEntry> valoracionesAlumnos = new ArrayList<>();

        for(SkillMedia skillsM: mediasValoracionesSkill)
        {
            valoracionesAlumnos.add(new RadarEntry((float) skillsM.getMedia()));
        }

        RadarDataSet radarDataSetAlumnos = new RadarDataSet(valoracionesAlumnos,nomLlista);
        radarDataSetAlumnos.setColor(coloresRandom[0]);
        radarDataSetAlumnos.setLineWidth(2f);
        radarDataSetAlumnos.setValueTextColor(Color.BLACK);
        radarDataSetAlumnos.setValueTextSize(0f);

        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSetAlumnos);

        ArrayList<String> puntos = new ArrayList<>();

        for(SkillMedia skillsM: mediasValoracionesSkill)
        {
            puntos.add(skillsM.getNomSkill());
        }


        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(puntos));

        radarChart.getDescription().setText(nomLlista);
        radarChart.setData(radarData);
    }







}
