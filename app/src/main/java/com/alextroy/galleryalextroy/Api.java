package com.alextroy.galleryalextroy;

import com.alextroy.galleryalextroy.Model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String SERVICES_REST = "/services/rest";
    String METHOD = "method";
    String API_KEY = "api_key";
    String FORMAT = "format";
    String NO_JSON_CALLBACK = "nojsoncallback";
    String EXTRAS = "extras";

    @GET(SERVICES_REST)
    Call<Response> getData(@Query(METHOD) String method,
                           @Query(API_KEY) String key,
                           @Query(FORMAT) String formatResponse,
                           @Query(NO_JSON_CALLBACK) int callback,
                           @Query(EXTRAS) String imageType);
}
