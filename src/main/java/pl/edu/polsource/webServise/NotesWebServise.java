package pl.edu.polsource.webServise;

import pl.edu.polsource.DBAcces.*;
import pl.edu.polsource.model.Notes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/")
public class NotesWebServise {
    @GET
    public Response listNotes() {
        return Response.ok(getAllTitles()).build();
    }

    private String getAllTitles() {
        DBAccesor accesor = new DBAccesor();
        ArrayList<Notes> allNotes = accesor.getAllNotes();
        String list = "All titles: ";
        for (Notes note : allNotes)
            list += note.getTitle() + ", ";
        return list;
    }
}
