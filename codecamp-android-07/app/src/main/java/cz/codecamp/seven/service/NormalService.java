package cz.codecamp.seven.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cz.codecamp.seven.ui.activity.LoginActivity;

/**
 * Service that shows that normal service run in main thread
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class NormalService extends Service {
    public static final String TAG = NormalService.class.getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra(LoginActivity.NAME_KEY);
        String password= intent.getStringExtra(LoginActivity.NAME_KEY);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent broadcast = new Intent(LoginActivity.ACTION);
        if(name.equals("test") && password.equals("test")) {
            broadcast.putExtra(LoginActivity.SUCCESS_KEY, true);
        }else {
            broadcast.putExtra(LoginActivity.SUCCESS_KEY, false);
        }
        sendBroadcast(broadcast);
        return super.onStartCommand(intent, flags, startId);
    }
}
