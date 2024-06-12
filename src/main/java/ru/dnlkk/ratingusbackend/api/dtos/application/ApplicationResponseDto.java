package ru.dnlkk.ratingusbackend.api.dtos.application;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicationResponseDto {
    private int id;
    private String email;
    private String name;
    private String address;
    private String phone;
    private int creatorId;
    private String status;
    private String code;
}
