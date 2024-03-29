package ru.dnlkk.ratingusbackend.model;

import java.sql.Timestamp;

public class Timetable {

    private int id;

    private int lessonNumber;

    private Timestamp startTime;
    private Timestamp endTime;

    private School school;

}
