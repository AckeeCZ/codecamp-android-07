package cz.codecamp.seven.ui.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cz.codecamp.seven.R;
import cz.codecamp.seven.service.BoundService;

/**
 * Activity that shows example of using bounded service
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class BoundServiceActivity extends Activity {
    public static final String TAG = BoundServiceActivity.class.getName();
    BoundService mBoundService;
    boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        final TextView txtTimestamp = (TextView) findViewById(R.id.txt_timestamp);
        Button btnGetTimestamp = (Button) findViewById(R.id.btn_get_timestamp);
        btnGetTimestamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mServiceBound) {
                    txtTimestamp.setText(mBoundService.getTimestamp());
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }
    };
}
