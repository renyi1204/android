package com.ry_050.myapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;

public class SearcgActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcg);
        inittoolbar("搜索详情",true);
    }
}