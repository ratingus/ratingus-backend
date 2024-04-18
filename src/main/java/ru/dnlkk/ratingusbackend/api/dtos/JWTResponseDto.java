package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponseDto {
    private String token;
}
