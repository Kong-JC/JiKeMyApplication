package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kong on 2018/2/19.
 */

public class AIDLService extends Service {

    private static final String TAG = AIDLService.class.getSimpleName();
    private List<Person> mPersons = new ArrayList<>();

    private IPersonManager.Stub mManager = new IPersonManager.Stub() {
        @Override
        public List<Person> getPersons() throws RemoteException {
            return mPersons;
        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            Log.d(TAG,"service add person:"+person.toString());
            mPersons.add(person);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mManager.addPerson(new Person("zhangsan",23,"man"));
            mManager.addPerson(new Person("lisi",43,"man"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
