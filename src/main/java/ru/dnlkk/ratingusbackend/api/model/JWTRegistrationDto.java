package ru.dnlkk.ratingusbackend.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
