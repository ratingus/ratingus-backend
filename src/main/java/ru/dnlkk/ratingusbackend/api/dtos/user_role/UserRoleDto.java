package ru.dnlkk.ratingusbackend.api.dtos.user_role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.profile.SchoolDto;

import java.sql.Timestamp;

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
    private SchoolDto school;
}
