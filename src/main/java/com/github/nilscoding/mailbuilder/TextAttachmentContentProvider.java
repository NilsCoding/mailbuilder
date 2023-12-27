package com.github.nilscoding.mailbuilder;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 * Content provider for text-based attachments.
 * @author nilscoding
 */
public class TextAttachmentContentProvider extends BinaryContentProvider {

    /**
     * Data.
     */
    protected byte[] data;
    /**
     * Content-Type.
     */
    protected String contentType;
    /**
     * Filename.
     */
    protected String filename;

    /**
     * Creates an attachment content provider with a text file based on given string, content type and file name.
     * @param text        attachment text content
     * @param contentType content type, e.g. text/plain
     * @param fileName    file name
     */
    public TextAttachmentContentProvider(String text, String contentType, String fileName) {
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
        this.filename = fileName;
    }

    /**
     * Returns the DataSource.
     * @return DataSource
     */
    @Override
    public DataSource getDataSource() {
        return new ByteArrayDataSource(this.data, this.contentType);
    }

    /**
     * Returns the content ID.
     * @return content ID
     */
    @Override
    public String getContentId() {
        return null;
    }

    /**
     * Returns the name.
     * @return name
     */
    @Override
    public String getName() {
        return this.filename;
    }

}
