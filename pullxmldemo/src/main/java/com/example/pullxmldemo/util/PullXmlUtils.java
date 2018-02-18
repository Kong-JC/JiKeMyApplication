package com.example.pullxmldemo.util;


import com.example.pullxmldemo.bean.NewsInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * pull解析xml的工具类
 */
public class PullXmlUtils {
    /**
     * 根据网络数据解析封装到集合中
     * @param xmlString
     * @return 封装后的集合
     */
    public static List<NewsInfo> PullXmlToList(byte[] xmlString){
        List<NewsInfo> list=null;
        NewsInfo info=null;
        boolean flag=false;
        try{
            //1.创建xml pull解析的工厂类
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            //2.构建xml pull解析的解析器
            XmlPullParser xmlPullParser=factory.newPullParser();
            //3.解析器加载需要解析的数据
            xmlPullParser.setInput(new ByteArrayInputStream(xmlString),"gbk");
            //4.获取当前解析的事件类型
            int eventType=xmlPullParser.getEventType();
            //5. 根据不同的事件类型执行相应的操作
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String tagName=xmlPullParser.getName();//获取标签名称
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT://文档开始读取
                        list=new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG://起始标签读取
                        if("item".equals(tagName)){
                            info=new NewsInfo();
                            flag=true;
                        }else if("title".equals(tagName) && flag){
                            info.setTitle(xmlPullParser.nextText());
                        }else if("description".equals(tagName) && flag){
                            info.setDescription(xmlPullParser.nextText());
                        }else if("pubDate".equals(tagName)){
                            info.setPubDate(xmlPullParser.nextText());
                        }else if("author".equals(tagName)){
                            info.setAuthor(xmlPullParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG://读取到结束标签
                        if("item".equals(tagName)){
                            list.add(info);
                            flag=false;
                        }
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}











