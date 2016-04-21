package cz.codecamp.seven.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cz.codecamp.seven.R;

/**
 * Activity with example of picking photo from gallery
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class ShareTextActivity extends Activity {
    public static final String TAG = ShareTextActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_text);
        final EditText edit = (EditText) findViewById(R.id.edit_text);
        Button btnShare = (Button) findViewById(R.id.btn_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sharing from my app");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, edit.getText().toString());
//                startActivity(sharingIntent);
                startActivity(Intent.createChooser(sharingIntent, "Pick application"));
            }
        });
    }
}
