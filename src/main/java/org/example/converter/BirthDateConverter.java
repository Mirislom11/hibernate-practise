package org.example.converter;

import jakarta.persistence.Converter;
import org.example.customType.BirthDay;

import jakarta.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Converter(autoApply = true)
public class BirthDateConverter implements AttributeConverter<BirthDay, Date> {
    @Override
    public Date convertToDatabaseColumn(BirthDay attribute) {
        return Optional.ofNullable(attribute)
                .map(BirthDay::getBirthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public BirthDay convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(Date::toLocalDate)
                .map(BirthDay::new)
                .orElse(null);
    }
}
