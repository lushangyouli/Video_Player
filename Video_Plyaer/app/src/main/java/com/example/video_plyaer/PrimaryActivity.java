package com.example.video_plyaer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.viewpager2.widget.ViewPager2;

public class PrimaryActivity extends AppCompatActivity {

//    private List<VideoResult> videoResults;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        viewPager2 = findViewById(R.id.viewpager2);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        query();
    }

    private void query(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getVideos().enqueue(new Callback<List<VideoResult>>() {
            @Override
            public void onResponse(Call<List<VideoResult>> call, Response<List<VideoResult>> response) {
                if(response.body() != null){
                    Log.d("retrofit", "***");
                    List<VideoResult> videos = response.body();
                    viewPager2.setAdapter(new MyAdapter(videos));
                }
            }

            @Override
            public void onFailure(Call<List<VideoResult>> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
                Log.d("retrofit", "*****");
            }
        });
    }
}
