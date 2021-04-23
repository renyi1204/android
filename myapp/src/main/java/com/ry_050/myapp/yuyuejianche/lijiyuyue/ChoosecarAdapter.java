package com.ry_050.myapp.yuyuejianche.lijiyuyue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ry_050.myapp.R;
import com.ry_050.myapp.yuyuejianche.cheliangguanli.CarData;

public class ChoosecarAdapter extends RecyclerView.Adapter<ChoosecarAdapter.mycar> {
    private Context context;
    private CarData carData;
    private AdapterView.OnItemClickListener onItemClickListener;
    private int radpostion;

    public void setRadpostion(int radpostion) {
        this.radpostion = radpostion;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public ChoosecarAdapter(Context context, CarData carData) {
        this.context = context;
        this.carData = carData;
    }

    @NonNull
    @Override
    public mycar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.choosecar_item,parent,false);
        return new mycar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mycar holder, int position) {
        holder.platename.setText("车牌号："+carData.getRows().get(position).getPlateNum());
//        holder.chooseRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
        if (radpostion==position){
            holder.chooseRadio.setChecked(true);
        }else {
            holder.chooseRadio.setChecked(false);
        }

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
        private TextView platename;
        private RadioButton chooseRadio;
        public mycar(@NonNull View itemView) {
            super(itemView);
            platename = (TextView)itemView.findViewById(R.id.platename);
            chooseRadio = (RadioButton)itemView.findViewById(R.id.choose_radio);
        }
    }
}
