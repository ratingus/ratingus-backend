package ru.dnlkk.ratingusbackend.api.dtos.school;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SchoolWasCreatedDto implements Serializable {
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
}