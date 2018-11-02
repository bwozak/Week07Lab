/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author 672762
 */
public class Note implements Serializable {
    private int noteId;
    private Date dateCreated;
    private String content;

    public Note() {
    }

    public Note(int noteId, String dateCreated, String contents) throws ParseException {
        this.noteId = noteId;
        this.dateCreated = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(dateCreated);
        this.content = content;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContents(String contents) {
        this.content = contents;
    }
    
    
}
