package ru.dnlkk.ratingusbackend.model;

import java.util.List;

public class Study {

    private int id;
    private String name;

    private List<User> teachers;

    private List<Lesson> lessons;

    private School school;
}
