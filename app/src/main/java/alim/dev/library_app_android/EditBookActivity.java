package alim.dev.library_app_android;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditBookActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private int bookId;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        databaseHelper = new DatabaseHelper(this);

        EditText etEditTitle = findViewById(R.id.etEditTitle);
        EditText etEditAuthor = findViewById(R.id.etEditAuthor);
        EditText etEditDescription = findViewById(R.id.etEditDescription);
        Button btnUpdateBook = findViewById(R.id.btnUpdateBook);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("bookId", -1);

        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery(
                "SELECT title, author, description FROM books WHERE id = ?",
                new String[]{String.valueOf(bookId)}
        );

        if (cursor != null && cursor.moveToFirst()) {
            etEditTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
            etEditAuthor.setText(cursor.getString(cursor.getColumnIndex("author")));
            etEditDescription.setText(cursor.getString(cursor.getColumnIndex("description")));
            cursor.close();
        }

        btnUpdateBook.setOnClickListener(v -> {
            String updatedTitle = etEditTitle.getText().toString().trim();
            String updatedAuthor = etEditAuthor.getText().toString().trim();
            String updatedDescription = etEditDescription.getText().toString().trim();

            if (updatedTitle.isEmpty() || updatedAuthor.isEmpty() || updatedDescription.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            } else {
                updateBook(updatedTitle, updatedAuthor, updatedDescription);
            }
        });
    }

    private void updateBook(String title, String author, String description) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        values.put("description", description);

        int result = databaseHelper.getWritableDatabase().update(
                "books",
                values,
                "id = ?",
                new String[]{String.valueOf(bookId)}
        );

        if (result > 0) {
            Toast.makeText(this, "Книга обновлена!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Ошибка обновления", Toast.LENGTH_SHORT).show();
        }
    }
}