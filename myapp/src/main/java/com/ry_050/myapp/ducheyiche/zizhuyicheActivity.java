package com.ry_050.myapp.ducheyiche;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ry_050.myapp.R;

public class zizhuyicheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zizhuyiche);
        BottomNavigationView navView = findViewById(R.id.zizhuyiche);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.zizhuyiche_1);
        NavigationUI.setupWithNavController(navView, navController);
    }
}