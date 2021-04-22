package com.ry_050.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ry_050.myapp.MainActivity;
import com.ry_050.myapp.R;
import com.ry_050.myapp.data.Dingdandata;
import com.ry_050.myapp.data.NewsListdata;

public class Dingdanadapter extends RecyclerView.Adapter<Dingdanadapter.myappad> {
    private Context context;
    private Dingdandata dingdandata;

    public Dingdanadapter(Context context, Dingdandata dingdandata) {
        this.context = context;
        this.dingdandata = dingdandata;
    }

    @NonNull
    @Override
    public myappad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dingdan_item,parent,false);
        return new myappad(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myappad holder, int position) {
        holder.orderNum.setText("订单号："+dingdandata.getData().get(position).getOrderNum());
        holder.path.setText(dingdandata.getData().get(position).getPath());
        holder.createTime.setText("订单创建时间："+dingdandata.getData().get(position).getCreateTime());
    }

    @Override
    public int getItemCount() {
        return dingdandata.getData().size();
    }

    public class myappad extends RecyclerView.ViewHolder {
        private TextView orderNum;
        private TextView path;
        private TextView createTime;
        public myappad(@NonNull View itemView) {
            super(itemView);
            orderNum = (TextView)itemView. findViewById(R.id.orderNum);
            path = (TextView)itemView. findViewById(R.id.path);
            createTime = (TextView)itemView. findViewById(R.id.createTime);
        }
    }
}
