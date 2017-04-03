package com.tomekgozdek.futureapp.api;

import com.google.gson.GsonBuilder;
import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.model.adapter.FutureDeserializer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tomasz Gozdek on 03.04.2017.
 */

public class ApiClient {
    public static final String API_BASE_URL = "http://pinky.futuremind.com/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getHttpClient())
                    .build();
        }

        return retrofit;
    }

    private static GsonConverterFactory getGsonConverterFactory(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FutureItem.class, new FutureDeserializer());
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    private static OkHttpClient getHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }
}
