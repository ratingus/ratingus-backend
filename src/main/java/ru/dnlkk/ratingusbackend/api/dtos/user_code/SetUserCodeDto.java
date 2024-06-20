package ru.dnlkk.ratingusbackend.api.dtos.user_code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SetUserCodeDto {
    private String code;
}
