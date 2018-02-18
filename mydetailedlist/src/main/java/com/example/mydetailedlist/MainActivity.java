package com.example.mydetailedlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mydetailedlist.model.Item;
import com.example.mydetailedlist.utils.SPHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listItem; // ListView

    private List<Item> itemList; // 待办事项列表

    private ArrayAdapter adapter; // ListView的适配器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //获得添加按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //绑定点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItemActivity.startActivity(MainActivity.this);
            }
        });


        // 绑定  ListView
        listItem = (ListView) findViewById(R.id.list_item);


        // 设置Adapter
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, getData());
        listItem.setAdapter(adapter);

        // 添加item的监听事件
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //点击时弹出对话框，让用户选择修改或者是完成

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                String[] opt = {"修改", "完成"};

                builder.setItems(opt, new OnOptListener(itemList.get(i)));
                builder.show();

            }
        });

    }


    /**
     * item的点击事件
     */
    private class OnOptListener implements DialogInterface.OnClickListener {

        private Item item;

        public OnOptListener(Item item) {
            this.item = item;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {


            switch (i) {
                case 0:
                    // 点击修改
                    EditItemActivity.startActivity(MainActivity.this, item);

                    break;

                case 1:

                    //点击完成
                    
                    //删除数据
                    SPHelper.delNode(MainActivity.this, item);

                    //更新界面
                    updateData();

                    break;
            }

            //隐藏弹出框
            dialogInterface.dismiss();

        }

    }


    /**
     * 获取本地数据
     *
     * @return
     */
    private List<String> getData() {

        itemList = SPHelper.getItemList(this);

        List<String> data = new ArrayList<String>();

        for (int i = 0; i < itemList.size(); i++) {
            data.add(itemList.get(i).getName());
        }

        return data;
    }

    /**
     * 页面回调，从AddItemActivity、EditItemActivity返回时回调此方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //更新界面
        updateData();
    }

    /**
     * 更新界面
     */
    private void updateData() {

        adapter.clear();
        adapter.addAll(getData());

        adapter.notifyDataSetInvalidated();

    }

}
