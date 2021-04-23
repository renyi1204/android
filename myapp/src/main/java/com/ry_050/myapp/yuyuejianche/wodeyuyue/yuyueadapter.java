package com.ry_050.myapp.yuyuejianche.wodeyuyue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ry_050.myapp.R;

public class yuyueadapter extends RecyclerView.Adapter<yuyueadapter.myyuyue> {
    private Context context;
    private yuyuedata yuyue;

    public yuyueadapter(Context context, yuyuedata yuyue) {
        this.context = context;
        this.yuyue = yuyue;
    }

    @NonNull
    @Override
    public myyuyue onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.yuyueadapter,parent,false);
        return new myyuyue(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myyuyue holder, int position) {
            holder.yuyuePlate.setText("车牌号："+yuyue.getRows().get(position).getPlateNum());
            holder.yuyueTime.setText("预约时间"+yuyue.getRows().get(position).getAptTime());
            holder.yuyueAddress.setText("预约地点："+yuyue.getRows().get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return yuyue.getTotal();
    }

    public class myyuyue extends RecyclerView.ViewHolder {
        private TextView yuyuePlate;
        private TextView yuyueTime;
        private TextView yuyueAddress;



        public myyuyue(@NonNull View itemView) {
            super(itemView);
            yuyuePlate = (TextView) itemView.findViewById(R.id.yuyue_plate);
            yuyueTime = (TextView) itemView.findViewById(R.id.yuyue_time);
            yuyueAddress = (TextView) itemView.findViewById(R.id.yuyue_address);
        }
    }
}
