package ebookfrenzy.com.todo;

/**
 * Created by rahimsiahpoosh on 2017-03-06.
 */



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class SQLhelper extends SQLiteOpenHelper{

    public static final String TABLE_NOTE = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_NOTE = "note";
    private SQLiteDatabase database;


    public SQLhelper(Context context) {
        super(context, "note.db", null, 6);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String DATABASE_CREATE = "create table " + TABLE_NOTE + " (" + COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_SUBJECT + " text not null, " + COLUMN_NOTE + " text);";
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public void open() throws SQLException {

        database = getWritableDatabase();
    }

    public Note addNote(Note note) {
        open();
        ContentValues values = new ContentValues();
        values.put(SQLhelper.COLUMN_SUBJECT, note.getSubject());
        values.put(SQLhelper.COLUMN_NOTE, note.getNotes());
        long insertId = database.insert(TABLE_NOTE, null,
                values);
        close();
        return note;
    }

    public void deleteNote(Note note) {
        open();
        long id = note.getId();
        database.delete(TABLE_NOTE, SQLhelper.COLUMN_ID + " = " + Long.toString(id), null);
        close();
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        String[] allColumns = {COLUMN_ID, COLUMN_SUBJECT, COLUMN_NOTE};
        Cursor cursor = database.query(SQLhelper.TABLE_NOTE,
                allColumns, null, null, null, null, null);

        cursor.moveToLast();
        while (!cursor.isBeforeFirst()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToPrevious();
        }
        cursor.close();
        return notes;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getLong(0));

        String subject=cursor.getString(1);
        note.setSubject(subject);

        String not =cursor.getString(2);
        note.setNotes(not);
        return note;
    }

    public Note getNote(int position) {
        open();
        String[] allColumns = {COLUMN_ID, COLUMN_SUBJECT, COLUMN_NOTE};
        Cursor cursor = database.query(SQLhelper.TABLE_NOTE,
                allColumns, null, null, null, null, COLUMN_ID + " DESC");

        cursor.moveToPosition(position);
        Note note = cursorToNote(cursor);

        cursor.close();
        close();
        return note;

    }

    public void updateData (Note note) {
        open();
        ContentValues values = new ContentValues();
        values.put(SQLhelper.COLUMN_ID, note.getId());
        values.put(SQLhelper.COLUMN_SUBJECT, note.getSubject());
        values.put(SQLhelper.COLUMN_NOTE, note.getNotes());
        database.update(TABLE_NOTE, values, "_id="+note.getId(), null);
        close();
    }
}
