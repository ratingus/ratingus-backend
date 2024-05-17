package ru.dnlkk.ratingusbackend.api.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserWithLoginDto { //todo: УДАЛИТЬ
    private int id;
    private String login;
    private Timestamp birthDate;
}
