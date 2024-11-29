package alim.dev.library_app_android;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        databaseHelper = new DatabaseHelper(this);

        EditText etTitle = findViewById(R.id.etTitle);
        EditText etAuthor = findViewById(R.id.etAuthor);
        EditText etDescription = findViewById(R.id.etDescription);
        Button btnAddBook = findViewById(R.id.btnAddBook);

        btnAddBook.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            } else {
                addBookToDatabase(title, author, description);
            }
        });
    }

    private void addBookToDatabase(String title, String author, String description) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        values.put("description", description);

        long result = databaseHelper.getWritableDatabase().insert("books", null, values);
        if (result != -1) {
            Toast.makeText(this, "Книга добавлена!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Ошибка добавления книги", Toast.LENGTH_SHORT).show();
        }
    }
}