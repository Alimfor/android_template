package com.example.peopledbapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class PersonAdapter extends BaseAdapter {
    private Context context;
    private List<Person> people;

    public PersonAdapter(Context context, List<Person> people) {
        this.context = context;
        this.people = people;
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.person_item, parent, false);
        }

        Person person = people.get(position);

        TextView name = convertView.findViewById(R.id.person_name);
        TextView age = convertView.findViewById(R.id.person_age);
        TextView birthDate = convertView.findViewById(R.id.person_birth_date);
        ImageView photo = convertView.findViewById(R.id.person_photo);

        name.setText(person.getName());
        age.setText(String.valueOf(person.getAge()));
        birthDate.setText(person.getBirthDate());

        int imageResource = context.getResources().getIdentifier(
                person.getPhotoPath(), // Например, "ivan"
                "drawable",
                context.getPackageName()
        );
        if (imageResource != 0) {
            photo.setImageResource(imageResource);
        } else {
            photo.setImageResource(R.drawable.ivan); // Путь к заглушке, если фото не найдено
        }

        Log.d("PersonAdapter", "Загружаю изображение: " + person.getPhotoPath());
        Log.d("PersonAdapter", "Идентификатор ресурса: " + imageResource);
        return convertView;
    }


}


