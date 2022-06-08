package com.travelers.travelersBackend.util;

import java.util.function.Supplier;

public class CustomException extends RuntimeException implements Supplier<CustomException> {
    public CustomException(String message) {
            super(message);
            }


    @Override
    public CustomException get() {
            return this;
            }
}
