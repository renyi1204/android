package com.ry_050.myapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.data.Appdata;
import com.ry_050.myapp.ducheyiche.zizhuyicheActivity;
import com.ry_050.myapp.yuyuejianche.yuyuejiancheActivity;

public class Appadapter extends RecyclerView.Adapter<Appadapter.myappad> {
    private Context context;
    private Appdata appdata;
    public Appadapter(Context context, Appdata appdata) {
        this.context = context;
        this.appdata = appdata;
    }

    @NonNull
    @Override
    public myappad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.app_item,null,false);
        return new myappad(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myappad holder, int position) {
        if (position==10){
            holder.appImg.setImageResource(R.mipmap.gengduofuwu);
            holder.appTitle.setText("更多服务");
            return;
        }
        holder.appTitle.setText(appdata.getRows().get(position).getServiceName());
        Glide.with(context).load(MainActivity.baseurl+appdata.getRows().get(position).getImgUrl()).into(holder.appImg);
        holder.appImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position){
                    case 0:
                    intent=new Intent(context, yuyuejiancheActivity.class);
                    break;
                    case 1:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    case 2:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    case 3:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    case 4:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    case 5:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    case 6:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    case 7:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    case 8:
                        intent=new Intent(context, zizhuyicheActivity.class);
                        break;
                    case 9:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                    default:
                        intent=new Intent(context, yuyuejiancheActivity.class);
                        break;
                }
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return appdata.getTotal()+1;
    }

    public class myappad extends RecyclerView.ViewHolder {
        private ImageView appImg;
        private TextView appTitle;
        public myappad(@NonNull View itemView) {
            super(itemView);
            appImg = (ImageView) itemView.findViewById(R.id.app_img);
            appTitle = (TextView)itemView.findViewById(R.id.app_title);
        }
    }
}
