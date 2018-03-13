package pl.edu.polsource.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Notes.getAll", query = "SELECT n FROM Notes n")
})
public class Notes {

//    @Transient
//    private static int counter=1;

    private final Date createdDate = new Date();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private Date lastModified;

    public Notes() {
    }

    public Notes(int ID, String title, String content) {
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.lastModified = new Date();
    }

    public Notes(String title, String content) {

//        this.ID=counter++;

        this.title = title;
        this.content = content;
        this.lastModified = new Date();
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.lastModified = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.lastModified = new Date();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }
}
