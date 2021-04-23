package com.ry_050.myapp.ui.zhsq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;

public class kuaidixiangqingyeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidixiangqingye);
        inittoolbar("快递管理",true);
    }
}