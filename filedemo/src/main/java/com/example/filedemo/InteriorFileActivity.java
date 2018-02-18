package com.example.filedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 内部存储实例
 */
public class InteriorFileActivity extends AppCompatActivity {

    private TextView tvContent;
    private Button btnSave, btnRead;
    private EditText etNote;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interior_file);
        initView();
    }

    private void initView() {
        tvContent = findViewById(R.id.tvContent);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        etNote = findViewById(R.id.etNote);
        checkBox = findViewById(R.id.checkBox);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 保存 etNote 的内容到内部存储当中
                String content = etNote.getText().toString().trim();
                boolean success = saveToInternalFile("mynote.txt", content, checkBox.isChecked());
                if (success) {
                    Toast.makeText(InteriorFileActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InteriorFileActivity.this, "保存失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = readToInternalFile("mynote.txt");
                tvContent.setText(content);
            }
        });
    }

    @Nullable
    private String readToInternalFile(String fileName) {
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = openFileInput(fileName);
            br = new BufferedReader(new InputStreamReader(fis));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean saveToInternalFile(String fileName, String content, boolean isAppend) {
        if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(content)) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            if (isAppend) {
                fos = openFileOutput(fileName, MODE_APPEND);
            } else {
                fos = openFileOutput(fileName, MODE_PRIVATE);
            }
            fos.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
