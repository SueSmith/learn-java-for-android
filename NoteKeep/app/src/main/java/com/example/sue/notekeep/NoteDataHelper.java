package com.example.sue.notekeep;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sue on 29/10/15.
 */
public class NoteDataHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "NoteData.db";
    public static final String NOTE_TABLE="Note", NOTE_ID_COL="id", NOTE_TEXT_COL="text", NOTE_TYPE_COL="type";

    public NoteDataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + NOTE_TABLE + "(" + NOTE_ID_COL + " INTEGER PRIMARY KEY, " + NOTE_TEXT_COL + " TEXT, " +
                "" + NOTE_TYPE_COL + " INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+NOTE_TABLE+"");
        onCreate(db);
    }
}
