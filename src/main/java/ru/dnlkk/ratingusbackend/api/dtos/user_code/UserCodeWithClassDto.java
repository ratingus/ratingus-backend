package ru.dnlkk.ratingusbackend.api.dtos.user_code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dnlkk.ratingusbackend.api.dtos.clazz.ClassDto;
import ru.dnlkk.ratingusbackend.model.Class;
import ru.dnlkk.ratingusbackend.model.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCodeWithClassDto {
    private int id;
    private String code;
    private String name;
    private String surname;
    private String patronymic;
    private ClassDto classDto;
    private Role role;
}
