package com.asadeq.nm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            Thread.sleep((long)TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
            Log.e("Splash ", e.getMessage());
        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
