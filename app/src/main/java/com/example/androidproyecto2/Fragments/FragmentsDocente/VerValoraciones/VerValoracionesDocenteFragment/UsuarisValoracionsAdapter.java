package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.Grups_has_docents;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupsHasDocentService;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarisValoracionsAdapter extends RecyclerView.Adapter<UsuarisValoracionsAdapter.ViewHolder>
{
    private MainActivity activity;
    private Context context;
    private List<Usuari> usuaris;
    private List<Usuari> docentsGrup;
    private ViewPager vpMesesAño;
    private Usuari userSelected;
    private ArrayList<Mes> meses;
    private TextView lblTipoVal;
    private  int SelectedPosition = -1;


    public UsuarisValoracionsAdapter(Context context,List<Usuari> usuaris,List<Usuari> docentsGrup,MainActivity activity, ViewPager vpMesesAño, ArrayList<Mes> meses,TextView lblTipoVal) {
        this.context = context;
        this.usuaris = usuaris;
        this.activity = activity;
        this.vpMesesAño = vpMesesAño;
        this.meses = meses;
        this.docentsGrup = docentsGrup;
        this.lblTipoVal = lblTipoVal;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        RadioButton rdbUsuari;

        //Botones del dialog
        Dialog dialogSeleccionarTipoVerValoracion;
        RadioButton rdbTodasVals;
        RadioButton rdbGrupoDocenteVals;
        RadioButton rdbDocentesVals;
        RadioButton rdbMisUserVals;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rdbUsuari = itemView.findViewById(R.id.rdbUsuari);
            dialogSeleccionarTipoVerValoracion = new Dialog(activity);
            dialogSeleccionarTipoVerValoracion.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogSeleccionarTipoVerValoracion.setContentView(R.layout.dialog_menu_valoraciones_docente);
            dialogSeleccionarTipoVerValoracion.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            rdbTodasVals = dialogSeleccionarTipoVerValoracion.findViewById(R.id.rdbTodasVals);
            rdbGrupoDocenteVals = dialogSeleccionarTipoVerValoracion.findViewById(R.id.rdbGrupoDocenteVals);
            rdbDocentesVals = dialogSeleccionarTipoVerValoracion.findViewById(R.id.rdbDocentesVals);
            rdbMisUserVals = dialogSeleccionarTipoVerValoracion.findViewById(R.id.rdbMisUserVals);



            rdbUsuari.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    dialogSeleccionarTipoVerValoracion.show();
                    getUsuario(usuaris.get(SelectedPosition).getId(),rdbTodasVals,rdbGrupoDocenteVals,rdbDocentesVals,rdbMisUserVals);
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
        holder.rdbUsuari.setText(usuaris.get(position).getNom() + " " + usuaris.get(position).getCognoms());

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



    public void getUsuario(int id,RadioButton rdbTodasVals, RadioButton rdbGrupoDocenteVals, RadioButton rdbDocentesVals, RadioButton rdbMisUserVals)
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

                        //Coger las valoraciones que le han hecho al usuario de toda la gente incluido profesores
                        userSelected.getValoracions();

                        rdbTodasVals.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                lblTipoVal.setText("Ver valoraciones de todos a " + userSelected.getNom() + " " + userSelected.getCognoms());

                                if(userSelected.getValoracions().size() != 0)
                                {
                                    cargarVPagerMesesValoraciones(userSelected.getValoracions());
                                }
                                else
                                {
                                    Toast.makeText(activity, "No hay registros de valoraciones", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                        rdbGrupoDocenteVals.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                List<Valoracio> ValoracionesDocentesGrupo =  cogerValoracionesDocentesGrupo(userSelected.getValoracions(), docentsGrup);

                                lblTipoVal.setText("Ver valoraciones de Docentes del Grupo a " + userSelected.getNom() + " " + userSelected.getCognoms());

                                if(ValoracionesDocentesGrupo.size() != 0)
                                {
                                    cargarVPagerMesesValoraciones(ValoracionesDocentesGrupo);
                                }
                                else
                                {
                                    Toast.makeText(activity, "No hay registros de valoraciones", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        rdbDocentesVals.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                lblTipoVal.setText("Ver valoraciones de todos los Docentes a " + userSelected.getNom() + " " + userSelected.getCognoms());

                                cogerTodosLosDocentes(userSelected);
                            }
                        });

                        rdbMisUserVals.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                lblTipoVal.setText("Ver mis valoraciones a " + userSelected.getNom() + " " + userSelected.getCognoms());

                                cogerMisValoracionesDocentAlUsuarioSelect(userSelected);
                            }
                        });

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


    //Coger todos los meses sin repedidos de la valoraciones
    public HashSet<Mes> cogerMesesDeValoraciones(ArrayList<Mes> meses, List<Valoracio> valoracions)
    {
        HashSet<Mes> mesesValoracion = new HashSet<>();

        for (int i = 0; i < valoracions.size(); i++)
        {
            char [] numFecha = valoracions.get(i).getData().toCharArray();
            String mesStr = new StringBuilder(numFecha[4]).append(numFecha[5]).toString();
            int mes = Integer.parseInt(mesStr);

            for (int j = 0; j < meses.size(); j++)
            {
                if (mes - 1 == j)
                {
                    mesesValoracion.add(meses.get(j));
                }
            }

        }


       return mesesValoracion;
    }


    public void cargarVPagerMesesValoraciones(List<Valoracio> valoracions)
    {
        if (valoracions.size() != 0)
        {

            HashSet<Mes> mesesValoracion = cogerMesesDeValoraciones(meses,valoracions);

            //ArrayList<Valoracio> valoracionesDeMes = cogerValoracionesDeMes(meses,userSelected.getValoracions());
            //De los meses repetidos convertirlo a un arrayList Normal
            ArrayList<Mes> mesesDeValoraciones = new ArrayList<>(mesesValoracion);
            Collections.sort(mesesDeValoraciones);
            vpMesesAño.setClipToPadding(false);
            CalendarMesesAdapter calendarMesesAdapter = new CalendarMesesAdapter(context,mesesDeValoraciones, activity, valoracions);
            vpMesesAño.setAdapter(calendarMesesAdapter);

        }
        else
        {
            vpMesesAño.setAdapter(null);
        }
    }



    public List<Valoracio> cogerValoracionesDocentesGrupo(List<Valoracio> valsUsuari, List<Usuari> docentsG)
    {
        List<Valoracio> valsDocent = new ArrayList<>();

        for (Valoracio vals: valsUsuari) {

            for (Usuari user: docentsG)
            {
                if (vals.getUsuari_pp_id() == user.getId())
                {
                    valsDocent.add(vals);
                }

            }

        }

        return valsDocent;
    }


    public void cogerTodosLosDocentes(Usuari usuariSelected)
    {
        GrupsHasDocentService grupsHasDocentService = Api.getApi().create(GrupsHasDocentService.class);
        Call<List<Grups_has_docents>> listCall = grupsHasDocentService.Getgrups_has_docents();

        listCall.enqueue(new Callback<List<Grups_has_docents>>() {
            @Override
            public void onResponse(Call<List<Grups_has_docents>> call, Response<List<Grups_has_docents>> response) {
                switch (response.code())
                {
                    case 200:

                        List<Grups_has_docents> grupsHasDocents = response.body();

                        HashSet<Usuari> HashDocents = getDocents(grupsHasDocents);
                        List<Usuari> docents = new ArrayList<>(HashDocents);
                        Collections.sort(docents);

                        List<Valoracio> ValoracionesDocentes =  cogerValoracionesDocentesGrupo(userSelected.getValoracions(), docents);
                        if(ValoracionesDocentes.size() != 0)
                        {
                            cargarVPagerMesesValoraciones(ValoracionesDocentes);
                        }
                        else
                        {
                            Toast.makeText(activity, "No hay registros de valoraciones", Toast.LENGTH_SHORT).show();
                        }


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
            public void onFailure(Call<List<Grups_has_docents>> call, Throwable t) {

            }
        });

    }

    //Coger todos los docentes de la Api sin repetidos
    public HashSet<Usuari> getDocents(List<Grups_has_docents> grups_has_docents)
    {
        ArrayList<Usuari> docentsRep = new ArrayList<>();

        for (Grups_has_docents GD: grups_has_docents) {
            docentsRep.add(GD.getUsuaris());
        }
        HashSet<Usuari> docents = new HashSet<>(docentsRep);

        return docents;
    }



    public void cogerMisValoracionesDocentAlUsuarioSelect(Usuari usuariSelected)
    {
        UsuarisService usuarisService = Api.getApi().create(UsuarisService.class);
        Call<Usuari> usuariCall = usuarisService.Getusuaris(activity.usuariLogin.getId());

        usuariCall.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                switch (response.code())
                {
                    case 200:

                        Usuari YoDocent = response.body();

                        //mis valoraciones
                        List<Valoracio> misValoracions = cogerMisValoracionesDeUsuario(YoDocent.getValoracions1(), usuariSelected);
                        if(misValoracions.size() != 0)
                        {
                            cargarVPagerMesesValoraciones(misValoracions);
                        }
                        else
                        {
                            Toast.makeText(activity, "No hay registros de valoraciones", Toast.LENGTH_SHORT).show();
                        }



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


    public List<Valoracio> cogerMisValoracionesDeUsuario(List<Valoracio> misVals, Usuari userSel)
    {
        List<Valoracio> misValsUser = new ArrayList<>();

        for (Valoracio vals: misVals) {
            if(vals.getUsuari_valorat_id() == userSel.getId())
            {
                misValsUser.add(vals);
            }
        }

        return misValsUser;

    }

}


