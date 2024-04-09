package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class JWTRegistrationDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private String password;
    private Timestamp birthDate;
    private boolean agreement;
}
