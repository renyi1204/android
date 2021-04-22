package com.ry_050.myapp.ui.center;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.data.Getinfodata;
import com.ry_050.myapp.data.Logindata;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class centerFragment extends BaseFragment {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView imageView2;
    private TextView nickName;
    private Button gerenxinxi;
    private Button dingdanliebiao;
    private Button updatamima;
    private Button yijianfankui;
    private Button exit;
    public static String token1;

    @Override
    protected int setlayout() {
        return R.layout.center_fragment;
    }

    @Override
    protected void initView() {
        inittoolbar("个人中心");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        nickName = (TextView) findViewById(R.id.nickName);
        gerenxinxi = (Button) findViewById(R.id.gerenxinxi);
        dingdanliebiao = (Button) findViewById(R.id.dingdanliebiao);
        updatamima = (Button) findViewById(R.id.updatamima);
        yijianfankui = (Button) findViewById(R.id.yijianfankui);
        exit = (Button) findViewById(R.id.exit);


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"username\":\""+MainActivity.user+"\",\r\n    \"password\":\""+MainActivity.pwd+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                Logindata logindata=new Gson().fromJson(s,Logindata.class);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        token1=logindata.getToken();
                        Toast.makeText(getContext(), ""+token1, Toast.LENGTH_SHORT).show();
                        getlogin();
                    }
                });
            }
        });
        gerenxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(),GerenxinxiActivity.class));
            }
        });
        dingdanliebiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(),DingdanliebiaoActivity.class));
            }
        });
        updatamima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(),UpdatamimaActivity.class));
            }
        });
        yijianfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(),YijianfakuiActivity.class));
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(),ExitActivity.class));
            }
        });

    }

    private void getlogin() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/getInfo")
                .method("GET", null)
                .addHeader("Authorization", token1)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                Getinfodata getinfodata=new Gson().fromJson(s,Getinfodata.class);
                requireActivity().runOnUiThread(()->{
                    MainActivity.uid=getinfodata.getUser().getUserId();
                    Toast.makeText(getContext(), "id"+MainActivity.uid, Toast.LENGTH_SHORT).show();
                    nickName.setText(getinfodata.getUser().getNickName());
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }
}