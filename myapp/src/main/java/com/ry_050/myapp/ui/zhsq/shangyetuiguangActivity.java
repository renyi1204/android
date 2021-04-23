package com.ry_050.myapp.ui.zhsq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.ui.home.xiangqingyeActivity;

public class shangyetuiguangActivity extends BaseActivity {
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangyetuiguang);
        initView();
    }

    private void initView() {
        viewFlipper=findViewById(R.id.vf_sytg) ;
        viewFlipper.startFlipping();
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(shangyetuiguangActivity.this, xiangqingyeActivity.class));
            }
        });
    }
}