package com.github.nilscoding.mailbuilder.utils;

/**
 * String utils
 * @author nilscoding
 */
public class StringUtils {
    
    private StringUtils() { }
    
    /**
     * Checks if the given string is empty or not
     * @param str   string to check
     * @return  true if empty, false if not empty
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return (str.length() == 0);
    }
    
}
