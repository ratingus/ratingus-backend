package ru.dnlkk.ratingusbackend.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.dnlkk.ratingusbackend.model.enums.Role;

@Converter(autoApply = true)
public class RoleEnumConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.name();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return Role.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid value for Role enum: " + dbData, e);
        }
    }
}
