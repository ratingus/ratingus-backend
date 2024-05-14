package ru.dnlkk.ratingusbackend.api.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.model.Subject;
import ru.dnlkk.ratingusbackend.model.Timetable;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduleDto {
    private int id;

    private int lessonNumber;

    private int dayOfWeek;

    private Timetable timetable;

    private User teacher;

    private Subject subject;

    private Lesson lesson;
}
