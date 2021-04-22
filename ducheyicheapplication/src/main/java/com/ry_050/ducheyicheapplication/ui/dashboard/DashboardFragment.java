package com.ry_050.ducheyicheapplication.ui.dashboard;

import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ry_050.ducheyicheapplication.BastFragment;
import com.ry_050.ducheyicheapplication.MainActivity;
import com.ry_050.ducheyicheapplication.R;
import com.ry_050.ducheyicheapplication.ui.adapter.Yichelishiadapter;
import com.ry_050.ducheyicheapplication.ui.data.YichelishiData;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends BastFragment {
    private RecyclerView recyclerView;

    @Override
    protected void initView() {
        recyclerView=findViewById(R.id.rc1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/userinfo/caraction/list?userId=23")
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
                YichelishiData yichelishiData=new Gson().fromJson(s, YichelishiData.class);
                requireActivity().runOnUiThread(()->{
                    recyclerView.setAdapter(new Yichelishiadapter(getActivity(), yichelishiData));
                    Toast.makeText(getActivity(), "查询成功", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_dashboard;
    }
}