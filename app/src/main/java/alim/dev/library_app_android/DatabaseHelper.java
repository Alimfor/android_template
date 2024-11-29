package alim.dev.library_app_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Library.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS = "books";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_DESCRIPTION = "description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_BOOKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(createTable);

        insertBook(db, "Гарри Поттер", "Джоан Роулинг", "Мальчик-волшебник и его приключения.");
        insertBook(db, "Война и мир", "Лев Толстой", "Эпическая история о войне и любви.");
        insertBook(db, "1984", "Джордж Оруэлл", "Роман о тоталитарном будущем.");
    }

    private void insertBook(SQLiteDatabase db, String title, String author, String description) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_AUTHOR, author);
        values.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_BOOKS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id AS _id, title, author, description FROM books";
        return db.rawQuery(query, null);
    }

    public Cursor getRandomBook() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id AS _id, title, author, description FROM books ORDER BY RANDOM() LIMIT 1";
        return db.rawQuery(query, null);
    }
}
