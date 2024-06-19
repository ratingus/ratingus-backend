package ru.dnlkk.ratingusbackend.api.dtos.user_role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.model.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EditUserRoleDto {
    private String name;
    private String surname;
    private String patronymic;
    private Role role;
    private ClassDto classDto;
}
