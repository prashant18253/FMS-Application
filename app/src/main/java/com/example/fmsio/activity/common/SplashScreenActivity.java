package com.example.fmsio.activity.common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.fmsio.R;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        int SPLASH_TIME_OUT = 1500;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this,ChooseRoleActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}