package ebookfrenzy.com.todo;

/**
 * Created by rahimsiahpoosh on 2017-03-06.
 */

public class Note {
    private long id;
    private String subject, notes;

    public Note(long id, String subject, String notes) {
        this.id = id;
        this.subject = subject;
        this.notes = notes;
    }

    public Note () {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return  subject;
    }
}
