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
import com.ry_050.myapp.data.Hottopicdata;
import com.ry_050.myapp.data.NewsListdata;

public class Newslistadapter extends RecyclerView.Adapter<Newslistadapter.myappad> {
    private Context context;
    private NewsListdata newsListdata;

    public Newslistadapter(Context context, NewsListdata newsListdata) {
        this.context = context;
        this.newsListdata = newsListdata;
    }

    @NonNull
    @Override
    public myappad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.newslist_item,parent,false);
        return new myappad(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myappad holder, int position) {
        Glide.with(context).load(MainActivity.baseurl+newsListdata.getRows().get(position).getImgUrl()).into(holder.newsImg);
        holder.newsTitle.setText(newsListdata.getRows().get(position).getTitle());
        holder.newsContent.setText(newsListdata.getRows().get(position).getContent());
        holder.newsLikeNumber.setText("点赞数："+newsListdata.getRows().get(position).getLikeNumber());
        holder.newsUpdateTime.setText("发布时间："+newsListdata.getRows().get(position).getUpdateTime());
    }

    @Override
    public int getItemCount() {
        return newsListdata.getTotal();
    }

    public class myappad extends RecyclerView.ViewHolder {
        private ImageView newsImg;
        private TextView newsTitle;
        private TextView newsContent;
        private TextView newsLikeNumber;
        private TextView newsUpdateTime;
        public myappad(@NonNull View itemView) {
            super(itemView);
            newsImg = (ImageView) itemView.findViewById(R.id.news_img);
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsContent = (TextView) itemView.findViewById(R.id.news_content);
            newsLikeNumber = (TextView) itemView.findViewById(R.id.news_likeNumber);
            newsUpdateTime = (TextView) itemView.findViewById(R.id.news_updateTime);
        }
    }
}
