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
    private String organisationMail;
    private String organisationName;
    private String organisationAddress;
    private int creatorId;
}
