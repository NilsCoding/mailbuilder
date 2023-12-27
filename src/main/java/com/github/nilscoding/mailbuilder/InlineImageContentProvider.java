package com.github.nilscoding.mailbuilder;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

/**
 * Content provider for inline images.
 * @author nilscoding
 */
public class InlineImageContentProvider extends BinaryContentProvider {

    /**
     * DataSource.
     */
    protected DataSource datasource;
    /**
     * Image ID.
     */
    protected String imageid;

    /**
     * Creates a new inline image content provider based on a DataSource with a given content id.
     * @param dataSource data source
     * @param imageId    content id
     */
    public InlineImageContentProvider(DataSource dataSource, String imageId) {
        super();
        this.datasource = dataSource;
        this.imageid = imageId;
    }

    /**
     * Creates a new inline image content provider from given binary data, content type and content id.
     * @param imageData   image data
     * @param contentType content type
     * @param imageId     content id
     */
    public InlineImageContentProvider(byte[] imageData, String contentType, String imageId) {
        super();
        this.datasource = new ByteArrayDataSource(imageData, contentType);
        this.imageid = imageId;
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
        return this.imageid;
    }

    /**
     * Returns the name.
     * @return name
     */
    @Override
    public String getName() {
        return null;
    }

}
