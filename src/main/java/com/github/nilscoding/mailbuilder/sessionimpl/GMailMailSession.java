package com.github.nilscoding.mailbuilder.sessionimpl;

/**
 * Mail session for GMail
 * @author nilscoding
 */
public class GMailMailSession extends SmtpSSLAuthMailSession {
    
    /**
     * Creates a new mail session for GMail with given username and password
     * @param username  username (email)
     * @param password  password
     */
    public GMailMailSession(String username, String password) {
        super("smtp.gmail.com", 587, username, password);
    }

}
