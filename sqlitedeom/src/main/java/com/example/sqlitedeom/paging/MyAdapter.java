package com.example.sqlitedeom.paging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlitedeom.MyApplication;
import com.example.sqlitedeom.R;
import com.example.sqlitedeom.cursoradapter.Course;

import java.util.List;

/**
 * Created by Kong on 2018/2/17.
 */

public class MyAdapter extends BaseAdapter {

    private List<Course> courseList;
    private Context context;

    public MyAdapter(List<Course> courseList) {
        this.context = MyApplication.getContext();
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Course getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = courseList.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(course.getName());
        holder.tvTeacher.setText(course.getTeacher());
        holder.tvPrice.setText(course.getPrice());
        return convertView;
    }

    private static final class ViewHolder {
        private TextView tvName, tvTeacher, tvPrice;

        public ViewHolder(View view) {
            tvName = view.findViewById(R.id.tvName);
            tvTeacher = view.findViewById(R.id.tvTeacher);
            tvPrice = view.findViewById(R.id.tvPrice);
        }
    }

}
