package com.ry_050.myapp.yuyuejianche.lijiyuyue;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.yuyuejianche.cheliangguanli.CarData;
import com.ry_050.myapp.yuyuejianche.cheliangguanli.MycarAdapter;

import java.io.IOException;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class lijiyuyueFragment extends BaseFragment {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private RecyclerView rc4;
    private Button data;
    private Button time;
    private Button address;
    private Button buttonYuyue;
    private int carid;
    Calendar calendar=Calendar.getInstance(Locale.CHINA);
    private BottomNavigationView yuyuejiancheNav;


    String[] list=new String[15];
    private int addressid;
    @Override
    protected int setlayout() {
        return R.layout.lijiyuyue_fragment;
    }

    @Override
    protected void initView() {
        inittoolbar("立即预约");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        rc4 = (RecyclerView) findViewById(R.id.rc4);
        data = (Button) findViewById(R.id.data);
        time = (Button) findViewById(R.id.time);
        address = (Button) findViewById(R.id.address);
        buttonYuyue = (Button) findViewById(R.id.button_yuyue);
        yuyuejiancheNav =  getActivity().findViewById(R.id.yuyuejianche_nav);


        rc1();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/userinfo/place/list")
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
                Adressdata adressdata=new Gson().fromJson(s,Adressdata.class);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<adressdata.getTotal();i++){
                            list[i]=adressdata.getRows().get(i).getAddress();
                        }
                    }
                });
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Toast.makeText(getContext(), ""+year+"-"+(month+1)+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
                    data.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                }
            },year,month,day).show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
            int hours=calendar.get(Calendar.HOUR_OF_DAY);
            int minutes=calendar.get(Calendar.MINUTE);
            new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Toast.makeText(getContext(), ""+hours+":"+minutes, Toast.LENGTH_SHORT).show();
                    time.setText(hourOfDay+":"+minute);
                }
            },hours,minutes,true).show();
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("请选择地点").setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        address.setText(list[which]);
                        addressid=which;
                        Toast.makeText(getContext(), ""+list[which], Toast.LENGTH_SHORT).show();

                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });
        buttonYuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n\"userId\":\""+MainActivity.uid+"\",\r\n\"carId\":\""+carid+"\",\r\n\"aptTime\":\""+data.getText().toString()+time.getText().toString()+"\",\r\n\"addressId\":\""+addressid+"\"\r\n}\r\n\r\n");
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/userinfo/apt")
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
                        requireActivity().runOnUiThread(()->{
                            Toast.makeText(getContext(), "预约成功", Toast.LENGTH_SHORT).show();
                            yuyuejiancheNav.setSelectedItemId(R.id.wodeyuyue);
                        });
                    }
                });
            }
        });
    }

    private void rc1() {
        rc4.setLayoutManager(new LinearLayoutManager(getContext()));
        rc4.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
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
                        ChoosecarAdapter choosecarAdapter=new ChoosecarAdapter(getContext(),carData);
                        choosecarAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                choosecarAdapter.setRadpostion(position);
                                carid=carData.getRows().get(position).getId();
                            }
                        });
                        rc4.setAdapter(choosecarAdapter);
                    }
                });
            }
        });
    }


}