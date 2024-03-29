package ru.dnlkk.ratingusbackend.model;

import java.util.List;

public class School {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;

    private List<Timetable> timetables;

    private List<UserRole> userRoles;

    private List<Class> classes;
    private List<UserCode> userCodes;
    private List<Study> studies;
}
