package ru.dnlkk.ratingusbackend.api.dtos.clazz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClassDto {
    private int id;
    private String name;
}
