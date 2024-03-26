package com.example.oechappfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true);
        if (isFirstLaunch) {
            // Первый запуск, показываем экран Onboarding
            startActivity(new Intent(this, OnboardingActivity.class));
        } else {
            // Переходим к основной активности
            startActivity(new Intent(this, HomeActivity.class));
        }
        finish(); // Закрываем текущую Activity, чтобы пользователь не мог вернуться к ней
    }
}