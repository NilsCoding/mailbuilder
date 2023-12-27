package com.github.nilscoding.mailbuilder;

/**
 * Default StringContentProvider implementation.
 * @author nilscoding
 */
public class DefaultStringContentProvider extends StringContentProvider {

    /**
     * String data.
     */
    protected String data;

    /**
     * Creates a string content provider with given data.
     * @param str string to use as data
     */
    public DefaultStringContentProvider(String str) {
        super();
        this.data = str;
    }

    /**
     * Returns the string data.
     * @return string data
     */
    @Override
    public String getStringData() {
        return this.data;
    }

}
