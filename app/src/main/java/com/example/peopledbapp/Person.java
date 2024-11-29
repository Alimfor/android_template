package com.example.peopledbapp;

public class Person {
    private String name;
    private int age;
    private String birthDate;
    private String photoPath;

    public Person(String name, int age, String birthDate, String photoPath) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
