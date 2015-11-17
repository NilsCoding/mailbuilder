package com.github.nilscoding.mailbuilder;

import com.github.nilscoding.mailbuilder.utils.StringUtils;
import java.util.LinkedList;
import java.util.List;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Mail Builder, used for building a message with optional attachments and inline attachments
 * @author nilscoding
 */
public class MailBuilder {

    private Session session;
    private final List<InternetAddress> from = new LinkedList<>();
    private final List<InternetAddress> to = new LinkedList<>();
    private final List<InternetAddress> cc = new LinkedList<>();
    private final List<InternetAddress> bcc = new LinkedList<>();
    private String subject;
    private StringContentProvider plainTextProvider;
    private StringContentProvider htmlTextProvider;
    private HtmlToPlainConverter plainConverter = null;
    private final List<BinaryContentProvider> inlineImages = new LinkedList<>();
    private final List<BinaryContentProvider> attachments = new LinkedList<>();
    
    private MailBuilder() {
    }
    
    /**
     * Creates a new MailBuilder based on a MailSession
     * @param mailSession   mail session
     * @return  new MailBuilder instance
     */
    public static MailBuilder onSession(MailSession mailSession) {
        MailBuilder mb = new MailBuilder();
        mb.session = mailSession.createNewSession();
        return mb;
    }
    
    /**
     * Creates a new MailBuilder based on a JavaMail session
     * @param session   JavaMail session
     * @return  new MailBuilder instance
     */
    public static MailBuilder onSession(Session session) {
        MailBuilder mb = new MailBuilder();
        mb.session = session;
        return mb;
    }
    
    /**
     * Adds one or more senders
     * @param address   sender address
     * @return  builder instance
     */
    public MailBuilder addFrom(InternetAddress... address) {
        if (address != null) {
            for (InternetAddress oneAddress : address) {
                if (oneAddress != null) {
                    this.from.add(oneAddress);
                }
            }
        }
        return this;
    }
    
    /**
     * Adds one or more senders
     * @param address   sender address
     * @return  builder instance
     */
    public MailBuilder addFrom(String... address) {
        if (address != null) {
            for (String oneAddress : address) {
                if (oneAddress != null) {
                    try {
                        this.from.add(new InternetAddress(oneAddress));
                    } catch (AddressException adrEx) {
                    }
                }
            }
        }
        return this;
    }
    
    /**
     * Adds senders
     * @param addresses sender addresses
     * @return  builder instance
     */
    public MailBuilder addFrom(Iterable<InternetAddress> addresses) {
        if (addresses != null) {
            for (InternetAddress oneAddress : addresses) {
                if (oneAddress != null) {
                    this.from.add(oneAddress);
                }
            }
        }
        return this;
    }
    
    /**
     * Sets the sender, clears previously added sender
     * @param address   sender address
     * @return  builder instance
     */
    public MailBuilder setFrom(InternetAddress address) {
        this.from.clear();
        if (address != null) {
            this.from.add(address);
        }
        return this;
    }
    
    /**
     * Sets the sender, clears previously added sender
     * @param address   sender address
     * @return  builder instance
     */
    public MailBuilder setFrom(String address) {
        this.from.clear();
        if (StringUtils.isEmpty(address) == false) {
            try {
                this.from.add(new InternetAddress(address));
            } catch (AddressException adrEx) {
            }
        }
        return this;
    }

    /**
     * Clears all senders
     * @return  builder instance
     */
    public MailBuilder clearFrom() {
        this.from.clear();
        return this;
    }
     
    /**
     * Adds one or more recipients
     * @param address   recipient address
     * @return  builder instance
     */
    public MailBuilder addTo(InternetAddress... address) {
        if (address != null) {
            for (InternetAddress oneAddress : address) {
                if (oneAddress != null) {
                    this.to.add(oneAddress);
                }
            }
        }
        return this;
    }
    
