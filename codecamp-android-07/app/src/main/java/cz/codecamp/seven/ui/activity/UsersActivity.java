package cz.codecamp.seven.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import cz.codecamp.seven.R;
import cz.codecamp.seven.provider.DataProvider;
import cz.codecamp.seven.ui.adapter.UsersAdapter;

/**
 * Activity with list of users
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class UsersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final String TAG = UsersActivity.class.getName();
    private static final int LOADER_USERS = 1;
    private UsersAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initRecyclerView();
        getSupportLoaderManager().initLoader(LOADER_USERS, null, this);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new UsersAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, DataProvider.USERS_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished: ");
        if (data != null) {
            mAdapter.setData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.setData(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(this, AddUserActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
