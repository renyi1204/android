package com.ry_050.myapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;

public class xiangqingyeActivity extends BaseActivity {
    private ImageView imageView;
    private TextView textView;
    private TextView textView2;
    private CityPickerView  cityPickerView=new CityPickerView();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqingye);
        inittoolbar("新闻详情",true);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        Glide.with(this).load(getIntent().getStringExtra("img")).into(imageView);
        textView2.setText(getIntent().getStringExtra("content"));
        if (getIntent().getStringExtra("tittle")!=null){
            textView.setText(getIntent().getStringExtra("tittle"));
        }


    }
}