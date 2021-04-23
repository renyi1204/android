package com.ry_050.myapp.yuyuejianche.wodeyuyue;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class wodeyuyueFragment extends BaseFragment {
    private RecyclerView rc5;
    @Override
    protected int setlayout() {
        return R.layout.wodeyuyue_fragment;
    }

    @Override
    protected void initView() {
        inittoolbar("我的预约");
        rc5=findViewById(R.id.rc5);
        rc5.setLayoutManager(new LinearLayoutManager(getContext()));
        rc5.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/userinfo/apt/list?userId=23")
                .method("GET", null)
                .addHeader("Authorization", MainActivity.token)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                yuyuedata data=new Gson().fromJson(s,yuyuedata.class);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rc5.setAdapter(new yuyueadapter(getContext(),data));
                    }
                });
            }
        });
    }

}