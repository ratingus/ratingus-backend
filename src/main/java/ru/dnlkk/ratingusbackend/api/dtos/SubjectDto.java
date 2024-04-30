package ru.dnlkk.ratingusbackend.api.dtos;

import jakarta.persistence.*;
import lombok.Data;
import ru.dnlkk.ratingusbackend.model.Lesson;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;

import java.util.List;

@Data
public class SubjectDto {

    private int id;

    private String name;

    private List<User> teachers;

    private List<Lesson> lessons;

    private School school;
}
