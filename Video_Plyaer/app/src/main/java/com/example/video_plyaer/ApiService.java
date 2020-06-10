package com.example.video_plyaer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("api/invoke/video/invoke/video")
    Call<List<VideoResult>> getVideos();

}
