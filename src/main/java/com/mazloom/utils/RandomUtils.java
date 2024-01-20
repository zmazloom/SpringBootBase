package com.mazloom.utils;

import java.security.SecureRandom;

public class RandomUtils {

    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int length = 6;

    public static String generateRandomString() {
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(secureRandom.nextInt(characters.length())));
        }

        return randomString.toString();
    }

}
