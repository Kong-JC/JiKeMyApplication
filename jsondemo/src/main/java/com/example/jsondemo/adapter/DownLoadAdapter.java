package com.example.jsondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jsondemo.R;
import com.example.jsondemo.bean.WeatherInfo;

import java.util.List;

/**
 *
 */
public class DownLoadAdapter extends BaseAdapter{
    private List<WeatherInfo> list;
    private Context context;

    public DownLoadAdapter(List<WeatherInfo> list,Context context){
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
        if(holder==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
            holder=new ViewHolder();
            holder.iv_icon01= (ImageView) convertView.findViewById(R.id.iv_icon01);
            holder.iv_icon02= (ImageView) convertView.findViewById(R.id.iv_icon02);
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_temp= (TextView) convertView.findViewById(R.id.tv_temp);
            holder.tv_weather= (TextView) convertView.findViewById(R.id.tv_weather);
            holder.tv_wind= (TextView) convertView.findViewById(R.id.tv_wind);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //设置天气图标
        String iconName=list.get(position).getIcon();//获取天气图标的名字          "icon": "d00|n00",
        String dayName=iconName.substring(0,iconName.indexOf("|"));
        String nightName=iconName.substring(iconName.indexOf("|")+1);
        //getIdentifier(资源名称,获取资源位置,资源的包名)
        int imgId01=context.getResources().getIdentifier(dayName,"drawable",context.getPackageName());
        int imageId02=context.getResources().getIdentifier(nightName,"drawable",context.getPackageName());

        holder.iv_icon01.setImageResource(imgId01);
        holder.iv_icon02.setImageResource(imageId02);

        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_weather.setText(list.get(position).getWeather());
        holder.tv_temp.setText(list.get(position).getTemperature());
        holder.tv_wind.setText(list.get(position).getWinddirect());
        return convertView;
    }
    static class ViewHolder{
        ImageView iv_icon01,iv_icon02;
        TextView tv_date,tv_weather,tv_temp,tv_wind;
    }
}











