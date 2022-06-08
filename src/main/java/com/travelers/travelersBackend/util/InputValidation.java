package com.travelers.travelersBackend.util;

import com.travelers.travelersBackend.model.Traveler;

import java.util.regex.Pattern;

public final class InputValidation {

    private static final Pattern idPattern = Pattern.compile("-?\\d+");
    private static final int ID_LENGTH = 9;

    private InputValidation() {
    }

    public static void validate(Traveler traveler) throws CustomException {
        if (traveler == null)
            throw new CustomException("The object is null");
        else if (traveler.getId() == null)
            throw new CustomException("ID cent be null");
        else if (traveler.getId().length() != ID_LENGTH)
            throw new CustomException("ID length most be 9");
        else if (!traveler.getId().matches(idPattern.pattern()))
            throw new CustomException("ID should contain only numbers");
    }

}
