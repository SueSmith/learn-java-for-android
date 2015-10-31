package com.example.sue.notekeep;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends ListActivity {

    private static final String TAG = "MainActivity";

    private NoteManager noteBoss;
    private NoteAdapter noteAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteBoss = new NoteManager(this);
        int numNotes = noteBoss.getNumNotes();

        Log.v(TAG, "number of notes: "+numNotes);

        //map note collection to list view for display
        noteAdapt = new NoteAdapter(this, noteBoss.getNotes());
        setListAdapter(noteAdapt);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.add){
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Add Note");
            alert.setMessage("Enter note text:");
            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            alert.setView(input);
            alert.setPositiveButton("ADD",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String noteText = input.getText().toString();
                            if (noteText != null && noteText.length() > 0) addNote(noteText);
                        }
                    });
            alert.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void addNote(String newNote){
        boolean noteAdded = noteBoss.addNewNote(newNote);
        noteAdapt.notifyDataSetChanged();
        if(noteAdded)
            Toast.makeText(getApplicationContext(),"Added!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Oops! Not added.", Toast.LENGTH_SHORT).show();

    }
}
