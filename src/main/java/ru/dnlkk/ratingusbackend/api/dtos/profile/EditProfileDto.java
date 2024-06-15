package ru.dnlkk.ratingusbackend.api.dtos.profile;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EditProfileDto {
    private String name;
    private String surname;
    private String patronymic;
    private Timestamp birthDate;
}
