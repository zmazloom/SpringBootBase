package com.mazloom.message;

public class ErrorMessage {

    private ErrorMessage() {
    }

    public static String errorInternalServer() {
        return Translator.toLocale("error.internal.server", null);
    }

    public static String accessDenied() {
        return Translator.toLocale("access.denied", null);
    }

    public static String errorResourceConflict() {
        return Translator.toLocale("error.resource.conflict", null);
    }

}
