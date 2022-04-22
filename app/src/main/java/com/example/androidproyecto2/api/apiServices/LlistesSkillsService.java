package com.example.androidproyecto2.api.apiServices;

import com.example.androidproyecto2.Clases.LlistaSkills;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LlistesSkillsService
{
    @GET("api/llistes_skills/")
    Call<List<LlistaSkills>> Getusuaris();
}
