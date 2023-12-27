package com.github.nilscoding.mailbuilder;

/**
 * Interface for HTML to plain text converter.
 * @author nilscoding
 */
public interface HtmlToPlainConverter {

    /**
     * Converts the given HTML document to plain text, suitable for use in plain text e-mail body.
     * @param htmlText html text to convert
     * @return converted plain text
     */
    String convertHtmlToPlainText(String htmlText);

}
