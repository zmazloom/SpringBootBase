package com.mazloom.message;

public class FoodMessage {

    private FoodMessage() {
    }

    public static String foodNameIsDuplication() {
        return Translator.toLocale("food.name.is.duplicate", null);
    }

    public static String foodNotFound() {
        return Translator.toLocale("food.not.found", null);
    }

    public static String foodRemoved() {
        return Translator.toLocale("food.deleted", null);
    }

    public static String foodUpdated() {
        return Translator.toLocale("food.updated", null);
    }

    public static String foodCreated() {
        return Translator.toLocale("food.created", null);
    }

}
