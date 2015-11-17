package com.github.nilscoding.mailbuilder;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 * Content provider for inline images
 * @author nilscoding
 */
public class InlineImageContentProvider extends BinaryContentProvider {

    protected DataSource datasource;
    protected String imageId;
    
    /**
     * Creates a new inline image content provider based on a DataSource with a given content id
     * @param datasource    data source
     * @param imageId   content id
     */
    public InlineImageContentProvider(DataSource datasource, String imageId) {
        super();
        this.datasource = datasource;
        this.imageId = imageId;
    }
    
    /**
     * Creates a new inline image content provider from given binary data, content type and content id
     * @param imageData image data
     * @param contentType   content type
     * @param imageId   content id
     */
    public InlineImageContentProvider(byte[] imageData, String contentType, String imageId) {
        super();
        this.datasource = new ByteArrayDataSource(imageData, contentType);
        this.imageId = imageId;
    }

    @Override
    public DataSource getDataSource() {
        return this.datasource;
    }

    @Override
    public String getContentId() {
        return this.imageId;
    }

    @Override
    public String getName() {
        return null;
    }
    
}
