package com.example.jsondemo.util;


import com.example.jsondemo.bean.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * json解析的工具类
 */
public class ParserJsonUtils {
    /**
     * 根据json字符串解析封装成实体类并且存储到集合中
     * @param jsonString json字符串
     * @return   集合
     */
    public static List<WeatherInfo> parserJsonToList(String jsonString){
        List<WeatherInfo> list=new ArrayList<>();
        try {
            //1.将json字符串封装成jsonObject对象
            JSONObject jsonObject=new JSONObject(jsonString);
            //2.根据key获取value值  数组
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            //3.循环数组将数据封装到list集合中
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=jsonArray.getJSONObject(i);
                //将jsonobject中的value值封装成属性值
                WeatherInfo info=new WeatherInfo();
                info.setDate(obj.getString("date"));
                info.setIcon(obj.getString("icon"));
                info.setWeather(obj.getString("weather"));
                info.setTemperature(obj.getString("temperature"));
                info.setWinddirect(obj.getString("winddirect"));
                list.add(info);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
















