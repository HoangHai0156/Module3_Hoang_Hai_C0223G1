package com.example.productmanager.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
    private static String REGEX;
    public static boolean isProductNameValid(String productName){
        REGEX = "^[a-zA-Z\\s]{7,25}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(productName);
        return matcher.matches();
    }
    public static boolean isDescriptionValid(String description){
        REGEX = "^[a-zA-Z][a-zA-Z0-9\\s.,!?-]{8,245}$";
        return Pattern.matches(REGEX,description);
    }
}
