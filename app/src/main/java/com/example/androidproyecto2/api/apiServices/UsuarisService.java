package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.Grups_has_alumnes;
import com.example.androidproyecto2.Clases.Usuari;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarisService {

    @GET("api/usuaris/")
    Call<List<Usuari>> Getusuaris(String nomUsuari, String s);

    @GET("api/usuaris/")
    Call<List<Usuari>> Getusuaris();

    @GET("api/usuaris/nom/{nom}")
    Call<Usuari> Getusuaris(@Path("nom") String nom);



}
