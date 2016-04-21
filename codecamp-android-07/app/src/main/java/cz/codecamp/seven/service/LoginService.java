package cz.codecamp.seven.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import cz.codecamp.seven.ui.activity.LoginActivity;
import cz.codecamp.seven.ui.activity.MainActivity;

/**
 * Service that login users and sends broadcast with resu.t
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class LoginService extends IntentService {
    public static final String TAG = LoginService.class.getName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     **/
    public LoginService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
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
    }
}
