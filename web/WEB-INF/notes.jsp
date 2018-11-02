<%-- 
    Document   : notes.jsp
    Created on : Oct 25, 2018, 1:47:22 PM
    Author     : 672762
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes</h1>
        
        <table>
            <thead>
                <th>note ID</th>
                <th>Date Created</th>
                <th>Contents</th>
                <th></th>
                <th></th>
            </thead>
            <tbody>
                <c:forEach var="note" items="${notes}">
                    <tr>
                        <th>${note.noteId}</th>
                        <th>${note.dateCreated}</th>
                        <th>${note.content}</th>
                        <th>
                            <form method="post" action="notes?action=delete">
                                <input type="submit" value="Delete">
                                <input type="hidden" name="selectedNoteToDelete" value="${note.noteId}">
                            </form>
                        </th>
                        <th>
                            <form method="post" action="notes">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="view">
                                <input type="hidden" name="selectedNoteToView" value="${note.noteId}">
                            </form>
                        </th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <c:if test="${selectedNoteToEdit == null}">
            <form method="post" action="notes?action=add">
                <textarea cols="40" rows="15" name="content"></textarea>
                <input type="submit" value="Add"/>
            </form>
        </c:if>
        
        <c:if test="${selectedNoteToEdit != null}">
            <form method="post" action="notes?action=edit">
                <input type="hidden" name="hiddenNote" value="${selectedNoteToEdit.noteId}">
                <textarea cols="40" rows="15" name="content">${selectedNoteToEdit.content}</textarea>
                <input type="submit" value="Add">
            </form>
        </c:if>
        
        ${message}
    </body>
</html>
