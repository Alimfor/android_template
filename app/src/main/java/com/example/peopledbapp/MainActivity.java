package com.example.peopledbapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        PeopleDatabaseHelper dbHelper = new PeopleDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (isFirstRun()) {
            insertInitialData(db);
        }

        Cursor cursor = db.rawQuery("SELECT * FROM " + PeopleDatabaseHelper.TABLE_NAME, null);
        List<Person> people = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(PeopleDatabaseHelper.COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(PeopleDatabaseHelper.COLUMN_AGE));
                String birthDate = cursor.getString(cursor.getColumnIndexOrThrow(PeopleDatabaseHelper.COLUMN_BIRTH_DATE));
                String photo = cursor.getString(cursor.getColumnIndexOrThrow(PeopleDatabaseHelper.COLUMN_PHOTO));
                people.add(new Person(name, age, birthDate, photo));
            } while (cursor.moveToNext());
        }
        cursor.close();

        PersonAdapter adapter = new PersonAdapter(this, people);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private boolean isFirstRun() {
        return true;
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(PeopleDatabaseHelper.COLUMN_NAME, "Иван Иванов");
        values.put(PeopleDatabaseHelper.COLUMN_AGE, 25);
        values.put(PeopleDatabaseHelper.COLUMN_BIRTH_DATE, "5 июля 1999");
        values.put(PeopleDatabaseHelper.COLUMN_PHOTO, "ivan");
        db.insert(PeopleDatabaseHelper.TABLE_NAME, null, values);

        values.put(PeopleDatabaseHelper.COLUMN_NAME, "Мария Петрова");
        values.put(PeopleDatabaseHelper.COLUMN_AGE, 31);
        values.put(PeopleDatabaseHelper.COLUMN_BIRTH_DATE, "15 марта 1993");
        values.put(PeopleDatabaseHelper.COLUMN_PHOTO, "maria");
        db.insert(PeopleDatabaseHelper.TABLE_NAME, null, values);

        values.put(PeopleDatabaseHelper.COLUMN_NAME, "Степан Степанов");
        values.put(PeopleDatabaseHelper.COLUMN_AGE, 32);
        values.put(PeopleDatabaseHelper.COLUMN_BIRTH_DATE, "7 апреля 1992");
        values.put(PeopleDatabaseHelper.COLUMN_PHOTO, "stepa");
        db.insert(PeopleDatabaseHelper.TABLE_NAME, null, values);

        values.put(PeopleDatabaseHelper.COLUMN_NAME, "Петр Петров");
        values.put(PeopleDatabaseHelper.COLUMN_AGE, 25);
        values.put(PeopleDatabaseHelper.COLUMN_BIRTH_DATE, "5 мая 1999");
        values.put(PeopleDatabaseHelper.COLUMN_PHOTO, "petr");
        db.insert(PeopleDatabaseHelper.TABLE_NAME, null, values);

        values.put(PeopleDatabaseHelper.COLUMN_NAME, "Галым Галымов");
        values.put(PeopleDatabaseHelper.COLUMN_AGE, 34);
        values.put(PeopleDatabaseHelper.COLUMN_BIRTH_DATE, "1 марта 1990");
        values.put(PeopleDatabaseHelper.COLUMN_PHOTO, "galym");
        db.insert(PeopleDatabaseHelper.TABLE_NAME, null, values);
    }
}
