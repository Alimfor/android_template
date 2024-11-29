package alim.dev.library_app_android;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BooksListActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private BooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        Button btnAddNewBook = findViewById(R.id.btnAddNewBook);
        btnAddNewBook.setOnClickListener(v -> {
            Intent intent = new Intent(BooksListActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        databaseHelper = new DatabaseHelper(this);

        // Инициализация RecyclerView
        RecyclerView rvBooks = findViewById(R.id.rvBooks);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));

        // Загрузка книг и настройка адаптера
        Cursor cursor = databaseHelper.getAllBooks();
        if (cursor != null && cursor.getCount() > 0) {
            adapter = new BooksAdapter(this, cursor);
            rvBooks.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Список книг пуст!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadBooks();
    }

    private void reloadBooks() {
        Cursor cursor = databaseHelper.getAllBooks();
        if (cursor != null && cursor.getCount() > 0) {
            if (adapter == null) {
                RecyclerView rvBooks = findViewById(R.id.rvBooks);
                adapter = new BooksAdapter(this, cursor);
                rvBooks.setAdapter(adapter);
            } else {
                adapter.swapCursor(cursor);
            }
        } else {
            Toast.makeText(this, "Список книг пуст!", Toast.LENGTH_SHORT).show();
        }
    }
}