package com.github.nilscoding.mailbuilder;

import javax.activation.DataSource;

/**
 * Binary data provider
 * @author nilscoding
 */
public abstract class BinaryContentProvider {
    
    public BinaryContentProvider() {
    }
    
    /**
     * Returns the data source for this content provider
     * @return  data source
     */
    public abstract DataSource getDataSource();
    
    /**
     * Returns the content id (used for inline attachments)
     * @return  content id, null if not needed
     */
    public abstract String getContentId();
    
    /**
     * Returns the file name (used for attachments)
     * @return  file name, null if not needed
     */
    public abstract String getName();
    
}
