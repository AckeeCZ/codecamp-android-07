package cz.codecamp.seven.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.codecamp.seven.R;
import cz.codecamp.seven.service.BoundService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_bounded, R.id.btn_intent, R.id.btn_login, R.id.btn_play_sound, R.id.btn_users})
    void onButtonCLicked(View view) {
        Class<?> activityClass = null;
        switch (view.getId()) {
            case R.id.btn_bounded:
                activityClass = BoundServiceActivity.class;
                break;
            case R.id.btn_intent:
                activityClass = ShareTextActivity.class;
                break;
            case R.id.btn_login:
                activityClass = LoginActivity.class;
                break;
            case R.id.btn_play_sound:
                activityClass = PlaySoundActivity.class;
                break;
            case R.id.btn_users:
                activityClass = UsersActivity.class;
                break;
        }
        if(activityClass != null) {
            startActivity(new Intent(this, activityClass));
        }
    }
}
