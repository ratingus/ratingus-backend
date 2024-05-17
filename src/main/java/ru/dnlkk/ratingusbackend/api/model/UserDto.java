package ru.dnlkk.ratingusbackend.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dnlkk.ratingusbackend.model.*;
import ru.dnlkk.ratingusbackend.model.Class;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private int id;

    private String name;

    private String surname;

    private String patronymic;

    private String login;

    private String password;

    private Timestamp birthDate;

    private List<Announcement> announcements;

    private List<StudentLesson> studentLessons;

    private List<UserCode> usersCodes;

    private List<Class> classes;

    private UserRole userRole;
    // мени ту ван (у каждого юзера одна роль, у роли много юзеров)  /swagger-ui/index.html

    private List<Application> applications;

    private List<Subject> subjects;
}
