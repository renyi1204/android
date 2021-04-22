package com.ry_050.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.ry_050.myapp.Activity.GuideViewActivity.GuideViewActivity;
import com.ry_050.myapp.ui.center.LoginActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sharedPreferences=getSharedPreferences("first",MODE_PRIVATE);
        boolean is=sharedPreferences.getBoolean("is",true);
        if (is){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("is",false);
            editor.apply();
            startActivity(new Intent(this, GuideViewActivity.class));
        }else {
            startActivity(new Intent(this, LoginActivity .class));
        }
    }
}