package com.travelers.travelers_backend.util;

import java.util.regex.Pattern;

public final class InputValidation {

    private static Pattern idPattern = Pattern.compile("-?\\d+");
    private static int ID_LENGTH = 9;

    private InputValidation() {
    }

    public static boolean isValidId(String id) {
        return id == null || id.length() != ID_LENGTH || !id.matches(idPattern.pattern());
    }

}
