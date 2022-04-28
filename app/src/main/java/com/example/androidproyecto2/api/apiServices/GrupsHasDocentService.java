package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.Grups_has_docents;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GrupsHasDocentService
{
    @GET("api/grups_has_docents")
    Call<List<Grups_has_docents>> Getgrups_has_docents();
}
