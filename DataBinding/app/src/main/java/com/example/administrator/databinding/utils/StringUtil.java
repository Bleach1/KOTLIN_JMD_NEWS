package com.example.administrator.databinding.utils;
/**
 @description:  
 @author:  ljn
 @time:  2018/3/28 
 */
public class StringUtil {

    public static String capitalize(final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
}
