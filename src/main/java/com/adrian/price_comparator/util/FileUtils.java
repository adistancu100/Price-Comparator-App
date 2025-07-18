package com.adrian.price_comparator.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
    public static LocalDate extractDateFromFilename(String filename) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(filename);

        if (matcher.find()) {
            return LocalDate.parse(matcher.group());
        } else {
            throw new IllegalArgumentException("Nu s-a găsit o dată validă în numele fișierului: " + filename);
        }
    }

}
