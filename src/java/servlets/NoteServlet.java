/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.NotesDBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;
import services.NoteService;

/**
 *
 * @author 672762
 */
public class NoteServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NoteService noteservice = new NoteService();
        String action = request.getParameter("action");
        
        if(action != null && action.equals("view")) {
            int selectedNoteToView = Integer.parseInt(request.getParameter("selectedNoteToView"));
            
            try {
                Note noteToView = noteservice.get(selectedNoteToView);
                request.setAttribute("selectedNoteToEdit", noteToView);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            updateTable(request);
           }
         getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request,response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String content = request.getParameter("content");
        
        NoteService noteservice = new NoteService();
        if(action == null) {
            return;
        }
       
        try {
            switch(action) {
                case "add":
                    if(!(content == null || content.equals(""))) {
                        noteservice.insert(content);
                    } else {
                        request.setAttribute("message", "content cannot be empty");
                    }
                    updateTable(request);
                    break;
                    
                case "delete":
                    int selectedNoteToDelete = Integer.parseInt(request.getParameter("selectedNoteToDelete"));
                    noteservice.delete(selectedNoteToDelete);
                    updateTable(request);
                    break;
                case "edit":
                    if(!(content == null || content.equals(""))) {
                        noteservice.update(Integer.parseInt(request.getParameter("hiddenNote")), content);
                    } else {
                        request.setAttribute("message", "Content cannot be empty");
                    }
                    updateTable(request);
                    break;  
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    private void updateTable(HttpServletRequest request) {
        
        try {
            NoteService noteservice = new NoteService();
            List<Note> noteList = noteservice.getAll();
            request.setAttribute("notes", noteList);
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
