package ru.dnlkk.ratingusbackend.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponseDto {
    private String token;
}