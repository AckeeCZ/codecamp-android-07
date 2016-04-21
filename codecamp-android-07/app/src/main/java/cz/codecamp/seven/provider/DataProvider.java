package cz.codecamp.seven.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cz.codecamp.seven.BuildConfig;
import cz.codecamp.seven.db.DatabaseHelper;
import cz.codecamp.seven.domain.User;

/**
 * Provider for list of users
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class DataProvider extends ContentProvider {
    public static final String TAG = DataProvider.class.getName();

    public static final Uri USERS_URI = Uri.parse("content://" + BuildConfig.APPLICATION_ID + "/users");
    private DatabaseHelper mOpenHelper;

    private static final UriMatcher sUriMatcher;

    private static final int ALL_USERS = 0;


    private static final int SINGLE_USER = 1;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(BuildConfig.APPLICATION_ID, "users", ALL_USERS);
        sUriMatcher.addURI(BuildConfig.APPLICATION_ID, "users/#", SINGLE_USER);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        switch (sUriMatcher.match(uri)) {
            case ALL_USERS:
                c = mOpenHelper.getWritableDatabase().query(User.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SINGLE_USER:
                long id = Long.parseLong(uri.getLastPathSegment());
                c = mOpenHelper.getUser(id);
                break;
        }
        if (c != null) {
            c.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long id = mOpenHelper.insertUser(values);
        notifyUri(uri);
        return Uri.withAppendedPath(USERS_URI, String.valueOf(id));
    }

    public void notifyUri(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        long id = Long.parseLong(uri.getLastPathSegment());
        int count = mOpenHelper.deleteUser(id);
        notifyUri(uri);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        switch (sUriMatcher.match(uri)) {
            case ALL_USERS:
                count = mOpenHelper.updateUsersGenerally(values, selection, selectionArgs);
                break;
            case SINGLE_USER:
                long id = Long.parseLong(uri.getLastPathSegment());
                count = mOpenHelper.updateUser(id, values);
                break;

        }
        notifyUri(uri);
        return count;

    }
}
