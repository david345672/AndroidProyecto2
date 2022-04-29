package com.example.androidproyecto2.Fragments.FragmentsAlumno.VerValoracionAlumnoFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.Grups_has_docents;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.SkillMedia;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupsHasDocentService;
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
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterVerValoracionCharts extends RecyclerView.Adapter<AdapterVerValoracionCharts.ViewHolder>
{
    private List<Usuari> docents;
    private Context context;
    private boolean isDocentVersion = false;
    private List<LlistaSkills> llistesSkills;
private List<Valoracio> v;
    private MainActivity activity;

    public AdapterVerValoracionCharts(Context context, ArrayList<LlistaSkills> lls, List<Usuari> docents, MainActivity ma){
        this.isDocentVersion = true;
        this.docents = docents;
        this.context = context;
        this.llistesSkills = lls;
        this.activity = ma;
        v = new ArrayList<>();

        for (Valoracio va :activity.usuariLogin.getValoracions()     ) {
            v.add(va);

        }
        for (Valoracio va :activity.usuariLogin.getValoracions1()     ) {
            v.add(va);

        }


        List<Valoracio> valoracionsDeProfessors = new ArrayList<>();
        for (Valoracio val: v  ) {
            for (Usuari doc: docents           ) {
                if(val.getUsuari_pp_id() == doc.getId()){
                    valoracionsDeProfessors.add(val);
                }
            }
        }
        v.clear();
        for (Valoracio vd : valoracionsDeProfessors){
            v.add(vd);
        }


    }

    public AdapterVerValoracionCharts(Context context, ArrayList<LlistaSkills> lls, MainActivity ma) {



        this.context = context;
        this.llistesSkills = lls;
        this.activity = ma;
        v = new ArrayList<>();

        for (Valoracio va :activity.usuariLogin.getValoracions()     ) {
            v.add(va);

        }

        for (Valoracio va :activity.usuariLogin.getValoracions1()     ) {
            v.add(va);

        }



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
            //ArrayList<Valoracio> valoracionsLlistesSkillsDia = getValoracionesDeListaSkill(llistaSkills.getId());

            //ArrayList<SkillMedia> mediasValoracionesSkill = getMediasSkills(llistaSkills.getSkills(),valoracionsLlistesSkillsDia);
            int [] coloresRandom = cogerColoresRandom(15);

            cargarBarChartValoraciones(barChart,llistaSkills.getNom(),coloresRandom);
            cargarPieChartValoraciones(pieChart,llistaSkills.getNom(),coloresRandom);
            cargarRadarChartValoraciones(radarChart,llistaSkills.getNom(),coloresRandom);


        }

    }


    @Override
    public AdapterVerValoracionCharts.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.listaskill_valoraciones_item_alumno,parent,false);

        return new AdapterVerValoracionCharts.ViewHolder(item);
    }


    public void onBindViewHolder(AdapterVerValoracionCharts.ViewHolder holder, int position)
    {
        holder.bindLlistaSkills(llistesSkills.get(position));

    }


    public int getItemCount()
    {
        return llistesSkills.size();
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


    //coger las valoraciones de la lista de skill
    /*
    public ArrayList<Valoracio> getValoracionesDeListaSkill(int idLlista)
    {
        ArrayList<Valoracio> valoracionsLLista = new ArrayList<>();

        for (Valoracio valsDia: totesValoracions)
        {
            if (valsDia.getLlistes_skills_id() == idLlista)
            {
                valoracionsLLista.add(valsDia);
            }
        }

        return valoracionsLLista;
    }
*/
    //Hacer la media de las valoraciones de la lista de skill filtrando por skills y devolver un arrayList con el nombre de la Skill y su media






    public void cargarBarChartValoraciones(BarChart barChart, String nomLlista, int[] coloresRandom)
    {
        ArrayList<BarEntry> valoracionesFrase = new ArrayList<>();
        List<LlistaSkills> lls = llistesSkills;
        int selected = 0;
        for ( LlistaSkills l: lls ) {
            if(lls.get(selected).getNom().equals(nomLlista)){
                break;
            }
            selected++;
        }

        //List<Valoracio> v = activity.usuariLogin.getValoracions();
        //v.addAll(activity.usuariLogin.getValoracions1());
        if(isDocentVersion){
            int x = 2014;
            int cantitatvals = 0;
            for (int i = selected; i < lls.size(); i++) {
                //por cada lista
                for (int j = 0; j < lls.get(i).getSkills().size(); j++) {
                    //por cada skill
                    int puntuacio = 0;
                    for (Valoracio val : v) {
                        //por cada valoracion

                        if (val.getSkills_id() == lls.get(i).getSkills().get(j).getId() && val.getUsuari_valorat_id() == activity.usuariLogin.getId()) {
                           puntuacio += val.getNota();
                            cantitatvals++;
                        }
                    }
                    if (cantitatvals == 0){
                        valoracionesFrase.add(new BarEntry(x++,cantitatvals));
                    }else {
                        valoracionesFrase.add(new BarEntry(x++, puntuacio / cantitatvals));
                    }
                    System.out.println(puntuacio+".,.,."+lls.get(i).getSkills().size()+"jlkjl"+v.size());

                }
                String[] puntos = new String[lls.get(i).getSkills().size()];
                for (int r = 0; r < lls.get(i).getSkills().size(); r++) {
                    puntos[r] = lls.get(i).getSkills().get(r).getNom();
                }
                break; // mala practica pero fucniona, sé como arreglarlo pero no hay tiempo :/
            }
        }else{
            int x = 2014;
            for (int i = selected; i < lls.size(); i++) {
                //por cada lista
                for (int j = 0; j < lls.get(i).getSkills().size(); j++) {
                    //por cada skill
                    int puntuacio = 0;
                    for (Valoracio val : v) {
                        //por cada valoracion

                        if (val.getSkills_id() == lls.get(i).getSkills().get(j).getId() && val.getUsuari_valorat_id() == activity.usuariLogin.getId()) {
                            if(val.getNota() == 1)puntuacio++;
                        }
                    }
                    valoracionesFrase.add(new BarEntry(x++,puntuacio));
                    System.out.println(puntuacio+".,.,."+lls.get(i).getSkills().size()+"jlkjl"+v.size());

                }
                String[] puntos = new String[lls.get(i).getSkills().size()];
                for (int r = 0; r < lls.get(i).getSkills().size(); r++) {
                    puntos[r] = lls.get(i).getSkills().get(r).getNom();
                }
                break; // mala practica pero fucniona, sé como arreglarlo pero no hay tiempo :/
            }
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



    public void cargarPieChartValoraciones(PieChart pieChart, String nomLlista, int[] coloresRandom)
    {
        ArrayList<PieEntry> valoracionesFrase = new ArrayList<>();
        List<LlistaSkills> lls = llistesSkills;
        int selected = 0;
        for ( LlistaSkills l: lls ) {
            if(lls.get(selected).getNom().equals(nomLlista)){
                break;
            }
            selected++;
        }

        if(isDocentVersion){
            int cantitatvals = 0;
            for (int i = selected; i < lls.size(); i++) {
                //por cada lista
                for (int j = 0; j < lls.get(i).getSkills().size(); j++) {
                    //por cada skill
                    int puntuacio = 0;
                    for (Valoracio val : v) {
                        //por cada valoracion

                        if (val.getSkills_id() == lls.get(i).getSkills().get(j).getId() && val.getUsuari_valorat_id() == activity.usuariLogin.getId()) {
                            puntuacio += val.getNota();
                            cantitatvals++;
                        }
                    }
                    if(cantitatvals == 0){
                        valoracionesFrase.add(new PieEntry(cantitatvals,lls.get(i).getSkills().get(j).getNom()));
                    }else {
                        valoracionesFrase.add(new PieEntry(puntuacio / cantitatvals, lls.get(i).getSkills().get(j).getNom()));
                    }

                }
                String[] puntos = new String[lls.get(i).getSkills().size()];
                for (int r = 0; r < lls.get(i).getSkills().size(); r++) {
                    puntos[r] = lls.get(i).getSkills().get(r).getNom();
                }
                break;
            }
        }else{
        for (int i = selected; i < lls.size(); i++) {
            //por cada lista
            for (int j = 0; j < lls.get(i).getSkills().size(); j++) {
                //por cada skill
                int puntuacio = 0;
                for (Valoracio val : v) {
                    //por cada valoracion

                    if (val.getSkills_id() == lls.get(i).getSkills().get(j).getId() && val.getUsuari_valorat_id() == activity.usuariLogin.getId()) {
                        if(val.getNota() == 1)puntuacio++;
                    }
                }
                valoracionesFrase.add(new PieEntry(puntuacio,lls.get(i).getSkills().get(j).getNom()));
                System.out.println(puntuacio+" piechart");
            }
            String[] puntos = new String[lls.get(i).getSkills().size()];
            for (int r = 0; r < lls.get(i).getSkills().size(); r++) {
                puntos[r] = lls.get(i).getSkills().get(r).getNom();
            }
            break;
        }
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



    public void cargarRadarChartValoraciones(RadarChart radarChart, String nomLlista, int[] coloresRandom)
    {

        ArrayList<RadarEntry> valoracionesAlumnos = new ArrayList<>();
        List<LlistaSkills> lls = llistesSkills;
        int selected = 0;
        for ( LlistaSkills l: lls ) {
            if(lls.get(selected).getNom().equals(nomLlista)){
                break;
            }
            selected++;
        }

        if (isDocentVersion){
            for (int i = selected; i < lls.size(); i++) {
                //por cada lista
                for (int j = 0; j < lls.get(i).getSkills().size(); j++) {
                    //por cada skill
                    int puntuacio = 0;
                    //int times = 0;
                    for (Valoracio val : v) {
                        //por cada valoracion

                        if (val.getSkills_id() == lls.get(i).getSkills().get(j).getId() && val.getUsuari_valorat_id() == activity.usuariLogin.getId()) {
                            puntuacio++;
                        }
                        //if(val.getUsuari_valorat_id() == activity.usuariLogin.getId())times++;
                    }
                    valoracionesAlumnos.add(new RadarEntry((float)puntuacio));
                    //System.out.println("skill num "+j+ "de la frase "+nomLlista+" punt"+puntuacio+" valoracions d'aquest usuari"+times);
                    //times = lls.get(i).getSkills().size();
                }String[] puntos = new String[lls.get(i).getSkills().size()];
                for (int r = 0; r < lls.get(i).getSkills().size(); r++) {
                    puntos[r] = lls.get(i).getSkills().get(r).getNom();
                }
                RadarDataSet radarDataSetAlumnos = new RadarDataSet(valoracionesAlumnos," Valoracions globals dels alumnes");
                radarDataSetAlumnos.setColor(cogerColoresRandom(1)[0]);
                radarDataSetAlumnos.setLineWidth(2f);
                radarDataSetAlumnos.setDrawValues(false);
                radarDataSetAlumnos.setValueTextColor(Color.BLACK);
                radarDataSetAlumnos.setValueTextSize(0f);

                XAxis xAxis = radarChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(puntos));



                RadarData radarData = new RadarData();
                radarData.addDataSet(radarDataSetAlumnos);


                radarChart.getDescription().setText(lls.get(i).getNom());
                radarChart.setData(radarData);
                break;
        }}else{
        for (int i = selected; i < lls.size(); i++) {
            //por cada lista
            for (int j = 0; j < lls.get(i).getSkills().size(); j++) {
                //por cada skill
                int puntuacio = 0;
                //int times = 0;
                for (Valoracio val : v) {
                    //por cada valoracion

                    if (val.getSkills_id() == lls.get(i).getSkills().get(j).getId() && val.getUsuari_valorat_id() == activity.usuariLogin.getId()) {
                        if(val.getNota() == 1)puntuacio++;
                    }
                    //if(val.getUsuari_valorat_id() == activity.usuariLogin.getId())times++;
                }
                valoracionesAlumnos.add(new RadarEntry((float)puntuacio));
                //System.out.println("skill num "+j+ "de la frase "+nomLlista+" punt"+puntuacio+" valoracions d'aquest usuari"+times);
                //times = lls.get(i).getSkills().size();
            }
            String[] puntos = new String[lls.get(i).getSkills().size()];
            for (int r = 0; r < lls.get(i).getSkills().size(); r++) {
                puntos[r] = lls.get(i).getSkills().get(r).getNom();
            }
            RadarDataSet radarDataSetAlumnos = new RadarDataSet(valoracionesAlumnos," Valoracions globals dels alumnes");
            radarDataSetAlumnos.setColor(cogerColoresRandom(1)[0]);
            radarDataSetAlumnos.setLineWidth(2f);
            radarDataSetAlumnos.setDrawValues(false);
            radarDataSetAlumnos.setValueTextColor(Color.BLACK);
            radarDataSetAlumnos.setValueTextSize(0f);

            XAxis xAxis = radarChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(puntos));



            RadarData radarData = new RadarData();
            radarData.addDataSet(radarDataSetAlumnos);


            radarChart.getDescription().setText(lls.get(i).getNom());
            radarChart.setData(radarData);
            break;
        }

        }

    }







}
