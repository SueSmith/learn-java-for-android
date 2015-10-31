package com.example.sue.notekeep;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by sue on 26/10/15.
 */
public class Note implements Comparable<Note> {

    //note type
    private int noteType;

    //constants for types
    public static final int WORK_NOTE=1, HOME_NOTE=2, FITNESS_NOTE=3, GENERAL_NOTE=0;

    //note text
    private String note;

    /*
    note constructor
     */
    public Note(String noteText){
        note=noteText;
        noteType=GENERAL_NOTE;
    }

    /*
    method to return text for this note
     */
    public String getText(){
        return note;
    }

    /*
    method to return note type
     */
    public int getNoteType(){
        return noteType;
    }

    /*
    method to set note type
     */
    public void setNoteType(int newType){
        Set<Integer> types = new HashSet<Integer>();
        types.add(GENERAL_NOTE); types.add(WORK_NOTE); types.add(FITNESS_NOTE); types.add(HOME_NOTE);
        if(!types.contains(newType)) throw new IllegalArgumentException();
        noteType=newType;
    }

    /*
    method to return string for note inclusion in list
     */
    public String toString(){
        return note;
    }

    /*
    method to compare this note with another
     */
    public int compareTo(Note otherNote){
        return this.toString().compareTo(otherNote.toString());
    }
}
