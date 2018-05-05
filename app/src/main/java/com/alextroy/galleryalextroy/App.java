package com.alextroy.galleryalextroy;

import android.app.Application;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static final String BASE_URL = "https://api.flickr.com";
    private Retrofit retrofit;
    private static Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static Api getApi() {
        return api;
    }

}
