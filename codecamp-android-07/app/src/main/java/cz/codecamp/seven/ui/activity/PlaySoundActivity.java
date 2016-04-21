package cz.codecamp.seven.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.codecamp.seven.R;
import cz.codecamp.seven.service.PlaySoundService;

/**
 * Activity
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class PlaySoundActivity extends Activity {
    public static final String TAG = PlaySoundActivity.class.getName();
    private static final int REQUEST_PICK_AUDIO_FILE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sound);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start_service)
    public void onStartServiceClicked() {
        Intent intentPick = new Intent();
        intentPick.setType("audio/*");
        intentPick.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentPick, REQUEST_PICK_AUDIO_FILE);

    }

    @OnClick(R.id.btn_stop_service)
    public void onStopServiceClicked() {
        stopService();
    }

    private void stopService() {
        stopService(new Intent(this, PlaySoundService.class));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_PICK_AUDIO_FILE && resultCode== Activity.RESULT_OK) {
            Intent i = new Intent(this, PlaySoundService.class);
            i.setData(data.getData());
            startService(i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }
}
