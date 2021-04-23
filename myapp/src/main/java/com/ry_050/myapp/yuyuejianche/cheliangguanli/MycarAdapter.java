package com.ry_050.myapp.yuyuejianche.cheliangguanli;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ry_050.myapp.R;

public class MycarAdapter extends RecyclerView.Adapter<MycarAdapter.mycar> {
    private Context context;
    private CarData carData;
    private AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public MycarAdapter(Context context, CarData carData) {
        this.context = context;
        this.carData = carData;
    }

    @NonNull
    @Override
    public mycar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mycar_item,parent,false);
        return new mycar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mycar holder, int position) {
        holder.tvUserid.setText("车辆ID"+carData.getRows().get(position).getId());
        holder.tvPlateNum.setText("车牌号："+carData.getRows().get(position).getPlateNum());
        holder.tvMainNum.setText("车架号："+carData.getRows().get(position).getMainNum());
        holder.tvCarType.setText("车辆类型："+carData.getRows().get(position).getCarType());
        holder.tvPhone.setText("手机号："+carData.getRows().get(position).getPhone());
        holder.tvMileage.setText("里程数"+carData.getRows().get(position).getMileage());

        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(null,v,position,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return carData.getTotal();
    }

    public class mycar extends RecyclerView.ViewHolder {
        private TextView tvUserid;
        private TextView tvPlateNum;
        private TextView tvMainNum;
        private TextView tvCarType;
        private TextView tvPhone;
        private TextView tvMileage;
        public mycar(@NonNull View itemView) {
            super(itemView);
            tvUserid = (TextView) itemView.findViewById(R.id.tv_userid);
            tvPlateNum = (TextView) itemView.findViewById(R.id.tv_plateNum);
            tvMainNum = (TextView) itemView.findViewById(R.id.tv_mainNum);
            tvCarType = (TextView) itemView.findViewById(R.id.tv_carType);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            tvMileage = (TextView) itemView.findViewById(R.id.tv_mileage);
        }
    }
}
