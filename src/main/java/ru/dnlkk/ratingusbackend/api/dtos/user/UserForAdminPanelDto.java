package ru.dnlkk.ratingusbackend.api.dtos.user;

import lombok.*;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserForAdminPanelDto {
    private int id;
    private String login;
    private Timestamp birthDate;
    private String name;
    private String surname;
    private String patronymic;
    private String schoolName;
    private Role role;
}