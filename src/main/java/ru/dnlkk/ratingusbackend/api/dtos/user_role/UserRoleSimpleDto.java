package ru.dnlkk.ratingusbackend.api.dtos.user_role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRoleSimpleDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
}
