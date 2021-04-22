package com.ry_050.ducheyicheapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ry_050.ducheyicheapplication.R;
import com.ry_050.ducheyicheapplication.ui.data.YIcheData;
import com.ry_050.ducheyicheapplication.ui.data.YichelishiData;

public class Yichelishiadapter extends RecyclerView.Adapter<Yichelishiadapter.mylishi>{
    private Context context;
    private YichelishiData yichelishiData;

    public Yichelishiadapter(Context context, YichelishiData yichelishiData) {
        this.context = context;
        this.yichelishiData = yichelishiData;
    }

    @NonNull
    @Override
    public mylishi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rc_item,null,false);
        return new mylishi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mylishi holder, int position) {
        holder.plates.setText(yichelishiData.getRows().get(position).getPlates());
        holder.tel.setText(yichelishiData.getRows().get(position).getTel());
        holder.address.setText(yichelishiData.getRows().get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return yichelishiData.getTotal();
    }

    public class mylishi extends RecyclerView.ViewHolder {
        private TextView plates;
        private TextView tel;
        private TextView address;
        public mylishi(@NonNull View itemView) {
            super(itemView);
            plates = (TextView) itemView.findViewById(R.id.plates);
            tel = (TextView) itemView.findViewById(R.id.tel);
            address = (TextView)itemView.findViewById(R.id.address);
        }
    }
}
