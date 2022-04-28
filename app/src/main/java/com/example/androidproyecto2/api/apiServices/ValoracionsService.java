package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ValoracionsService
{
    @GET("api/valoracions/")
    Call<List<Valoracio>> Getvaloracions();

    @POST("api/valoracions/")
    Call<Valoracio> insertValoracio(@Body Valoracio valoracio);




}
