package com.tomekgozdek.futureapp.api;

import com.google.gson.GsonBuilder;
import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.model.adapter.FutureDeserializer;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by TomekG on 2017-04-02.
 */
public class FutureServiceTest {

    private static Retrofit retrofit;

    @BeforeClass
    public static void setup(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FutureItem.class, new FutureDeserializer());
        //add client with extended timeout
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                    .readTimeout(60, TimeUnit.SECONDS)
                                    .connectTimeout(60, TimeUnit.SECONDS)
                                    .build();

        retrofit = new Retrofit.Builder().baseUrl("http://pinky.futuremind.com/")
                            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                            .client(okHttpClient)
                            .build();
    }

    @Test
    public void apiReturnNonEmptyListOfItems(){
        FutureService futureService = retrofit.create(FutureService.class);

        Response<ApiResponse> response = null;
        try {
            response = futureService.listFutureItems().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response != null){
            Assert.assertNotNull(response.body().data);
            Assert.assertNotEquals("Response list is not empty", -1, response.body().data.size());
        } else {
            fail("Response is null");
        }
    }
}