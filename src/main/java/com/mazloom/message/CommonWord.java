package com.mazloom.message;

public class CommonWord {

    private CommonWord() {
    }

    public static String user() {
        return Translator.toLocale("word.user", null);
    }

}
