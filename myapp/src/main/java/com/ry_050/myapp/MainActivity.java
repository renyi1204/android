package com.ry_050.myapp;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.ry_050.myapp.data.Getinfodata;
import com.ry_050.myapp.data.Logindata;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    public static String token;
    public static String baseurl="http://192.168.148.15:10002";
    public static int uid;
    public static String user;
    public static String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        initView();
    }

    private void initView() {
        user=getIntent().getStringExtra("login");
        pwd=getIntent().getStringExtra("pwd");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"username\":\""+user+"\",\r\n    \"password\":\""+pwd+"\"\r\n}");
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
                Logindata  logindata=new Gson().fromJson(ss,Logindata.class);
                token=logindata.getToken();
                runOnUiThread(()->{
                    Toast.makeText(MainActivity.this, "登陆成功"+token, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

}