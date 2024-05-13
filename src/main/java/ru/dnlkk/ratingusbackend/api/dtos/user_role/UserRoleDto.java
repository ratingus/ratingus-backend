package ru.dnlkk.ratingusbackend.api.dtos.user_role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.model.School;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserCode;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRoleDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private Timestamp birthdate;
    private String schoolName;
    private Role userRole;
}