    /**
     * Adds one or more recipients
     * @param address   recipient address
     * @return  builder instance
     */
    public MailBuilder addTo(String... address) {
        if (address != null) {
            for (String oneAddress : address) {
                if (oneAddress != null) {
                    try {
                        this.to.add(new InternetAddress(oneAddress));
                    } catch (AddressException adrEx) {
                    }
                }
            }
        }
        return this;
    }
    
    /**
     * Adds recipients
     * @param addresses recipient addresses
     * @return  builder instance
     */
    public MailBuilder addTo(Iterable<InternetAddress> addresses) {
        if (addresses != null) {
            for (InternetAddress oneAddress : addresses) {
                if (oneAddress != null) {
                    this.to.add(oneAddress);
                }
            }
        }
        return this;
    }    
    
    /**
     * Sets the recipient, clears previously added recipient
     * @param address   recipient address
     * @return  builder instance
     */
    public MailBuilder setTo(InternetAddress address) {
        this.to.clear();
        if (address != null) {
            this.to.add(address);
        }
        return this;
    }
    
    /**
     * Sets the recipient, clears previously added recipient
     * @param address   recipient address
     * @return  builder instance
     */
    public MailBuilder setTo(String address) {
        this.to.clear();
        if (StringUtils.isEmpty(address) == false) {
            try {
                this.to.add(new InternetAddress(address));
            } catch (AddressException adrEx) {
            }
        }
        return this;
    }
    
    /**
     * Clears all recipients
     * @return  builder instance
     */
    public MailBuilder clearTo() {
        this.to.clear();
        return this;
    }
    
    /**
     * Adds one or more cc recipients
     * @param address   cc recipient address
     * @return  builder instance
     */
    public MailBuilder addCc(InternetAddress... address) {
        if (address != null) {
            for (InternetAddress oneAddress : address) {
                if (oneAddress != null) {
                    this.cc.add(oneAddress);
                }
            }
        }
        return this;
    }
    
    /**
     * Adds one or more cc recipients
     * @param address   cc recipient address
     * @return  builder instance
     */
    public MailBuilder addCc(String... address) {
        if (address != null) {
            for (String oneAddress : address) {
                if (oneAddress != null) {
                    try {
                        this.cc.add(new InternetAddress(oneAddress));
                    } catch (AddressException adrEx) {
                    }
                }
            }
        }
        return this;
    }
    
    /**
     * Adds cc recipients
     * @param addresses cc recipient addresses
     * @return  builder instance
     */
    public MailBuilder addCc(Iterable<InternetAddress> addresses) {
        if (addresses != null) {
            for (InternetAddress oneAddress : addresses) {
                if (oneAddress != null) {
                    this.cc.add(oneAddress);
                }
            }
        }
        return this;
    } 
    
    /**
     * Sets the cc recipient, clears previously added cc recipient
     * @param address   cc recipient address
     * @return  builder instance
     */
    public MailBuilder setCc(InternetAddress address) {
        this.cc.clear();
        if (address != null) {
            this.cc.add(address);
        }
        return this;
    }
    
    /**
     * Sets the cc recipient, clears previously added cc recipient
     * @param address   cc recipient address
     * @return  builder instance
     */
    public MailBuilder setCc(String address) {
        this.cc.clear();
        if (StringUtils.isEmpty(address) == false) {
            try {
                this.cc.add(new InternetAddress(address));
            } catch (AddressException adrEx) {
            }
        }
        return this;
    }
    
    /**
     * Clears all cc recipients
     * @return  builder instance
     */
    public MailBuilder clearCc() {
        this.cc.clear();
        return this;
    }
    
    /**
     * Adds one or more bcc recipients
     * @param address   bcc recipient address
     * @return  builder instance
     */
    public MailBuilder addBcc(InternetAddress... address) {
        if (address != null) {
            for (InternetAddress oneAddress : address) {
                if (oneAddress != null) {
                    this.bcc.add(oneAddress);
                }
            }
        }
        return this;
    }
    
    /**
     * Adds one or more bcc recipients
     * @param address   bcc recipient address
     * @return  builder instance
     */
    public MailBuilder addBcc(String... address) {
        if (address != null) {
            for (String oneAddress : address) {
                if (oneAddress != null) {
                    try {
                        this.bcc.add(new InternetAddress(oneAddress));
                    } catch (AddressException adrEx) {
                    }
                }
            }
        }
        return this;
    }
        
