package ru.dnlkk.ratingusbackend.api.dtos.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProfileDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private Timestamp birthdate;
    private List<SchoolDto> schools;
}
