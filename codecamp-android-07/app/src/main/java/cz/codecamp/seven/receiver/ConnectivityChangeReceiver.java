package cz.codecamp.seven.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cz.codecamp.seven.utils.NetworkUtil;

/**
 * Broadcast receiver for connectivity change
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class ConnectivityChangeReceiver extends BroadcastReceiver {
    public static final String TAG = ConnectivityChangeReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, NetworkUtil.getConnectivityStatusString(context), Toast.LENGTH_SHORT).show();
    }
}
