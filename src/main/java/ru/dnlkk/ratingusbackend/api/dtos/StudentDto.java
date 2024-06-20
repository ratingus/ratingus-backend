package ru.dnlkk.ratingusbackend.api.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentDto {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private List<List<MarkDto>> marks = new ArrayList<>();
}
