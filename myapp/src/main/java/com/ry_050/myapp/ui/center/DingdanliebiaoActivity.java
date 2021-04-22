package com.ry_050.myapp.ui.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.ry_050.myapp.Adapter.Dingdanadapter;
import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.data.Dingdandata;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DingdanliebiaoActivity extends BaseActivity {
    private TabLayout zf;
    private TabItem yzf;
    private TabItem wzf;
    private RecyclerView rcZhifu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdanliebiao);
        inittoolbar("订单列表",true);
        zf = (TabLayout) findViewById(R.id.zf);
        yzf = (TabItem) findViewById(R.id.yzf);
        wzf = (TabItem) findViewById(R.id.wzf);
        rcZhifu = (RecyclerView) findViewById(R.id.rc_zhifu);
        rcZhifu.setLayoutManager(new LinearLayoutManager(this));
        rcZhifu.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/userinfo/orders/list")
                .method("GET", null)
                .addHeader("Authorization", centerFragment.token1)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            String s=response.body().string();
                Dingdandata dingdandata=new Gson().fromJson(s,Dingdandata.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rcZhifu.setAdapter(new Dingdanadapter(getApplicationContext(),dingdandata));
                        Toast.makeText(DingdanliebiaoActivity.this, "", Toast.LENGTH_SHORT).show();

                    zf.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            if (tab.getText().toString().equals("已支付")){
                                rcZhifu.setVisibility(View.VISIBLE);
                            }else {
                                rcZhifu.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    }
                });
            }
        });
    }
}