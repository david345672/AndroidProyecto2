package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.Grups_has_alumnes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GrupsHasAlumnesService {
    @GET("api/grups_has_alumnes")
    Call<List<Grups_has_alumnes>> Getgrups_has_alumnes();


}
