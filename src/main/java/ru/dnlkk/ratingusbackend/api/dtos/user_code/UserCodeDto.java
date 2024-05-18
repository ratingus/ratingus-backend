package ru.dnlkk.ratingusbackend.api.dtos.user_code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.model.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCodeDto {
    private int id;
    private String code;
    private String name;
    private String surname;
    private String patronymic;
    private String userClassName;
    private Role userRole;
}
