package com.example.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;


public class MessengerService extends Service {
    private static final String TAG = MessengerService.class.getSimpleName();
    private static final int MSG_FROM_CLIENT = 10;
    private static final int MSG_FROM_SERVICE = 11;
    private final Messenger mMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_FROM_CLIENT:
                    Log.e(TAG,"msg from client:"+msg.getData().getString("clientMsg"));
                    Messenger messenger = msg.replyTo;
                    Message replyMsg = Message.obtain(null,MSG_FROM_SERVICE);
                    Bundle data = new Bundle();
                    data.putString("serviceMsg","I got you msg,I'm service");
                    replyMsg.setData(data);
                    try {
                        messenger.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
