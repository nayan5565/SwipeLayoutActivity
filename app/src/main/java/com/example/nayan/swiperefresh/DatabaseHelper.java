package com.example.nayan.swiperefresh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nayan on 7/12/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_FRIENDS = "friends";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    private String CREATE_TABLE_CONTUCTS = "create table " + TABLE_FRIENDS + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + TABLE_FRIENDS);
        onOpen(db);

    }

    public long addContuct(int id, String name,String tableName) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_NAME, name);

        result = db.insert(tableName, null, values);

        return result;
    }

    public long updateContuct(int id, String name) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_NAME, name);
        result = db.update(TABLE_FRIENDS, values, KEY_ID + "=?", new String[]{id + ""});
        return result;
    }

    public int deleteCotact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_FRIENDS, KEY_ID + "=?", new String[]{id + ""});
        return result;
    }

    public String getContuct() {
        String result="";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_FRIENDS;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null ) {
            cursor.moveToFirst();
            do {
                String id = cursor.getString(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                result = result+id+" "+name+"\n";

            } while (cursor.moveToNext());
        }
        return result;
    }
}
