package ru.dnlkk.ratingusbackend.api.dtos.application;

import lombok.*;
import ru.dnlkk.ratingusbackend.api.dtos.user_code.UserCodeWithClassDto;

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
    private UserCodeWithClassDto code;
    private Boolean isActivated;
}
