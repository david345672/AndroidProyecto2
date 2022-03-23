package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.Grup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GrupService {
    @GET("api/grups")
    Call<List<Grup>> GetGrups();
}
