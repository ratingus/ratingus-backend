package ru.dnlkk.ratingusbackend.api.dtos.user_code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import ru.dnlkk.ratingusbackend.api.dtos.user.UserWithLoginDto;
import ru.dnlkk.ratingusbackend.model.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCodeViewDto {
    private int id;
    private String code;
    private boolean activated;
    private String name;
    private String surname;
    private String patronymic;
    private String userClassName;
//    private UserWithLoginDto userWithLoginDto;
    private String schoolName;
    private Role userRole;
}
