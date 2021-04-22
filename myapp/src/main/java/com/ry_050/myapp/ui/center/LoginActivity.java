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
import com.ry_050.myapp.data.Logindata;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText editLogin;
    private EditText editPassword;
    private Button button_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        inittoolbar("登录",false);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        editLogin = (EditText) findViewById(R.id.edit_login);
        editPassword = (EditText) findViewById(R.id.edit_password);
        button_login=findViewById(R.id.button_Login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "{\r\n    \"username\":\""+editLogin.getText().toString()+"\",\r\n    \"password\":\""+editPassword.getText().toString()+"\"\r\n}");
                Request request = new Request.Builder()
                        .url("http://192.168.148.15:10002/login")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String ss=response.body().string();
                        Logindata logindata=new Gson().fromJson(ss,Logindata.class);
                        runOnUiThread(()->{
                            if (logindata.getCode()==200){
//                                Toast.makeText(LoginActivity.this, "200", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("login",editLogin.getText().toString())
                                        .putExtra("pwd",editPassword.getText().toString()));
                            }else {
                                Toast.makeText(LoginActivity.this, "密码错误请重新输入密码", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });
    }
}