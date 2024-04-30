package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.Data;
import ru.dnlkk.ratingusbackend.model.Lesson;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DayLessonDto {

    private int dayNumber;
    private Timestamp day;
    private List<Lesson> dayLessons;
    private String mark;
    private String note;
}
