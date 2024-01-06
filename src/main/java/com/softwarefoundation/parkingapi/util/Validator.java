package com.softwarefoundation.parkingapi.util;

import java.util.Objects;

public class Validator {


    public static boolean isNull(final Object value) {
        return value == null;
    }

    public static boolean isNotNull(final Object value) {
        return !isNull(value);
    }

}
