package com.github.nilscoding.mailbuilder;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 * Content provider for text-based attachments
 * @author nilscoding
 */
public class TextAttachmentContentProvider extends BinaryContentProvider {

    protected byte[] data;
    protected String contentType;
    protected String filename;
    
    /**
     * Creates an attachment content provider with a text file based on given string, content type and file name
     * @param text  attachment text content
     * @param contentType   content type, e.g. text/plain
     * @param filename  file name
     */
    public TextAttachmentContentProvider(String text, String contentType, String filename) {
        if (text != null) {
            try {
                this.data = text.getBytes("utf-8");
            } catch (Exception ex) {
                this.data = text.getBytes();
            }
        } else {
            this.data = new byte[0];
        }
        this.contentType = contentType;
        this.filename = filename;
    }
    
    @Override
    public DataSource getDataSource() {
        return new ByteArrayDataSource(this.data, this.contentType);
    }

    @Override
    public String getContentId() {
        return null;
    }

    @Override
    public String getName() {
        return this.filename;
    }
    
}
