package com.ry_050.myapp.ui.zhsq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;

public class kuaijianguanliActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private LinearLayout kuaidi111;
    private LinearLayout kuaidi333;
    private LinearLayout kuaidi222;
    private LinearLayout kuaidi444;
    private LinearLayout aaaaaa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaijianguanli);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        kuaidi111 = (LinearLayout) findViewById(R.id.kuaidi111);
        kuaidi333 = (LinearLayout) findViewById(R.id.kuaidi333);
        kuaidi222 = (LinearLayout) findViewById(R.id.kuaidi222);
        kuaidi444 = (LinearLayout) findViewById(R.id.kuaidi444);
        aaaaaa=findViewById(R.id.aaaaaa);
        inittoolbar("快递管理",true);
        kuaidi111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kuaijianguanliActivity.this,kuaidixiangqingyeActivity.class) );
            }
        });
        kuaidi333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kuaijianguanliActivity.this,kuaidixiangqingyeActivity.class) );
            }
        });
        kuaidi222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kuaijianguanliActivity.this,kuaidixiangqingyeActivity.class) );
            }
        });
        kuaidi444.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kuaijianguanliActivity.this,kuaidixiangqingyeActivity.class) );
            }
        });
    }
}