package com.uog.fyp.e.learning.service;

import android.provider.ContactsContract;

import com.uog.fyp.e.learning.model.Retrophoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {
    @GET("/photos")
    Call<List<Retrophoto>> getAllPhotos();
    @POST("/photos")
    Call<List<Retrophoto>> createPost(@Body Retrophoto retrophoto);

}
