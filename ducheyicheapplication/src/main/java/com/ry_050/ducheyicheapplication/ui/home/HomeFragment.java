package com.ry_050.ducheyicheapplication.ui.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ry_050.ducheyicheapplication.BastFragment;
import com.ry_050.ducheyicheapplication.MainActivity;
import com.ry_050.ducheyicheapplication.R;
import com.ry_050.ducheyicheapplication.ui.data.MallAreaListBean;
import com.ry_050.ducheyicheapplication.ui.data.MallCityListBean;
import com.ry_050.ducheyicheapplication.ui.data.MallprovinceListBean;
import com.ry_050.ducheyicheapplication.ui.data.YIcheData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BastFragment {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText plate;
    private EditText name;
    private EditText photo;
    private EditText idcard;
    private TextView textView;
    private Spinner spinner;
    private TextView textView2;
    private Spinner spinner4;
    private TextView textView3;
    private Spinner spinner5;
    private EditText name2;
    private ImageView imageShow;
    private Button buttonSubmit;
    private TextView textView4;
    private List<String> ProvinveList,CityList,AreaList;
    private ArrayAdapter<String> provinceAdapter,cityAdapter,areaAdaper;
    private MallprovinceListBean[] mallprovinceListBeans;
    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        plate = (EditText) findViewById(R.id.plate);
        name = (EditText) findViewById(R.id.name);
        photo = (EditText) findViewById(R.id.photo);
        idcard = (EditText) findViewById(R.id.idcard);
        spinner = (Spinner) findViewById(R.id.yiche_sheng);
        spinner4 = (Spinner) findViewById(R.id.yiche_shi);
        spinner5 = (Spinner) findViewById(R.id.yiche_qu);
        name2 = (EditText) findViewById(R.id.name2);
        imageShow = (ImageView) findViewById(R.id.imgae_show);
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        textView4 = (TextView) findViewById(R.id.textView4);

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10);
            }
        });
        loadAddressSpinner();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(plate.getText().toString())&&
                TextUtils.isEmpty(name.getText().toString())&&
                TextUtils.isEmpty(photo.getText().toString())&&
                TextUtils.isEmpty(idcard.getText().toString())&&
                TextUtils.isEmpty(name2.getText().toString())){
                    Toast.makeText(getView().getContext(), "输入不可为空", Toast.LENGTH_SHORT).show();
                }else {
                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .build();
                    MediaType mediaType = MediaType.parse("text/plain");
                    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("cardId",idcard.getText().toString())
                            .addFormDataPart("names",name.getText().toString())
                            .addFormDataPart("userId","23")
                            .addFormDataPart("tel",photo.getText().toString())
                            .addFormDataPart("address",spinner.getSelectedItem().toString()
                                    +spinner4.getSelectedItem().toString()
                                    +spinner5.getSelectedItem().toString()
                                    +name2.getText().toString())
                            .addFormDataPart("plates",plate.getText().toString())
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
                            String s=response.body().string();
                            YIcheData yIcheData=new Gson().fromJson(s,YIcheData.class);
                            requireActivity().runOnUiThread(()->{
                                if (yIcheData.getCode()==200){
                                    View view=LayoutInflater.from(getActivity()).inflate(R.layout.call,null,false);
                                    TextView carname=view.findViewById(R.id.carname);
                                    TextView carphoto=view.findViewById(R.id.carphoto);

                                    carname.setText("车主："+name.getText().toString());
                                    carphoto.setText("车牌号"+photo.getText().toString());
                                    carphoto.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + photo.getText().toString())));
                                        }
                                    });
                                    AlertDialog.Builder a=new AlertDialog.Builder(getView().getContext());
                                    a.setTitle("校验成功").setCancelable(true).setView(view).create().show();
                                }
                            });
                        }
                    });




                }
            }
        });



    }
    //省
    private List<String> getProvinveList(MallprovinceListBean[] provinceList){
        if (ProvinveList==null){
            ProvinveList=new ArrayList<>();
        }else{
            ProvinveList.clear();
        }
        for (MallprovinceListBean province:provinceList){
            ProvinveList.add(province.getProvinceName());
        }
        return ProvinveList;
    }
    //市
    private List<String> getCityList(MallprovinceListBean[] provinceList,String provinceName){
        if (CityList==null){
            CityList=new ArrayList<>();
        }else {
            CityList.clear();
        }
            for (MallprovinceListBean province:provinceList){
            if (province.getProvinceName().equals(provinceName)){
                for (MallCityListBean city: province.getMallCityList()){
                    CityList.add(city.getCityName());
                }
                break;
            }
        }
            return CityList;
    }
    //区
    private List<String> getAreaList(MallprovinceListBean[] provinceList,String provinceName,String cityName){
        if (AreaList==null){
            AreaList=new ArrayList<>();
        }else {
            AreaList.clear();
        }
        for (MallprovinceListBean province:provinceList){
            if (province.getProvinceName().equals(provinceName)){
                for (MallCityListBean city:province.getMallCityList()){
                    if (city.getCityName().equals(cityName)){
                        for (MallAreaListBean Area:city.getMallAreaList()){
                            AreaList.add(Area.getAreaName());
                        }
                    }
                }
            }
        }
        return AreaList;
    }

    private void loadAddressSpinner(){
        InputStream is=getResources().openRawResource(R.raw.cities);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
//        Gson gson=new GsonBuilder().create();
        mallprovinceListBeans=new Gson().fromJson(bufferedReader,MallprovinceListBean[].class);
//        mallCityListBeans=new Gson().fromJson(bufferedReader,MallCityListBean[].class);
//        mallAreaListBeans=new Gson().fromJson(bufferedReader,MallAreaListBean[].class);

        ProvinveList=getProvinveList(mallprovinceListBeans);
        CityList=getCityList(mallprovinceListBeans,ProvinveList.get(0));
        AreaList=getAreaList(mallprovinceListBeans,ProvinveList.get(0),CityList.get(0));

        //设置适配器
        provinceAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,ProvinveList);
        spinner.setAdapter(provinceAdapter);

        cityAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,CityList);
        spinner4.setAdapter(cityAdapter);

        areaAdaper=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,AreaList);
        spinner5.setAdapter(areaAdaper);
        //监听事件

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String province=ProvinveList.get(position);
                CityList=getCityList(mallprovinceListBeans,ProvinveList.get(position));
                cityAdapter.notifyDataSetChanged();
                spinner4.setSelection(0);

                AreaList=getAreaList(mallprovinceListBeans,ProvinveList.get(position),CityList.get(0));
                areaAdaper.notifyDataSetChanged();
                spinner5.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AreaList=getAreaList(mallprovinceListBeans,ProvinveList.get(spinner.getSelectedItemPosition()),CityList.get(position));
                areaAdaper.notifyDataSetChanged();
                spinner5.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
            if (resultCode == RESULT_OK) {
                Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                imageShow.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_home;
    }
}