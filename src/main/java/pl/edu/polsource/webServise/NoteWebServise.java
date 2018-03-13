package pl.edu.polsource.webServise;

import pl.edu.polsource.DBAcces.DBAccesor;
import pl.edu.polsource.model.Notes;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/note")
public class NoteWebServise {

    private static final String NOTE_NOT_FOUND = "Note not found.";
    private static final String ERROR_DELETING = "Error while tring deltet note";
    private static final String INPUT_MISSING = "Neither title nor content can be empty.";
    private static final Logger LOGGER = Logger.getLogger(NoteWebServise.class.getName());
    private final DBAccesor accesor = new DBAccesor();


    @GET
    @Path("{id: \\d+}") //digits only
    public Response getNoteById(@PathParam("id") int id) {
        Notes note = accesor.getNoteById(id);
        return checkNote(note);
    }

    @GET
    @Path("{title: \\w+}") //word
    public Response getNoteByTitle(@PathParam("title") String title) {
        Notes note = accesor.getNoteByTitle(title);
        return checkNote(note);
    }

    private Response checkNote(Notes note) {
        if (note == null) {
            logMessage(NOTE_NOT_FOUND);
            return Response.status(Response.Status.NOT_FOUND).entity(NOTE_NOT_FOUND).build();
        }
        logNote("GET", note);

        return Response.ok().entity(note).build();
    }

    @POST //to create
    public Response postNote(
            @FormParam("title") String title,
            @FormParam("content") String content) {
        if (title == null || content == null) {
            logMessage(INPUT_MISSING);
            return Response.status(Response.Status.BAD_REQUEST).entity(INPUT_MISSING).build();
        }

        Notes note = new Notes(title, content);
        accesor.saveNote(note);
        logNote("CREATED", note);

        return Response.ok().entity(note).build();
    }

    @PUT //to create or update
    public Response putNote(
            @FormParam("title") String title,
            @FormParam("content") String content) {
        if (title == null || content == null) {
            logMessage(INPUT_MISSING);
            return Response.status(Response.Status.BAD_REQUEST).entity(INPUT_MISSING).build();
        }

        Notes note = accesor.getNoteByTitle(title);
        if (note == null) {
            return postNote(title, content);
        } else {
            accesor.editNote(note, content);
            logNote("UPDATE", note);
        }

        return Response.ok().entity(note).build();
    }

    @DELETE
    @Path("{title: \\w+}")
    public Response deleteNoteByTitle(@PathParam("title") String title) {
        Notes note = accesor.getNoteByTitle(title);
        return deleteNote(note);
    }

    @DELETE
    @Path("{id: \\d+}")
    public Response deleteNoteById(@PathParam("id") int id) {
        Notes note = accesor.getNoteById(id);
        return deleteNote(note);
    }

    private Response deleteNote(Notes note) {
        if (note == null) {
            logMessage(NOTE_NOT_FOUND);
            return Response.status(Response.Status.NOT_FOUND).entity(NOTE_NOT_FOUND).build();
        }

        if (accesor.deleteNote(note.getID())) {
            logMessage(String.format("Note %s deleted successfully", note.getTitle()));
            return Response.ok().entity("Note deleted successfully.").build();
        }

        logMessage(ERROR_DELETING);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ERROR_DELETING).build();
    }

    private void logNote(String operation, Notes note) {
        LOGGER.log(Level.INFO, String.format(
                "\n%s NOTE : ID=%d , title=%s , content=%s\ncreatedDate=%s , lastModified=%s ",
                operation, note.getID(), note.getTitle(), note.getContent(),
                note.getCreatedDate(), note.getLastModified()));
    }

    private void logMessage(String message) {
        LOGGER.log(Level.INFO, message);
    }
}
