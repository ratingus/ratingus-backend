package ru.dnlkk.ratingusbackend.api.dtos.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ApplicationStatus {
    private ApplicationStatusType type;
    private String code;
}