    /**
     * Adds bcc recipients
     * @param addresses bcc recipient addresses
     * @return  builder instance
     */
    public MailBuilder addBcc(Iterable<InternetAddress> addresses) {
        if (addresses != null) {
            for (InternetAddress oneAddress : addresses) {
                if (oneAddress != null) {
                    this.bcc.add(oneAddress);
                }
            }
        }
        return this;
    } 
    
    /**
     * Sets the bcc recipient, clears previously added bcc recipient
     * @param address   bcc recipient address
     * @return  builder instance
     */
    public MailBuilder setBcc(InternetAddress address) {
        this.bcc.clear();
        if (address != null) {
            this.bcc.add(address);
        }
        return this;
    }
    
    /**
     * Sets the bcc recipient, clears previously added bcc recipient
     * @param address   bcc recipient address
     * @return  builder instance
     */
    public MailBuilder setBcc(String address) {
        this.bcc.clear();
        if (StringUtils.isEmpty(address) == false) {
            try {
                this.bcc.add(new InternetAddress(address));
            } catch (AddressException adrEx) {
            }
        }
        return this;
    }
    
    /**
     * Clears all bcc recipients
     * @return  builder instance
     */
    public MailBuilder clearBcc() {
        this.bcc.clear();
        return this;
    }
    
