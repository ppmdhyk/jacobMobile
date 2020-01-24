package com.example.jacobmobile.rest;

import com.example.jacobmobile.model.PostSaran;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("inputsaran")
    Call<PostSaran> inputsaran(@Field("saran") String saran,@Field("topik") String topik,@Field("urgensi") String urgensi,@Field("sentimen") String sentimen);

}
