package com.ry_050.exerciserapplication.ui.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ry_050.exerciserapplication.R;

import java.util.Calendar;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {
    private Button data;
    private EditText dataText;
    private Button time;
    private Button address;
    private EditText timeText;
    private EditText adressText;
    private ImageView show_image;
    private Button button_camera;
    Calendar calendar=Calendar.getInstance(Locale.CHINA);
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
        //创建视图之前
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();//创建视图之后
    }

    private void initView() {
        data = (Button) getView().findViewById(R.id.data);
        dataText = (EditText) getView().findViewById(R.id.data_text);
        time = (Button) getView().findViewById(R.id.time);
        address = (Button) getView().findViewById(R.id.address);
        timeText = (EditText) getView().findViewById(R.id.time_text);
        adressText = (EditText) getView().findViewById(R.id.adress_text);
        button_camera=getView().findViewById(R.id.camera);
        show_image=getView().findViewById(R.id.show_image);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Toast.makeText(getActivity(), "选择了"+year+"年"+month+"月"+dayOfMonth+"日", Toast.LENGTH_SHORT).show();
                    dataText.setText(String.format(year+"年"+month+"月"+dayOfMonth+"日")); //dayOfMonth  注意随便选
                }
            },year,month,day).show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hours=calendar.get(Calendar.HOUR_OF_DAY);
                int minute=calendar.get(Calendar.MINUTE);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getActivity(), "选择了"+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
                        timeText.setText(hourOfDay+":"+minute);
                    }
                },hours,minute,true).show();
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("选择你的检车地点");
                builder.setCancelable(true);
                String[] text=new String[] {
                        "1","2","3","4"
                };
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setIcon(R.mipmap.ic_launcher_round)
                        .setItems(text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "你选择的是"+text[which], Toast.LENGTH_SHORT).show();
                                adressText.setText(text[which]);
                            }
                        }).create();   //一定要有create（）创建
                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();  //点击后销毁
                    }
                });
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();//创建alertdialog
                dialog.show();//显示对话框
            }
        });

    button_camera.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,10);
        }
    });
    }

    //回调函数onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if (resultCode==RESULT_OK){
                    Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                    show_image.setImageBitmap(bitmap);
                }
                break;
        }
    }

}