package com.example.movielistactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initSplash();
    }

    private void initSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentLogin = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intentLogin);
            }
        }, 3000);
    }
}
