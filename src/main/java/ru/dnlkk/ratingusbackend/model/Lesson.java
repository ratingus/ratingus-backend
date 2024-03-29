package ru.dnlkk.ratingusbackend.model;

import java.sql.Timestamp;
import java.util.List;

public class Lesson {

    private int id;

    private int lessonNumber;

    private String homework;

    private String theme;

    private Timestamp date;

    private Study study;

    private List<StudentLesson> studentLessons;
}
