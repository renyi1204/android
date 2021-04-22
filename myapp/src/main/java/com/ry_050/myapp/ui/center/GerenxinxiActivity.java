package com.ry_050.myapp.ui.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.data.Getinfodata;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GerenxinxiActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView touxiang;
    private EditText name;
    private EditText sex;
    private EditText phone;
    private EditText idcard;
    private Button updatamima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenxinxi);
       initView();

    }

    private void updatauser() {
        updatamima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("userId", String.valueOf(MainActivity.uid))
                        .addFormDataPart("idCard",idcard.getText().toString())
                        .addFormDataPart("nickName",name.getText().toString())
                        .addFormDataPart("sex",sex.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/system/user/updata")
                        .method("POST", body)
                        .addHeader("Authorization", centerFragment.token1)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(GerenxinxiActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

    }

    private void initView() {
        inittoolbar("个人信息",true);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        name = (EditText) findViewById(R.id.name);
        sex = (EditText) findViewById(R.id.sex);
        phone = (EditText) findViewById(R.id.phone);
        idcard = (EditText) findViewById(R.id.idcard);
        updatamima = (Button) findViewById(R.id.updatamima);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.148.15:10002/getInfo")
                .method("GET", null)
                .addHeader("Authorization", centerFragment.token1)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                Getinfodata getinfodata=new Gson().fromJson(s,Getinfodata.class);
                runOnUiThread(()->{
                    MainActivity.uid=getinfodata.getUser().getUserId();
                    Toast.makeText(GerenxinxiActivity.this, "id"+MainActivity.uid, Toast.LENGTH_SHORT).show();
                    name.setText(getinfodata.getUser().getNickName());
                    sex.setText(getinfodata.getUser().getSex());
                    phone.setText(getinfodata.getUser().getPhonenumber());
                    idcard.setText(getinfodata.getUser().getIdCard());
                });
                idcard.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length()==18){
                            s.replace(2,14,"**********");
                        }
                    }
                });
                updatauser();
            }
        });


    }


}