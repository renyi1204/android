package com.ry_050.myapp.Adapter;

import android.content.Context;
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
