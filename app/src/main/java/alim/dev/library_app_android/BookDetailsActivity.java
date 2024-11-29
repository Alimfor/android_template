package alim.dev.library_app_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class BookDetailsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        databaseHelper = new DatabaseHelper(this);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthor = findViewById(R.id.tvAuthor);
        TextView tvDescription = findViewById(R.id.tvDescription);

        Button btnDeleteBook = findViewById(R.id.btnDeleteBook);
        Button btnEditBook = findViewById(R.id.btnEditBook);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("bookId", -1);
        boolean isRandom = intent.getBooleanExtra("random", false);

        Cursor cursor;
        if (isRandom) {
            cursor = databaseHelper.getRandomBook();
        } else {
            int bookId = intent.getIntExtra("bookId", -1);
            cursor = databaseHelper.getReadableDatabase().rawQuery(
                    "SELECT id AS _id, title, author, description FROM books WHERE id = ?",
                    new String[]{String.valueOf(bookId)}
            );
        }

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range") int bookId = cursor.getInt(cursor.getColumnIndex("_id"));

            tvTitle.setText(title);
            tvAuthor.setText("Автор: " + author);
            tvDescription.setText("Описание: " + description);

            if (isRandom) {
                btnDeleteBook.setVisibility(View.GONE);
                btnEditBook.setVisibility(View.GONE);
            } else {
                btnDeleteBook.setOnClickListener(v -> {
                    new AlertDialog.Builder(this)
                            .setTitle("Удаление книги")
                            .setMessage("Вы уверены, что хотите удалить эту книгу?")
                            .setPositiveButton("Удалить", (dialog, which) -> {
                                int result = databaseHelper.getWritableDatabase().delete(
                                        "books",
                                        "id = ?",
                                        new String[]{String.valueOf(bookId)}
                                );

                                if (result > 0) {
                                    Snackbar.make(findViewById(android.R.id.content), "Книга удалена!", Snackbar.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Snackbar.make(findViewById(android.R.id.content), "Ошибка удаления", Snackbar.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Отмена", null)
                            .show();
                });

                btnEditBook.setOnClickListener(v -> {
                    Intent editIntent = new Intent(BookDetailsActivity.this, EditBookActivity.class);
                    editIntent.putExtra("bookId", bookId);
                    startActivity(editIntent);
                });
            }
            cursor.close();
        } else {
            tvTitle.setText("Книга не найдена");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthor = findViewById(R.id.tvAuthor);
        TextView tvDescription = findViewById(R.id.tvDescription);

        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery(
                "SELECT id AS _id, title, author, description FROM books WHERE id = ?",
                new String[]{String.valueOf(bookId)}
        );

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

            tvTitle.setText(title);
            tvAuthor.setText("Автор: " + author);
            tvDescription.setText("Описание: " + description);

            cursor.close();
        } else {
            tvTitle.setText("Книга не найдена");
            tvAuthor.setText("");
            tvDescription.setText("");
        }
    }
}