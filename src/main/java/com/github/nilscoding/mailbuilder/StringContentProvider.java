package com.github.nilscoding.mailbuilder;

/**
 * String content provider
 * @author nilscoding
 */
public abstract class StringContentProvider {
    
    public StringContentProvider() {
    }
    
    /**
     * Returns the string content data
     * @return  string content data
     */
    public abstract String getStringData();

    /**
     * Returns the string data charset
     * @return  string charset
     */
    public String getStringCharset() {
        return "utf-8";
    }
    
}
