package cz.codecamp.seven.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cz.codecamp.seven.domain.User;


/**
 * Database helper for managing our access operations
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelper.class.getName();
    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertUser(ContentValues contentValues) {
        return getWritableDatabase().insert(User.TABLE_NAME, null, contentValues);
    }

    public int updateUser(long userId, ContentValues contentValues) {
        return getWritableDatabase().update(User.TABLE_NAME, contentValues, User.COL_ID + " = ?", new String[]{String.valueOf(userId)});
    }


    public int deleteUser(long userId) {
        return getWritableDatabase().delete(User.TABLE_NAME, User.COL_ID + " = ?", new String[]{String.valueOf(userId)});
    }

    public int updateUsersGenerally(ContentValues values, String selection, String[] selectionArgs) {
        return getWritableDatabase().update(User.TABLE_NAME, values, selection, selectionArgs);
    }

    public Cursor getUser(long id) {
        return getWritableDatabase().query(User.TABLE_NAME, null, User.COL_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
    }


}
