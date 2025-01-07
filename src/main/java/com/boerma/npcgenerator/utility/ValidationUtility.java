package com.boerma.npcgenerator.utility;

import java.util.List;

public class ValidationUtility {

    private ValidationUtility() {
    }

    public static void validateNotEmpty(String name, List<?> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The " + name + " table is empty " +
                    "or not loaded. Please populate the database.");
        }
    }

    public static void validateNotBlank(String name, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " cannot be blank or null.");
        }
    }

    public static void validateIntRange(String name, int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " must be between " + min + " and " + max + ".");
        }
    }
}
