package com.example.sue.notekeep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by sue on 23/10/15.
 */
public class NoteManager {

    //note array list
    private ArrayList<Note> notes;

    private NoteDataHelper noteHelper;
    private SQLiteDatabase db;

    /*
    constructor method for note manager
     */
    public NoteManager(Context context){

        noteHelper = new NoteDataHelper(context);
        db = noteHelper.getWritableDatabase();

        notes=new ArrayList<Note>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+NoteDataHelper.NOTE_TABLE+"", null);
        if(cursor.moveToFirst()){
            do {
                Note newNote = new Note(cursor.getString(cursor.getColumnIndex(NoteDataHelper.NOTE_TEXT_COL)));
                try {
                    newNote.setNoteType(cursor.getInt(cursor.getColumnIndex(NoteDataHelper.NOTE_TYPE_COL)));
                }
                catch(IllegalArgumentException iae){
                    newNote.setNoteType(Note.GENERAL_NOTE);
                }
                notes.add(newNote);
            } while(cursor.moveToNext());
        }

        Collections.sort(notes);
    }

    /*
    method to return note array list
     */
    public ArrayList<Note> getNotes(){
        return notes;
    }

    /*
    method to update note array list
     */
    public void setNotes(ArrayList<Note> newNotes){
        notes=newNotes;
    }

    /*
    method to return the number of notes
     */
    public int getNumNotes(){
        return notes.size();
    }

    /*
    method to add new note
     */
    public boolean addNewNote(String newNoteText){
        ContentValues values = new ContentValues();
        values.put(NoteDataHelper.NOTE_TEXT_COL, newNoteText);
        values.put(NoteDataHelper.NOTE_TYPE_COL, "0");
        boolean added = db.insert(NoteDataHelper.NOTE_TABLE, null, values)>=0;
        notes.add(new Note(newNoteText));
        Collections.sort(notes);
        return added;
    }


}
