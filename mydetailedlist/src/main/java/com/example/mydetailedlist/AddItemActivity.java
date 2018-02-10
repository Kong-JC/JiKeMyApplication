package com.example.mydetailedlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mydetailedlist.model.Item;
import com.example.mydetailedlist.utils.SPHelper;

import java.util.Date;

public class AddItemActivity extends AppCompatActivity {

    private Button buttonSubmit;

    private EditText editItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // 绑定输入框和按钮

        editItemName = (EditText) findViewById(R.id.edit_add_item);

        buttonSubmit = (Button) findViewById(R.id.btn_submit);

        // 绑定监听器

        buttonSubmit.setOnClickListener(new OnSubmitListener(editItemName));

    }

    private class OnSubmitListener implements View.OnClickListener {

        private EditText editItemName;

        public OnSubmitListener(EditText editItemName) {
            this.editItemName = editItemName;
        }

        @Override
        public void onClick(View view) {


            // 获取待办事项名字
            String itemName = editItemName.getText().toString();

            //保存

            Item item = new Item();
            item.setId(new Date().getTime());
            item.setName(itemName);

            SPHelper.addNode(AddItemActivity.this, item);

            AddItemActivity.this.finish();
        }

    }


    /**
     * 启动Activity
     *
     * @param context
     */
    public static void startActivity(Context context) {

        Intent intent = new Intent(context, AddItemActivity.class);
        ((Activity) context).startActivityForResult(intent, 1);
    }

}
