package alim.dev.library;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        TextView textView = findViewById(R.id.randomBookTextView);

        findViewById(R.id.randomizeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] books = BooksRepository.getBooks();
                int randomIndex = new Random().nextInt(books.length);
                textView.setText(books[randomIndex]);
            }
        });
    }
}
