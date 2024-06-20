package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private Timestamp birthDate;
    private List<Integer> announcementsId;
    private List<Integer> studentsLessonsId;
    private List<Integer> usersCodesId; //todo: нужно?
    private List<Integer> classesId;
    private List<Integer> usersRolesId;
    private List<Integer> subjectsId;
}
