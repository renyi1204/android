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
import com.ry_050.myapp.data.Appdata;
import com.ry_050.myapp.data.Hottopicdata;

public class Hottopicadapter extends RecyclerView.Adapter<Hottopicadapter.myappad> {
    private Context context;
    private Hottopicdata hottopicdata;

    public Hottopicadapter(Context context, Hottopicdata hottopicdata) {
        this.context = context;
        this.hottopicdata = hottopicdata;
    }

    @NonNull
    @Override
    public myappad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hottopic_item,null,false);
        return new myappad(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myappad holder, int position) {
        Glide.with(context).load(MainActivity.baseurl+hottopicdata.getRows().get(position).getImgUrl()).into(holder.hotImg);
        holder.hotTitle.setText(hottopicdata.getRows().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return hottopicdata.getTotal();
    }

    public class myappad extends RecyclerView.ViewHolder {
        private ImageView hotImg;
        private TextView hotTitle;
        public myappad(@NonNull View itemView) {
            super(itemView);
            hotImg = (ImageView) itemView.findViewById(R.id.hot_img);
            hotTitle = (TextView) itemView.findViewById(R.id.hot_title);
        }
    }
}
