package com.boerma.npcgenerator.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }

        switch (gender){
            case MALE:
                return 1;
            case FEMALE:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown Gender: " + gender);
        }
    }

    @Override
    public Gender convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            throw new IllegalArgumentException("Gender ID cannot be null.");
        }

        switch (dbData) {
            case 1:
                return Gender.MALE;
            case 2:
                return Gender.FEMALE;
            default:
                throw new IllegalArgumentException("Unknown Gender ID: " + dbData);
        }
    }
}
