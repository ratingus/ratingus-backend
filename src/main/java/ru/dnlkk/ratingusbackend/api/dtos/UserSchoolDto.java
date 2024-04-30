package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.Data;
import ru.dnlkk.ratingusbackend.model.School;

import java.util.List;

@Data
public class UserSchoolDto {
    private int userId;
    private List<School> schools;
}
