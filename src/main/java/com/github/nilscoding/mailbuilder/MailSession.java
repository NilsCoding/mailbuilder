package com.github.nilscoding.mailbuilder;

import javax.mail.Session;

/**
 * Mail session, used as a source for creating JavaMail sessions
 * @author nilscoding
 */
public interface MailSession {
    
    /**
     * Creates a new JavaMail session, each call should return a new session
     * @return  new JavaMail session
     */
    public Session createNewSession();
    
}