    /**
     * Sets the subject
     * @param subject   subject
     * @return  builder instance
     */
    public MailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }
    
    /**
     * Sets the plain text body, assuming UTF-8 charset
     * @param plainText plain text body
     * @return  builder instance
     */
    public MailBuilder setBodyPlain(String plainText) {
        this.plainTextProvider = new DefaultStringContentProvider(plainText);
        return this;
    }
    
    /**
     * Sets the plain text provider
     * @param contentProvider   plain text provider
     * @return  builder instance
     */
    public MailBuilder setBodyPlain(StringContentProvider contentProvider) {
        this.plainTextProvider = contentProvider;
        return this;
    }
    
    /**
     * Clears the plain text body
     * @return  builder instance
     */
    public MailBuilder clearBodyPlain() {
        this.plainTextProvider = null;
        return this;
    }
    
    /**
     * Sets the html text body, assuming UTF-8 charset
     * @param htmlText  html text body
     * @return  builder instance
     */
    public MailBuilder setBodyHtml(String htmlText) {
        this.htmlTextProvider = new DefaultStringContentProvider(htmlText);
        return this;
    }
    
    /**
     * Sets the html text provider
     * @param contentProvider   html text provider
     * @return  builder instance
     */
    public MailBuilder setBodyHtml(StringContentProvider contentProvider) {
        this.htmlTextProvider = contentProvider;
        return this;
    }
    
    /**
     * Clears the html text body
     * @return  builder instance
     */
    public MailBuilder clearBodyHtml() {
        this.htmlTextProvider = null;
        return this;
    }
    
    /**
     * Enables automatic plain text conversion from html if no plain text is set
     * @return  builder instance
     */
    public MailBuilder enableAutoPlainFromHtml() {
        this.plainConverter = new SimpleHtmlToPlainConverter();
        return this;
    }
    
    /**
     * Enables automatic plain text conversion from html if no plain text is set using the given converter
     * @param converter html-to-plain converter
     * @return  builder instance
     */
    public MailBuilder enableAutoPlainFromHtml(HtmlToPlainConverter converter) {
        this.plainConverter = converter;
        return this;
    }
    
    /**
     * Disables automatic plain text conversion from html
     * @return  builder instance
     */
    public MailBuilder disableAutoPlainFromHtml() {
        this.plainConverter = null;
        return this;
    }
    
    /**
     * Adds one or more inline images
     * @param inlineImage   inline image
     * @return  builder instance
     */
    public MailBuilder addHtmlInlineImage(BinaryContentProvider... inlineImage) {
        if ((inlineImage != null) && (inlineImage.length > 0)) {
            for (BinaryContentProvider oneContentProvider : inlineImage) {
                if (oneContentProvider != null) {
                    this.inlineImages.add(oneContentProvider);
                }
            }
        }
        return this;
    }
    
    /**
     * Adds inline images
     * @param inlineImages  inline images
     * @return  builder instance
     */
    public MailBuilder addHtmlInlineImages(Iterable<BinaryContentProvider> inlineImages) {
        if (inlineImages != null) {
            for (BinaryContentProvider oneContentProvider : inlineImages) {
                if (oneContentProvider != null) {
                    this.inlineImages.add(oneContentProvider);
                }
            }
        }
        return this;
    }
    
    /**
     * Clears all inline images
     * @return  builder instance
     */
    public MailBuilder clearHtmlInlineImages() {
        this.inlineImages.clear();
        return this;
    }
    
    /**
     * Adds one or more attachments
     * @param attachement   attachment
     * @return  builder instance
     */
    public MailBuilder addAttachment(BinaryContentProvider... attachement) {
        if ((attachement != null) && (attachement.length > 0)) {
            for (BinaryContentProvider oneContentProvider : attachement) {
                if (oneContentProvider != null) {
                    this.attachments.add(oneContentProvider);
                }
            }
        }
        return this;
    }
    
    /**
     * Adds attachments
     * @param attachments   attachments
     * @return  builder instance
     */
    public MailBuilder addAttachments(Iterable<BinaryContentProvider> attachments) {
        if (attachments != null) {
            for (BinaryContentProvider oneContentProvider : attachments) {
                if (oneContentProvider != null) {
                    this.attachments.add(oneContentProvider);
                }
            }
        }
        return this;
    }
    
    /**
     * Clears all attachments
     * @return  builder instance
     */
    public MailBuilder clearAttachments() {
        return this;
    }
    
    /**
     * Builds the complete JavaMail message, ready for sending<br>
     * Only uses data that has been set, no further validation is done
     * @return  message or null on error
     */
    public Message buildMessage() {
        try {
            MimeMessage message = new MimeMessage(this.session);
            message.setSubject(this.subject);
            if (this.from.isEmpty() == false) {
                message.addFrom(this.from.toArray(new InternetAddress[0]));
            }
            if (this.to.isEmpty() == false) {
                message.addRecipients(Message.RecipientType.TO, this.to.toArray(new InternetAddress[0]));
            }
            if (this.cc.isEmpty() == false) {
                message.addRecipients(Message.RecipientType.CC, this.cc.toArray(new InternetAddress[0]));
            }
            if (this.bcc.isEmpty() == false) {
                message.addRecipients(Message.RecipientType.BCC, this.bcc.toArray(new InternetAddress[0]));
            }

            Multipart mainMultipart = null;
            
            // if there are any attachments, then the main part must be multipart
            if (this.attachments.isEmpty() == false) {
                mainMultipart = new MimeMultipart();
                message.setContent(mainMultipart);
            }
            
            // collect content
            String htmlContent = null;
            String htmlContentCharset = null;
            if (this.htmlTextProvider != null) {
                htmlContent = this.htmlTextProvider.getStringData();
                htmlContentCharset = this.htmlTextProvider.getStringCharset();
            }
            String plainContent = null;
            String plainContentCharset = null;
            if (this.plainTextProvider != null) {
                plainContent = this.plainTextProvider.getStringData();
                plainContentCharset = this.plainTextProvider.getStringCharset();
            } else if ((this.plainConverter != null) && (htmlContent != null)) {
                // if no plain text provider is given but automatic conversion from
                //   html is enabled and html content is present then auto-convert
                plainContent = this.plainConverter.convertHtmlToPlainText(htmlContent);
                plainContentCharset = htmlContentCharset;
            }
            
            // build html part with inline images (if any)
            if (StringUtils.isEmpty(htmlContent) == false) {
                if (this.inlineImages.isEmpty() == true) {
                    // only html, no images
                    if (mainMultipart != null) {
                        MimeBodyPart htmlContentPart = new MimeBodyPart();
                        String htmlType = "text/html";
                        if (StringUtils.isEmpty(htmlContentCharset) == false) {
                            htmlType = htmlType + "; charset=" + htmlContentCharset;
                        }
                        htmlContentPart.setContent(htmlContent, htmlType);
                        mainMultipart.addBodyPart(htmlContentPart);
                    } else {
                        message.setContent(htmlContent, htmlContentCharset);
                    }
                } else {
                    // html plus images
                    MimeMultipart htmlPart = new MimeMultipart("related");
                    // add html first
                    MimeBodyPart htmlContentPart = new MimeBodyPart();
                    String htmlType = "text/html";
                    if (StringUtils.isEmpty(htmlContentCharset) == false) {
                        htmlType = htmlType + "; charset=" + htmlContentCharset;
                    }
                    htmlContentPart.setContent(htmlContent, htmlType);
                    htmlPart.addBodyPart(htmlContentPart);
                    // then add images
                    for (BinaryContentProvider oneImageProvider : this.inlineImages) {
                        if (oneImageProvider == null) {
                            continue;
                        }
                        MimeBodyPart imagePart = new MimeBodyPart();
                        String contentID = oneImageProvider.getContentId();
                        if (contentID != null) {
                            // Content-ID should look like <some_id>
                            if (contentID.startsWith("<") == false) {
                                contentID = "<" + contentID;
                            }
                            if (contentID.endsWith(">") == false) {
                                contentID = contentID + ">";
                            }
                            imagePart.addHeader("Content-ID", contentID);
                        }
                        imagePart.setDataHandler(new DataHandler(oneImageProvider.getDataSource()));
                        htmlPart.addBodyPart(imagePart);
                    }
                    // if plain text part is present, then build alternative parts, else just add html part
                    if (StringUtils.isEmpty(plainContent) == false) {
                        Multipart multipart = new MimeMultipart("alternative");
                        // add text first, then html
                        MimeBodyPart textPart = new MimeBodyPart();
                        textPart.setText(plainContent, plainContentCharset);
                        multipart.addBodyPart(textPart);
                        MimeBodyPart mbpHtml = new MimeBodyPart();
                        mbpHtml.setContent(htmlPart);
                        multipart.addBodyPart(mbpHtml);
                        if (mainMultipart != null) {
                            MimeBodyPart mbp = new MimeBodyPart();
                            mbp.setContent(multipart);
                            mainMultipart.addBodyPart(mbp);
                        } else {
                            message.setContent(multipart);
                        }
                    } else {
                        if (mainMultipart != null) {
                            MimeBodyPart mbp = new MimeBodyPart();
                            mbp.setContent(htmlPart);
                            mainMultipart.addBodyPart(mbp);
                        } else {
                            message.setContent(htmlPart);
                        }
                    } // plain text part
                } // inline images
            } else {
                // only plain text
                if (mainMultipart != null) {
                    MimeBodyPart mbp = new MimeBodyPart();
                    mbp.setText(plainContent, plainContentCharset);
                    mainMultipart.addBodyPart(mbp);
                } else {
                    message.setText(plainContent, plainContentCharset);
                }
            }
            
            // add attachments
            if ((this.attachments.isEmpty() == false) && (mainMultipart != null)) {
                for (BinaryContentProvider oneAttachmentProvider : this.attachments) {
                    if (oneAttachmentProvider == null) {
                        continue;
                    }
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.setDataHandler(new DataHandler(oneAttachmentProvider.getDataSource()));
                    attachmentPart.setFileName(oneAttachmentProvider.getName());
                    mainMultipart.addBodyPart(attachmentPart);
                }
            }
            
            return message;
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * Builds and sends the message
     * @return  true if message was sent, false on error
     */
    public boolean buildMessageAndSend() {
        Message message = this.buildMessage();
        if (message != null) {
            try {
                Transport.send(message);
                return true;
            } catch (Exception ex) {
            }
        }
        return false;
    }
    
}
