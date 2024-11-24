package alim.dev.library;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.watchListView);

        List<Watch> watches = new ArrayList<>();
        watches.add(new Watch("Rolex", "$5000", "Luxury watch with gold finish", R.drawable.rolex));
        watches.add(new Watch("Casio", "$150", "Durable and reliable digital watch", R.drawable.casio));
        watches.add(new Watch("Omega", "$3000", "Classic mechanical watch", R.drawable.omega));
        watches.add(new Watch("Fossil", "$200", "Stylish watch with leather strap", R.drawable.fossil));
        watches.add(new Watch("Seiko", "$250", "High-quality Japanese watch", R.drawable.seiko));

        WatchAdapter adapter = new WatchAdapter(this, watches);
        listView.setAdapter(adapter);
    }
}