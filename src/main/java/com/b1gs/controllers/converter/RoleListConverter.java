package com.b1gs.controllers.converter;

import com.b1gs.controllers.service.security.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RoleListConverter implements AttributeConverter<List<Role>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Role> roles) {
        return roles.stream()
                .map(Role::name)
                .collect(Collectors.joining(DELIMITER));

    }

    @Override
    public List<Role> convertToEntityAttribute(String rolesAsString) {
        return Arrays.stream(rolesAsString.split(DELIMITER))
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }
}
