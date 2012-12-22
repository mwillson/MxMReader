package mxmreader;

import java.util.ArrayList;

/**
 *
 * @author mwillson
 */
public class Chord {
    
    private ArrayList<Note> notes;
    
    public Chord (ArrayList n) {
        notes = n;
    }
    
    public ArrayList<Note> getNotes(){
        return notes;
    }
    
}
