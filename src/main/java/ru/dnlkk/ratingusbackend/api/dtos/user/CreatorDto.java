package ru.dnlkk.ratingusbackend.api.dtos.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreatorDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
}
