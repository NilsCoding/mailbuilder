package com.github.nilscoding.mailbuilder;

/**
 * String content provider.
 * @author nilscoding
 */
public abstract class StringContentProvider {

    /**
     * Creates a new string content provider instance.
     */
    public StringContentProvider() {
    }

    /**
     * Returns the string content data.
     * @return string content data
     */
    public abstract String getStringData();

    /**
     * Returns the string data charset. Default is utf-8.
     * @return string charset
     */
    public String getStringCharset() {
        return "utf-8";
    }

}
