package com.example.androidproyecto2.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String BASE_URL = "http://abp-politecnics.com/2022/dam03/";
    private static Retrofit retrofit = null;

    public static Retrofit getApi()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
