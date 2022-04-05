package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.Valoracio;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ValoracionsService
{
    @POST("api/valoracions/")
    Call<Valoracio> insertValoracio(@Body Valoracio valoracio);
}
