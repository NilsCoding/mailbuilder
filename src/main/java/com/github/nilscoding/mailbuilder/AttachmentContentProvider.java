package com.github.nilscoding.mailbuilder;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 * Content provider for attachments
 * @author nilscoding
 */
public class AttachmentContentProvider extends BinaryContentProvider {
    
    protected DataSource datasource;
    protected String filename;
    
    /**
     * Creates a new content provider for attachments based on a DataSource and a file name
     * @param datsource data source
     * @param filename  file name
     */
    public AttachmentContentProvider(DataSource datsource, String filename) {
        super();
        this.datasource = datsource;
        this.filename = filename;
    }

    /**
     * Creates a new content provider for attachment, based on binary data, content type and file name
     * @param fileData  file data
     * @param contentType   content type
     * @param filename  file name
     */
    public AttachmentContentProvider(byte[] fileData, String contentType, String filename) {
        super();
        this.datasource = new ByteArrayDataSource(fileData, contentType);
        this.filename = filename;
    }
    
    @Override
    public DataSource getDataSource() {
        return this.datasource;
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
