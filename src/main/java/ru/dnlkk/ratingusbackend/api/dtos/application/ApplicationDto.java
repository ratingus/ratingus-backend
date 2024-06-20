package ru.dnlkk.ratingusbackend.api.dtos.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicationDto {
    private int id;
    private String email;
    private String name;
    private String address;
    private String phone;
    private int creatorId;
}
