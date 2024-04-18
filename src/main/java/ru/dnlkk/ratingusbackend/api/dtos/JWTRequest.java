package ru.dnlkk.ratingusbackend.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTRequest {
    private String login;
    private String password;
}
