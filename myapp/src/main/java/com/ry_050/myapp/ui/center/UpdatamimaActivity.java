package com.ry_050.myapp.ui.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.data.Updatamimadata;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdatamimaActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText oldpwd;
    private EditText newpassword;
    private Button buttonUpdatpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatamima);
        inittoolbar("修改密码",true);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        oldpwd = (EditText) findViewById(R.id.oldpwd);
        newpassword = (EditText) findViewById(R.id.newpassword);
        buttonUpdatpwd = (Button) findViewById(R.id.button_updatpwd);
        buttonUpdatpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n\"userId\": \""+ MainActivity.uid +"\",\r\n\"oldPwd\": \""+oldpwd.getText().toString()+"\",\r\n\"password\": \""+newpassword.getText().toString()+"\"\r\n}\r\n");
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/system/user/resetPwd")
                        .method("PUT", body)
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
                        Updatamimadata updatamimadata=new Gson().fromJson(s,Updatamimadata.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UpdatamimaActivity.this, "修改成功，新密码："+newpassword.getText().toString()+"请重新登录", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(UpdatamimaActivity.this,LoginActivity.class));
                            }
                        });
                    }
                });
            }
        });

    }
}