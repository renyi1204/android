package com.ry_050.myapp.ui.zhsq;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.R;
import com.ry_050.myapp.ui.home.xiangqingyeActivity;

public class zhsqFragment extends BaseFragment {
    private ViewFlipper vfff;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private TextView textView3;
    private TextView textView4;

    private ConstraintLayout wuyefuwu;
    private ConstraintLayout kuaijianguanli;
    private ConstraintLayout youlinshejiao;
    private ConstraintLayout shangyeguiguang;
    private ConstraintLayout cheliangguanli;
    private ImageView imageView4;
    private TextView textView6;
    private TextView textView7;


    @Override
    protected int setlayout() {
        return R.layout.zhsq_fragment;
    }

    @Override
    protected void initView() {
    vfff=findViewById(R.id.fvvvv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        wuyefuwu = (ConstraintLayout) findViewById(R.id.wuyefuwu);
        kuaijianguanli = (ConstraintLayout) findViewById(R.id.kuaijianguanli);
        youlinshejiao = (ConstraintLayout) findViewById(R.id.youlinshejiao);
        shangyeguiguang = (ConstraintLayout) findViewById(R.id.shangyeguiguang);
        cheliangguanli = (ConstraintLayout) findViewById(R.id.changliangguanli);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        inittoolbar("智慧社区");
        vfff.startFlipping();
        vfff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(), xiangqingyeActivity.class));
            }
        });
        wuyefuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(), wuyeguanliActivity.class));
            }
        });
        kuaijianguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(), kuaijianguanliActivity.class));
            }
        });
        youlinshejiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(), youlinshejiaoActivity.class));
            }
        });
        shangyeguiguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(), shangyetuiguangActivity.class));
            }
        });
        cheliangguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(), wuyeguanliActivity.class));
            }
        });


    }
}