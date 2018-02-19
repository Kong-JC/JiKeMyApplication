package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.aidlservice.IPersonManager;
import com.example.aidlservice.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean mIsServiceConnected = false;
    private IPersonManager mIPersonManger;
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIsServiceConnected = true;
            mIPersonManger = IPersonManager.Stub.asInterface(service);
            try {
                List<Person> persons = mIPersonManger.getPersons();
                for (Person person: persons) {
                    Log.d(TAG,"Person:"+person.toString());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.aidlservice","com.example.aidlservice.AIDLService");
                bindService(intent,mConn, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.add_person_to_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsServiceConnected){
                    Person person = new Person("lilei",54,"man");
                    try {
                        mIPersonManger.addPerson(person);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbindService(mConn);
    }

}
