package com.ry_050.myapp.yuyuejianche.cheliangguanli;

import androidx.appcompat.widget.Toolbar;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.ui.center.centerFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class changliangguanliFragment extends BaseFragment {
    private RecyclerView carRecyclerview;
    private EditText plateNum;
    private EditText mainNum;
    private EditText carType;
    private EditText phone;
    private EditText mileage;
    private EditText carID;
    private Button updatacar;
    private Button addcar;
    private Button deletecar;
    private Button ree;
    private int idd;
    @Override
    protected int setlayout() {
        return R.layout.changliangguanli_fragment;
    }

    @Override
    protected void initView() {
        inittoolbar("车辆管理");
        carRecyclerview = (RecyclerView) findViewById(R.id.car_recyclerview);
        plateNum = (EditText) findViewById(R.id.plateNum);
        mainNum = (EditText) findViewById(R.id.mainNum);
        carType = (EditText) findViewById(R.id.carType);
        phone = (EditText) findViewById(R.id.phone);
        mileage = (EditText) findViewById(R.id.mileage);
        updatacar = (Button) findViewById(R.id.updatacar);
        addcar = (Button) findViewById(R.id.addcar);
        deletecar = (Button) findViewById(R.id.deletecar);
        carID=findViewById(R.id.CarID);
        ree=findViewById(R.id.reee);

        rc1();
        //增加车辆
        addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n\"userId\":\""+MainActivity.uid+"\",\r\n\"plateNum\":\""+plateNum.getText().toString()+"\",\r\n\"mainNum\":\""+mainNum.getText().toString()+"\",\r\n\"carType\":\""+carType.getText().toString()+"\",\r\n\"phone\":\""+phone.getText().toString()+"\",\r\n\"mileage\":\""+mileage.getText().toString()+"\"\r\n}\r\n");
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/userinfo/cars")
                        .method("POST", body)
                        .addHeader("Authorization", MainActivity.token)
                        .addHeader("Content-Type", "application/json")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "增加成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        //删除车辆
        deletecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "\r\n");
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/userinfo/cars/"+idd)
                        .method("DELETE", body)
                        .addHeader("Authorization",MainActivity.token)
                        .addHeader("Content-Type", "application/json")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        //修改车辆
        updatacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{ \r\n \"id\":\""+idd+"\",\r\n\"plateNum\":\""+plateNum.getText().toString()+"\",\r\n\"mainNum\":\""+mainNum.getText().toString()+"\",\r\n\"carType\":\""+carType.getText().toString()+"\",\r\n\"phone\":\""+phone.getText().toString()+"\",\r\n\"mileage\":\""+mileage.getText().toString()+"\"\r\n}\r\n");
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/userinfo/cars")
                        .method("PUT", body)
                        .addHeader("Authorization", MainActivity.token)
                        .addHeader("Content-Type", "application/json")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        //刷新
        ree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rc1();
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void rc1() {
        carRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        carRecyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/userinfo/cars/list?userId="+ MainActivity.uid+"")
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
                CarData carData=new Gson().fromJson(s,CarData.class);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    MycarAdapter mycarAdapter=new MycarAdapter(getContext(),carData);
                    mycarAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            carID.setText(carData.getRows().get(position).getId()+"");
                            plateNum.setText(carData.getRows().get(position).getPlateNum());
                            mainNum.setText(carData.getRows().get(position).getMainNum());
                            carType.setText(carData.getRows().get(position).getCarType());
                            mileage.setText(carData.getRows().get(position).getMileage());
                            phone.setText(carData.getRows().get(position).getPhone());
                            idd=carData.getRows().get(position).getId();
                        }
                    });
                        carRecyclerview.setAdapter(mycarAdapter);
                    }
                });
            }
        });
    }

}