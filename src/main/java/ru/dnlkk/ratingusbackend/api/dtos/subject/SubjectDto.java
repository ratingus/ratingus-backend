package ru.dnlkk.ratingusbackend.api.dtos.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class SubjectDto {
    private int id;
    private String name;
}