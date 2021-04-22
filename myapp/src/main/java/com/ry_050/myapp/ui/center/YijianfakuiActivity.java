package com.ry_050.myapp.ui.center;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ry_050.myapp.BaseActivity;
import com.ry_050.myapp.R;

public class YijianfakuiActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private EditText editYijianfankui;
    private Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijianfakui);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        editYijianfankui = (EditText) findViewById(R.id.edit_yijianfankui);
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        inittoolbar("意见反馈",true);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editYijianfankui.getText().clear();
            Toast.makeText(YijianfakuiActivity.this,"提交成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}