package com.example.filedemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 外部存储实例
 */
public class ExternalFileActivity extends AppCompatActivity {

    private TextView tvContent;
    private Button btnSave, btnRead;
    private EditText etNote;
    private CheckBox checkBox;

    private static final String TAG = "ExternalFileActivity";

    private static final int REQ_CODE_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file);
        initView();
        checkFilePermission();
    }

    private void checkFilePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 未获取到文件的权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_CODE_FILE);
        }
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
                String content = etNote.getText().toString().trim();
                boolean success = saveToExternalFile("kong", "mynote.txt", content);
                if (success) {
                    Toast.makeText(ExternalFileActivity.this, "保存到外部文件成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ExternalFileActivity.this, "保存到外部文件失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = readToExternalFile("kong", "mynote.txt");
                tvContent.setText(content);
            }
        });
    }

    /**
     * 从外部文件中读取
     */
    @Nullable
    private String readToExternalFile(String dirName, String fileName) {
        if (!checkExternalState()) {
            return null;
        }
        String externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(externalPath + "/" + dirName + "/");
        if (!dir.exists()) {
            return null;
        }
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(new File(dir, fileName));
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
                if (fis != null) {
                    fis.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 保存到外部文件当中
     */
    private boolean saveToExternalFile(String dirName, String fileName, String content) {
        if (!checkExternalState()) {
            return false;
        }
        String externalPath = Environment.getExternalStorageDirectory().getPath();
        File dir = new File(externalPath + "/" + dirName + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(dir, fileName));
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

    private boolean checkExternalState() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQ_CODE_FILE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "获取权限成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT).show();
        }
    }
}
