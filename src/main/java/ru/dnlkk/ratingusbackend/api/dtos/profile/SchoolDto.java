package ru.dnlkk.ratingusbackend.api.dtos.profile;

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
public class SchoolDto {
    private int id;
    private String name;
    private Role role;
    private ClassDto classDto;
}
