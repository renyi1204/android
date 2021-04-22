package com.ry_050.ducheyicheapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {


    public void inittoolbar (String string){
        Toolbar toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView textView=findViewById(R.id.toolbar_title);
        textView.setText(string);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();
            }
        });
    }
}
