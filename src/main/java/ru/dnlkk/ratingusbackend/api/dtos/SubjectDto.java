package ru.dnlkk.ratingusbackend.api.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.model.Schedule;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubjectDto {

    private int id;

    private String name;

    private List<User> teachers;

    private List<Schedule> schedules;

    private List<Lesson> lessons;

    private School school;
}
