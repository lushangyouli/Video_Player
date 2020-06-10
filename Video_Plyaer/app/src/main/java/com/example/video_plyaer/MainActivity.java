package com.example.video_plyaer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        ProgressBar pb = findViewById(R.id.progressBar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        pb.incrementProgressBy(1);
                        handler.postDelayed(this, 20);
                    }
                };
                handler.postDelayed(runnable, 20);
                Handler handler2 = new Handler();
                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, PrimaryActivity.class);
                        startActivity(intent);
                    }
                };
                handler2.postDelayed(runnable2, 2200);
            }
        });

    }

}
