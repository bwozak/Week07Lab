/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.NotesDB;
import database.NotesDBException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import models.Note;

/**
 *
 * @author 672762
 */
public class NoteService {
    
    private NotesDB noteDB;
    
    public NoteService() {
        noteDB = new NotesDB();
    }
    
    public Note get(int noteId) throws NotesDBException, ParseException {
        return noteDB.getNote(noteId);
    }
    
    public List<Note> getAll() throws NotesDBException, ParseException {
        return noteDB.getAll();
    }
    
    public int update(int noteId, String content) throws NotesDBException, ParseException {
        Date date = (Date) Calendar.getInstance().getTime();
        DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        String notedate = dateformat.format(date);
        Note note = new Note(noteId, notedate, content);
        return noteDB.update(note);
    }
    
    public int delete(int noteId) throws NotesDBException, ParseException {
        Note deleteNote = noteDB.getNote(noteId);
        return noteDB.delete(deleteNote);
    }
    
    public int insert(String content) throws Exception {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        String notedate = dateformat.format(date);
        Note note = new Note(0, notedate, content);
        System.out.println("here");
        return noteDB.insert(note);
        
    }
}
