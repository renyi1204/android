package com.ry_050.myapp.ducheyiche.zizhuyiche;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class zizhuyicheFragment extends BaseFragment {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText platenum;
    private EditText name1;
    private EditText phone1;
    private EditText idcard1;
    private EditText address1;
    private ImageView imageView_show;
    private Button button_camera;
    private Button button_sumbit;
    @Override
    protected int setlayout() {
        return R.layout.zizhuyiche_fragment;
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        platenum = (EditText) findViewById(R.id.platenum);
        name1 = (EditText) findViewById(R.id.name1);
        phone1 = (EditText) findViewById(R.id.phone1);
        idcard1 = (EditText) findViewById(R.id.idcard1);
        address1 = (EditText) findViewById(R.id.address1);
        imageView_show=findViewById(R.id.image_show);
        button_camera=findViewById(R.id.button_camera);
        button_sumbit=findViewById(R.id.button_sumbit1);
        inittoolbar("自助移车");

        address1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JDCityPicker cityPickerView=new JDCityPicker();
                cityPickerView.init(getActivity());
                JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
//                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
                cityPickerView.setConfig(jdCityConfig);

                cityPickerView.showCityPicker();
                cityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        super.onSelected(province, city, district);
                        address1.setText(province.getName()+city.getName()+district.getName());
                    }
                });
            }
        });
        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10);
            }
        });

        button_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("cardId",idcard1.getText().toString())
                        .addFormDataPart("names",name1.getText().toString())
                        .addFormDataPart("userId", String.valueOf(MainActivity.uid))
                        .addFormDataPart("tel",phone1.getText().toString())
                        .addFormDataPart("address",address1.getText().toString())
                        .addFormDataPart("plates",platenum.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/userinfo/caraction")
                        .method("POST", body)
                        .addHeader("Authorization", MainActivity.token)
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
                                Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                                View view=LayoutInflater.from(getContext()).inflate(R.layout.alertdialog_item,null,false);
                                TextView textView1=view.findViewById(R.id.plateNUm);
                                TextView textView=view.findViewById(R.id.carphone);

                                textView1.setText("车牌号："+platenum.getText().toString());
                                textView.setText("手机号："+phone1.getText().toString());
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone1.getText().toString())));
                                    }
                                });
                                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                                builder.setTitle("校验成功").setView(view).create().show();
                            }
                        });
                    }
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if (resultCode==RESULT_OK){
                    Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                    imageView_show.setImageBitmap(bitmap);
                }
        }
    }
}