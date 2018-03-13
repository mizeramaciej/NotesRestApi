package pl.edu.polsource;

import org.junit.Test;
import pl.edu.polsource.model.Notes;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class HibernateCRUDTest extends HibernateTest {

    @Test
    public void getNoteById_success() {
        Response response = nws.getNoteById(5);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals(5, note.getID());
    }

    @Test
    public void getNoteByTitle_success() {
        Response response = nws.getNoteByTitle("TestTitle");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals("TestTitle", note.getTitle());
    }

    @Test
    public void getNoteById_fail() {
        Response response = nws.getNoteById(13);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void getNoteByTitle_fail() {
        Response response = nws.getNoteByTitle("WrongTitle");
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void postNote_success() {
        Response response = nws.postNote("NewTitle", "Some interesing new content...");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals("NewTitle", note.getTitle());
    }

    @Test
    public void postNote_fail_missingTitle() {
        Response response = nws.postNote(null, "Some interesing new content...");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void postNote_fail_missingContent() {
        Response response = nws.postNote("NewTitle", null);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void putNote_success_create() {
        Response response = nws.putNote("NewTitle", "Some content fot tests...");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals("NewTitle", note.getTitle());
    }

    @Test
    public void putNote_success_edit() {
        Response response = nws.putNote("TestTitle", "Some new content");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals("Some new content", note.getContent());
    }

    @Test
    public void putNote_fail_missingInput() {
        Response response = nws.putNote(null, "Content text");
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteNoteById_success() {
        Response response = nws.deleteNoteById(5);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteNoteByTitle_success() {
        Response response = nws.deleteNoteByTitle("TestTitle");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteNoteById_fail() {
        Response response = nws.deleteNoteById(13);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }


}