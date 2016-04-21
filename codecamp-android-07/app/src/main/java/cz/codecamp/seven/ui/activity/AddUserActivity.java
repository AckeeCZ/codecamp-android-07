package cz.codecamp.seven.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cz.codecamp.seven.R;
import cz.codecamp.seven.domain.User;
import cz.codecamp.seven.provider.DataProvider;

/**
 * Activity with adding user
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class AddUserActivity extends Activity {
    public static final String TAG = AddUserActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        final EditText editName = (EditText) findViewById(R.id.edit_name);
        final EditText editSurname = (EditText) findViewById(R.id.edit_surname);
        final EditText editAge = (EditText) findViewById(R.id.edit_age);

        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(editName.getText().toString(), editSurname.getText().toString(), Integer.parseInt(editAge.getText().toString()));
                ContentValues cv = user.getContentValues();
                getContentResolver().insert(DataProvider.USERS_URI, cv);
            }
        });
    }
}
