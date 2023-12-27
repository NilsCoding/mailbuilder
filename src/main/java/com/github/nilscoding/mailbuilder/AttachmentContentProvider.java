package com.github.nilscoding.mailbuilder;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 * Content provider for attachments.
 * @author nilscoding
 */
public class AttachmentContentProvider extends BinaryContentProvider {

    /**
     * DataSource.
     */
    protected DataSource datasource;
    /**
     * Filename.
     */
    protected String filename;

    /**
     * Creates a new content provider for attachments based on a DataSource and a file name.
     * @param dataSource data source
     * @param fileName   file name
     */
    public AttachmentContentProvider(DataSource dataSource, String fileName) {
        super();
        this.datasource = dataSource;
        this.filename = fileName;
    }

    /**
     * Creates a new content provider for attachment, based on binary data, content type and file name.
     * @param fileData    file data
     * @param contentType content type
     * @param fileName    file name
     */
    public AttachmentContentProvider(byte[] fileData, String contentType, String fileName) {
        super();
        this.datasource = new ByteArrayDataSource(fileData, contentType);
        this.filename = fileName;
    }

    /**
     * Returns the DataSource.
     * @return DataSource
     */
    @Override
    public DataSource getDataSource() {
        return this.datasource;
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
