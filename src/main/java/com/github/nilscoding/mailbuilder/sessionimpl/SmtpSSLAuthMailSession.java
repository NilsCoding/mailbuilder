package com.github.nilscoding.mailbuilder.sessionimpl;

import com.github.nilscoding.mailbuilder.MailSession;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Enumeration;
import java.util.Properties;

/**
 * SMTP over SSL (STARTTLS) mail session with authentication.
 * @author nilscoding
 */
public class SmtpSSLAuthMailSession implements MailSession {

    /**
     * SMTP server host.
     */
    protected String host;
    /**
     * SMTP server port.
     */
    protected int port = 465;
    /**
     * Username.
     */
    protected String username;
    /**
     * Password.
     */
    protected String password;
    /**
     * Additional properties.
     */
    protected Properties additionalProperties;

    /**
     * Creates a new SMTP mail session with host, port 465, username and password.
     * @param host     smtp server host name
     * @param username username
     * @param password password
     */
    public SmtpSSLAuthMailSession(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a new SMTP mail session with host, port, username and password.
     * @param host     smtp server host name
     * @param port     smtp server port (e.g. 465 or 587)
     * @param username username
     * @param password password
     */
    public SmtpSSLAuthMailSession(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * Sets additional properties, see
     * <a href="https://javamail.java.net/nonav/docs/api/com/sun/mail/smtp/package-summary.html">JavaMail</a>
     * for examples when using JavaMail.
     * @param additionalProperties additional properties to set
     */
    public void setAdditionalProperties(Properties additionalProperties) {
        if (additionalProperties == null) {
            this.additionalProperties = null;
            return;
        }
        if (this.additionalProperties == null) {
            this.additionalProperties = additionalProperties;
        } else {
            for (Enumeration<?> en = additionalProperties.propertyNames(); en.hasMoreElements(); ) {
                Object tmpPropertyName = en.nextElement();
                if (tmpPropertyName == null) {
                    continue;
                }
                String onePropertyName = String.valueOf(tmpPropertyName);
                Object onePropertyValue = additionalProperties.get(onePropertyName);
                if (onePropertyValue instanceof CharSequence) {
                    this.additionalProperties.setProperty(
                            onePropertyName,
                            ((CharSequence) onePropertyValue).toString()
                    );
                } else {
                    this.additionalProperties.put(onePropertyName, onePropertyValue);
                }
            }
        }
    }

    /**
     * Creates a new JavaMail session.
     * @return new JavaMail session
     */
    @Override
    public Session createNewSession() {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", this.host);
            props.put("mail.smtp.port", String.valueOf(this.port));
            if (this.additionalProperties != null) {
                for (Enumeration<?> en = this.additionalProperties.propertyNames(); en.hasMoreElements(); ) {
                    String onePropertyName = String.valueOf(en.nextElement());
                    Object onePropertyValue = this.additionalProperties.get(onePropertyName);
                    if (onePropertyValue instanceof CharSequence) {
                        props.setProperty(onePropertyName, ((CharSequence) onePropertyValue).toString());
                    } else {
                        props.put(onePropertyName, onePropertyValue);
                    }
                }
            }
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            return session;
        } catch (Exception ex) {
            return null;
        }
    }

}
