package com.adrian.price_comparator.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FileUtils {
    public static LocalDate extractDateFromFilename(String filename) {
        try {
            int underscoreIndex = filename.indexOf('_');
            int dotIndex = filename.lastIndexOf('.');
            if (underscoreIndex > 0 && dotIndex > underscoreIndex) {
                String datePart = filename.substring(underscoreIndex + 1, dotIndex);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(datePart, formatter);
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
