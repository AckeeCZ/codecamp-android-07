package cz.codecamp.seven.ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.codecamp.seven.BuildConfig;
import cz.codecamp.seven.R;
import cz.codecamp.seven.service.LoginService;
import cz.codecamp.seven.service.NormalService;

/**
 * Activity that simulates login with remote login via intent service
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class LoginActivity extends Activity {
    public static final String TAG = LoginActivity.class.getName();

    public static final String ACTION = BuildConfig.APPLICATION_ID + ".LOGIN_RESULT";
    public static final String NAME_KEY = "name";
    public static final String SUCCESS_KEY = "success";
    public static final String PASSWORD_KEY = "password";
    private BroadcastReceiver receiver;

    @Bind(R.id.progress)
    View progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Button btnLogin = ButterKnife.findById(this, R.id.btn_login);
        final EditText editName = ButterKnife.findById(this, R.id.edit_login);
        final EditText editPassword = ButterKnife.findById(this, R.id.edit_password);
        receiver = new ResultReceiver();
        progress.setVisibility(View.VISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLogin(editName.getText().toString(), editPassword.getText().toString());
            }
        });
    }

    private void startLogin(String name, String password) {
        Intent i = new Intent(this, LoginService.class);
        i.putExtra(NAME_KEY, name);
        i.putExtra(PASSWORD_KEY, password);
        startService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    class ResultReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            progress.setVisibility(View.GONE);
            boolean success = intent.getBooleanExtra(SUCCESS_KEY, false);
            Snackbar.make(findViewById(R.id.content), success ? "Login Successful" : "Login failed", Snackbar.LENGTH_SHORT).show();
        }
    }
}
