package com.ry_050.myapp.ui.home;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.ry_050.myapp.Adapter.Appadapter;
import com.ry_050.myapp.Adapter.Hottopicadapter;
import com.ry_050.myapp.Adapter.Newslistadapter;
import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.R;
import com.ry_050.myapp.data.Appdata;
import com.ry_050.myapp.data.Hottopicdata;
import com.ry_050.myapp.data.NewsListdata;
import com.ry_050.myapp.data.Tabdata;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends BaseFragment {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText search;
    private ViewFlipper vfViewflipper;
    private RecyclerView rc1;
    private RecyclerView rc2;
    private RecyclerView rc3;
    private TabLayout tablayout;



    @Override
    protected int setlayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        inittoolbar("智慧城市");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        search = (EditText) findViewById(R.id.search);
        vfViewflipper = (ViewFlipper) findViewById(R.id.vf_viewflipper);
        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc2 = (RecyclerView) findViewById(R.id.rc2);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        rc3 = (RecyclerView) findViewById(R.id.rc3);
        edit_search();
        vf();
        Rc1();
        Rc2();
        tb1();

    }

    private void tb1() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/system/dict/data/type/press_category")
                .method("GET", null)
                .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                    String w=response.body().string();
                    Tabdata tabdata=new Gson().fromJson(w,Tabdata.class);
                    requireActivity().runOnUiThread(()->{
                        for (int i=0;i<tabdata.getData().size();i++){
                            tablayout.addTab(tablayout.newTab().setText(tabdata.getData().get(i).getDictLabel()).setTag(tabdata.getData().get(i).getDictCode()));
                            if (i==0){
                                Rc3(tabdata.getData().get(i).getDictCode());
                            }
                        }
                        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                Rc3(Integer.parseInt(tab.getTag().toString()));
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                    });
                    }
                });
    }

    private void Rc3(int dictCode) {
        rc3.setLayoutManager(new LinearLayoutManager(getContext()));
        rc3.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/press/press/list?pressCategory="+dictCode)
                .method("GET", null)
                .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                    String s=response.body().string();
                    NewsListdata newsListdata=new Gson().fromJson(s,NewsListdata.class);
                    requireActivity().runOnUiThread(()->{
                        rc3.setAdapter(new Newslistadapter(getContext(),newsListdata));
                    });

                    }
                });
    }


    private void Rc2() {
        rc2.setLayoutManager(new GridLayoutManager(getView().getContext(),2));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/press/press/list")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                Hottopicdata hottopicdata=new Gson().fromJson(s,Hottopicdata.class);
                requireActivity().runOnUiThread(()->{
                    rc2.setAdapter(new Hottopicadapter(getView().getContext(),hottopicdata));
                });

            }
        });
    }

    private void Rc1() {
        rc1.setLayoutManager(new GridLayoutManager(getView().getContext(),5));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/service/service/list?pageNum=1&pageSize=10")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();

                Appdata appdata=new Gson().fromJson(s,Appdata.class);

                requireActivity().runOnUiThread(()->{

                    Appdata.RowsBean rowsBean=new Appdata.RowsBean();
                    appdata.getRows().add(rowsBean);

                    rc1.setAdapter(new Appadapter(getView().getContext(),appdata));

                });
            }
        });
    }

    //搜索框事件
    private void edit_search() {
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH){
                    search.setText("");
                    startActivity(new Intent(getView().getContext(),SearcgActivity.class));
                }
                return false;
            }
        });
    }
    //轮播图
    private void vf() {
        for (int i=1;i<5;i++){
            ImageView imageView=new ImageView(getView().getContext());
            Glide.with(getView().getContext()).load("http://192.168.148.15:10002/profile/home"+i+".png").into(imageView);
            vfViewflipper.addView(imageView);
        }
        vfViewflipper.startFlipping();
        vfViewflipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getView().getContext(),xiangqingyeActivity.class).putExtra("img","http://192.168.148.15:10002/profile/home2.png")
                .putExtra("title","")
                .putExtra("content","111111111111111111111111111111111111111111111111111111111111111111111111111111"));

            }
        });
    }


}