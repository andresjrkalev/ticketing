package com.example.ticketing.util;

public class IntegerUtil {
    public static Integer parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
