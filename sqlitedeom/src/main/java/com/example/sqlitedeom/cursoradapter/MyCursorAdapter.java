package com.example.sqlitedeom.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sqlitedeom.R;

/**
 * Created by Kong on 2018/2/17.
 */

public class MyCursorAdapter extends CursorAdapter {

    private static final String TAG = "MyCursorAdapter";

    public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        view.setTag(new ViewHolder(view));
        Log.d(TAG, "newView: " + view);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvName.setText(cursor.getString(cursor.getColumnIndex("name")));
        holder.tvTeacher.setText(cursor.getString(cursor.getColumnIndex("teacher")));
        holder.tvPrice.setText(cursor.getDouble(cursor.getColumnIndex("price")) + "");
        Log.d(TAG, "bindView: " + view);
    }

    private static class ViewHolder {
        private TextView tvName, tvTeacher, tvPrice;

        public ViewHolder(View view) {
            this.tvName = view.findViewById(R.id.tvName);
            this.tvTeacher = view.findViewById(R.id.tvTeacher);
            this.tvPrice = view.findViewById(R.id.tvPrice);
        }
    }

}
