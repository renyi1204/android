package com.ry_050.myapp.Activity.GuideViewActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ry_050.myapp.Adapter.MyPagerAdapter;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.ui.center.LoginActivity;

import java.util.ArrayList;

public class GuideViewActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private Button buttonIp;
    private Button buttonZhuye;
    private View pointOne;
    private View pointTwo;
    private View pointThree;
    private View pointFour;
    private View pointFive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_view);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        buttonIp = (Button) findViewById(R.id.button_ip);
        buttonZhuye = (Button) findViewById(R.id.button_zhuye);
        pointOne = (View) findViewById(R.id.point_one);
        pointTwo = (View) findViewById(R.id.point_two);
        pointThree = (View) findViewById(R.id.point_three);
        pointFour = (View) findViewById(R.id.point_four);
        pointFive=findViewById(R.id.point_five);

        ArrayList<View> arrayList=new ArrayList<>();
        for (int i=0;i<5;i++){
            ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load("http://192.168.148.15:10002/profile/"+i+"-yingdao.jpg").into(imageView);
            arrayList.add(imageView);
            viewpager.setAdapter(new MyPagerAdapter(arrayList));
            viewpager.setCurrentItem(0);

            pointOne.setSelected(true);
            pointTwo.setSelected(false);
            pointThree.setSelected(false);
            pointFour.setSelected(false);
            pointFive.setSelected(false);

            viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    switch (position){
                        case 0 :
                            pointOne.setSelected(true);
                            pointTwo.setSelected(false);
                            pointThree.setSelected(false);
                            pointFour.setSelected(false);
                            pointFive.setSelected(false);
                            break;
                        case 1:
                            pointOne.setSelected(false);
                            pointTwo.setSelected(true);
                            pointThree.setSelected(false);
                            pointFour.setSelected(false);
                            pointFive.setSelected(false);
                            break;
                        case 2:
                            pointOne.setSelected(false);
                            pointTwo.setSelected(false);
                            pointThree.setSelected(true);
                            pointFour.setSelected(false);
                            pointFive.setSelected(false);
                            break;
                        case 3:
                            pointOne.setSelected(false);
                            pointTwo.setSelected(false);
                            pointThree.setSelected(false);
                            pointFour.setSelected(true);
                            pointFive.setSelected(false);
                            break;
                        case 4:
                            pointOne.setSelected(false);
                            pointTwo.setSelected(false);
                            pointThree.setSelected(false);
                            pointFour.setSelected(false);
                            pointFive.setSelected(true);
                            break;
                    }
                    if (position==4){
                        buttonIp.setVisibility(View.VISIBLE);
                        buttonZhuye.setVisibility(View.VISIBLE);
                        buttonIp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder=new AlertDialog.Builder(GuideViewActivity.this);
                                View view= LayoutInflater.from(GuideViewActivity.this).inflate(R.layout.wangluo,null,false);

                                builder.setTitle("网络设置").setCancelable(true).setView(view).create().show();
                            }
                        });
                        buttonZhuye.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(GuideViewActivity.this, LoginActivity.class));
                            }
                        });
                    }else {
                        buttonIp.setVisibility(View.INVISIBLE);
                        buttonZhuye.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }
}