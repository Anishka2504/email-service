package com.example.emailservice.mapper.uses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeMapper {

    public static final String DATETIME_PATTERN = "dd.MM.yyyy HH:mm:ss";

    public static String localDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(DATETIME_PATTERN));
    }

    public static LocalDateTime localDateTime(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATETIME_PATTERN));
    }
}
