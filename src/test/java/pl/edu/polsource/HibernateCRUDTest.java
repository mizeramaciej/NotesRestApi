package pl.edu.polsource;

import org.junit.Test;
import pl.edu.polsource.model.Notes;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class HibernateCRUDTest extends HibernateTest {
    private static final int EXISTING_ID = 5;
    private static final int NON_EXISTING_ID = 13;
    private static final String EXISTING_TITLE = "TestTitle";
    private static final String NEW_TITLE = "NewTitle";
    private static final String NON_EXISTING_TITLE = "WrongTitle";
    private static final String CONTENT = "Some content";
    private static final String NEW_CONTENT = "Some new content";


    @Test
    public void getNoteById_success() {
        Response response = nws.getNoteById(EXISTING_ID);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals(5, note.getID());
    }

    @Test
    public void getNoteByTitle_success() {
        Response response = nws.getNoteByTitle(EXISTING_TITLE);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals(EXISTING_TITLE, note.getTitle());
    }

    @Test
    public void getNoteById_fail() {
        Response response = nws.getNoteById(NON_EXISTING_ID);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void getNoteByTitle_fail() {
        Response response = nws.getNoteByTitle(NON_EXISTING_TITLE);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void postNote_success() {
        Response response = nws.postNote(NEW_TITLE, CONTENT);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals(NEW_TITLE, note.getTitle());
    }

    @Test
    public void postNote_fail_missingTitle() {
        Response response = nws.postNote(null, CONTENT);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void postNote_fail_missingContent() {
        Response response = nws.postNote(NEW_TITLE, null);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void putNote_success_create() {
        Response response = nws.putNote(NEW_TITLE, CONTENT);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals(NEW_TITLE, note.getTitle());
    }

    @Test
    public void putNote_success_edit() {
        Response response = nws.putNote(EXISTING_TITLE, NEW_CONTENT);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Notes note = (Notes) response.getEntity();
        assertEquals(NEW_CONTENT, note.getContent());
    }

    @Test
    public void putNote_fail_missingInput() {
        Response response = nws.putNote(null, CONTENT);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteNoteById_success() {
        Response response = nws.deleteNoteById(EXISTING_ID);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteNoteByTitle_success() {
        Response response = nws.deleteNoteByTitle(EXISTING_TITLE);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteNoteById_fail() {
        Response response = nws.deleteNoteById(NON_EXISTING_ID);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }


}