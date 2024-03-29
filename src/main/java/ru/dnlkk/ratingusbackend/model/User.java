package ru.dnlkk.ratingusbackend.model;

import java.sql.Timestamp;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private String password;
    private Timestamp birthDate;

    private List<Announcement> announcements;
    private List<StudentLesson> studentsLessons;
    private List<UserCode> usersCodes;
    private List<Class> classes;
    private List<UserRole> usersRoles;
    private List<Study> studies;
}
