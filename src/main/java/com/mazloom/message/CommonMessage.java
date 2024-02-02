package com.mazloom.message;

public class CommonMessage {

    private CommonMessage() {
    }

    public static String ok() {
        return Translator.toLocale("ok", null);
    }

    public static String paramRequired(String paramName) {
        return Translator.toLocale("param.required", new Object[]{paramName});
    }

}
