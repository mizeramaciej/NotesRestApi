package pl.edu.polsource.DBAcces;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.edu.polsource.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class DBAccesor {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DBAccesor() {

    }

    private static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public ArrayList<Notes> getAllNotes() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Notes> notes = (ArrayList<Notes>) session.createQuery("FROM Notes").list();

        transaction.commit();
        session.close();
        return notes;
    }

    public Notes getNoteById(int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Notes note = session.get(Notes.class, id);

        transaction.commit();
        session.close();
        return note;
    }

    public void saveNote(Notes note) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        session.save(note);

        transaction.commit();
        session.close();
    }

    public Notes getNoteByTitle(String title) {
        Session session = getSession();
        sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Notes resultNote;

        List<Notes> note = session.createNativeQuery(
                "SELECT * FROM NOTES WHERE title = :title")
                .setParameter("title", title).addEntity(Notes.class).list();
        if (note.isEmpty()) {
            resultNote = null;
        } else {
            resultNote = note.get(0);
        }

        transaction.commit();
        session.close();
        return resultNote;
    }

    public void editNote(Notes note, String content) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        note.setContent(content);
        session.update(note);


        transaction.commit();
        session.close();
    }

    public boolean deleteNote(int id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        boolean isDeleted = false;
        Notes noteToDelete = session.load(Notes.class, id);

        if (noteToDelete != null) {
            session.delete(noteToDelete);
            isDeleted = true;
        }

        session.flush();
        transaction.commit();

        session.close();
        return isDeleted;
    }
}
