package com.example.pullxmldemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pullxmldemo.R;
import com.example.pullxmldemo.bean.NewsInfo;

import java.util.List;

/**
 * ListView的适配器
 */
public class DownLoadAdapter extends BaseAdapter{
    private List<NewsInfo> list;
    private Context context;

    public DownLoadAdapter(List<NewsInfo> list,Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
            holder=new ViewHolder();
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_des= (TextView) convertView.findViewById(R.id.tv_des);
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_author= (TextView) convertView.findViewById(R.id.tv_author);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_des.setText(list.get(position).getDescription());
        holder.tv_date.setText(list.get(position).getPubDate());
        holder.tv_author.setText(list.get(position).getAuthor());
        return convertView;
    }

    static class ViewHolder{
        TextView tv_title,tv_des,tv_date,tv_author;
    }
}














