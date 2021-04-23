package com.ry_050.myapp.ui.zhsq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;

public class wuyeguanliActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuyeguanli);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        call = (Button) findViewById(R.id.call);
        call = (Button) findViewById(R.id.call);
        call = (Button) findViewById(R.id.call);
        call = (Button) findViewById(R.id.call);
        inittoolbar("物业管理",true);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:15533232924")));
            }
        });
    }
}