package com.github.nilscoding.mailbuilder.sessionimpl;

/**
 * Mail session for GMail / Google Mail.
 * @author nilscoding
 */
public class GMailMailSession extends SmtpSSLAuthMailSession {

    /**
     * GMail SMTP port.
     */
    protected static final int GMAIL_SMTP_PORT = 587;

    /**
     * Creates a new mail session for GMail / Google Mail with given username and password.
     * @param username username (email)
     * @param password password
     */
    public GMailMailSession(String username, String password) {
        super("smtp.gmail.com", GMAIL_SMTP_PORT, username, password);
    }

}
