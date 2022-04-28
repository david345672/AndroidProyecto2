package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.Notificacio;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment.NotificacionesFragment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificacionsService
{
    @DELETE("api/notificacions/{id}")
    Call<Notificacio> Deletenotificacions(@Path("id") int id);

    @POST("api/notificacions/")
    Call<Notificacio> Postnotificacions(@Body Notificacio notificacio);
}
