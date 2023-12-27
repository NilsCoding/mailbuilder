package com.github.nilscoding.mailbuilder;

/**
 * Simple implementation for converting an HTML document to plain text.
 * @author nilscoding
 */
public class SimpleHtmlToPlainConverter implements HtmlToPlainConverter {

    /**
     * Creates a new converter instance.
     */
    public SimpleHtmlToPlainConverter() {
    }

    /**
     * Converts the given HTML text to plain text.
     * @param htmlText html text to convert
     * @return converted text
     */
    @Override
    public String convertHtmlToPlainText(String htmlText) {
        if (htmlText == null) {
            return null;
        }
        String tmpStr = htmlText;
        // remove all newlines
        tmpStr = tmpStr.replace("\r", "");
        tmpStr = tmpStr.replace("\n", "");
        // convert <br> tags to newline
        tmpStr = tmpStr.replaceAll("<br[^>]*>", "\n");
        // replace headers with newline block
        tmpStr = tmpStr.replaceAll("<[/]*h[\\d][^>]*>", "\n");
        // replace paragraphs with newline block
        tmpStr = tmpStr.replaceAll("<[/]*p[^>]*>", "\n");
        tmpStr = tmpStr.replaceAll("<[/]*div[^>]*>", "\n");
        // check if string has a body part
        if (tmpStr.contains("<body") && tmpStr.contains("</body>")) {
            // remove everything outside of body
            tmpStr = tmpStr.replaceAll("(?s).*<body[^>]*>(.*)</body>.*", "$1");
        }
        // remove script blocks
        tmpStr = tmpStr.replaceAll("(?s)<script[^>]*>.*?</script>", "");
        tmpStr = tmpStr.replaceAll("(?s)<noscript[^>]*>.*?</noscript>", "");
        // replace lists with pseudo list elements
        tmpStr = tmpStr.replaceAll("<li[^>]*>", "\n- ");
        // add a newline at list end
        tmpStr = tmpStr.replaceAll("</ul>", "\n");
        tmpStr = tmpStr.replaceAll("</ol>", "\n");
        // remove all tags
        tmpStr = tmpStr.replaceAll("<[^>]+>", "");
        // reduce multiple newlines
        tmpStr = tmpStr.replaceAll("(?s)[\\n]{3,}", "\n\n");
        // trim text
        tmpStr = tmpStr.trim();

        return tmpStr;
    }

}
