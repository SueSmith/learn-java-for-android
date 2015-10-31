package com.example.sue.notekeep;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sue on 28/10/15.
 */
public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, ArrayList<Note> noteArrayList){
        super(context, 0, noteArrayList);

    }

    public View getView(int position, View convertView, ViewGroup parent){
        //get the note
        Note currNote = getItem(position);
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        //get text view
        TextView noteText = (TextView)convertView.findViewById(R.id.note);
        //set the note text
        noteText.setText(currNote.getText());
        //set style for type
        int noteType = currNote.getNoteType();
        switch(noteType){
            case Note.FITNESS_NOTE:
                noteText.setTextColor(Color.GREEN);
                break;
            case Note.HOME_NOTE:
                noteText.setTextColor(Color.BLUE);
                break;
            case Note.WORK_NOTE:
                noteText.setTextColor(Color.MAGENTA);
                break;
            default:
                noteText.setTextColor(Color.GRAY);
                break;
        }
        return convertView;
    }
}
