package alim.dev.library_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBooksList = findViewById(R.id.btnBooksList);
        Button btnRandomBook = findViewById(R.id.btnRandomBook);

        btnBooksList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BooksListActivity.class);
                startActivity(intent);
            }
        });

        btnRandomBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
                intent.putExtra("random", true);
                startActivity(intent);
            }
        });
    }
}