# mailbuilder *by NilsCoding*

*mailbuilder* simplifies the creation of emails using the JavaMail API. Utilizing the fluent interface methods of the central class `MailBuilder` you can build an email and easily attach files and inline images.

# Getting started

## tl,dr show me some example

```java
MailSession mailSession = new GMailMailSession("your_gmail_address", "secret_password");
MailBuilder mailBuilder = MailBuilder.onSession(mailSession);
Message message = mailBuilder
    .addFrom("your_gmail_address")
    .addTo("somebody@example.com")
    .setSubject("You've been waiting for this email!")
    .setBodyPlain("Hello there, an email.")
    .setBodyHtml("<html><body>Hello <i>there</i>, an email.</body></html>")
    .addAttachment(new TextAttachmentContentProvider("This is a simple text file attachment.", "text/plain", "somefile.txt"))
    .buildMessage();
Transport.send(message);
```

## the `MailSession`
Building an email with the JavaMail API uses a session to your SMTP email server, so *mailbuilder* needs such a session, too. So you first need to create a `MailSession` instance. *mailbuilder* comes with two build-in classes (in package `com.github.nilscoding.mailbuilder.sessionimpl`):

- `SmtpSSLAuthMailSession`
- `GMailMailSession`

They provide an easy way to configure a JavaMail session with STARTSSL and SMTP authentification. The `GMailMailSession` is pre-configured with Google's SMTP server settings.

If your SMTP server doesn't support STARTTLS and/or SMTP authentification (which is hopefully not the case), then feel free to implement a `MailSession` yourself or use a pre-inited JavaMail `Session` instead.

## creating a `MailBuilder`
The `MailBuilder` is the central class of this tiny framework. You use it by creating an instance of it, calling one of the overloaded `MailBuilder.onSession(...)` methods which takes either a `MailSession` or a JavaMail `Session` as an initial parameter.

## adding a subject
It's as easy as this: just call the `setSubject(String)` method and it will set the subject.

## the sender
It is highly recommended to set at least one sender address by calling one of the `setFrom(...)` or `addFrom(...)` methods.

## recipients: to, cc, bcc
There are many methods to add normal *to* recipients as well as *cc* and *bcc* recipients. You are suggested to add at least one *to* recipient.

## providing content

Message content can either be set as a string using the `setBody*(String)` methods or by being provided via `StringContentProvider`s.

The content providers are a way to lazily provide data. They are normally consumed when the message is build.

*mailbuilder* distinguishes between two types of attachments:

- inline attachments
- message attachments

While *message attachments* are the ones like PDF or ZIP files that are sent as "normal" attachments, *inline attachments* are referenced *in* the HTML mail body, most common use is embedding images.

*mailbuilder* uses `BinaryContentProvider`s to access both types of attachments. It comes with three build-in implementations:

- `AttachmentContentProvider`: a wrapper around a regular `DataSource` (or direct byte data) and a file name for use as normal attachments
- `InlineImageContentProvider`: a wrapper around a regular `DataSource` (or direct byte data) and an image id for use as inline attachments; the image id should refer to an `<img src="cid:imgId"/>` image id in the HTML body
- `TextAttachmentContentProvider`: a wrapper for a `String` to be sent as a text file attachment; this makes it easier to send generated texts as text file attachments 

## build the message
When all recipient and other message data is set, you can call `buildMessage()` to build a JavaMail `Message` object. If something goes wrong, `null` will be returned, but that's very unlikely to happen.

This `Message` can then be sent using JavaMail's `Transport.send(Message)` method. For your convenience both steps can be done at once calling `buildMessageAndSend()`.

# Site notes

## no specific order of method calls required
You can call all building methods of *mailbuilder* in any order you like to. All data is stored internally and the resulting message object is only assembled when `buildMessage()` is called. Please note that `MailBuilder` instances are not thread-safe.

## similar methods, similar behaviour
*mailbuilder* tries to make its usage as easy as possible and therefore focuses on similar behaviour when using similar methods.

When not otherwise noted, a method starting with `set` overwrites the corresponding value. In case of lists, all previous items will be removed and only the provided parameter will be used.

A method starting with `add` will collect the value (or values) and add them to the previously provided values. 

When not otherwise noted, this methods are null-safe: they will handle null collections and null values as gracefully as possible.

## only using given data
*mailbuilder* will only use the data that was provided to it. It will not add any additional, unnecessary fields or other data. That said, calling the `buildMessage()` method may result in an incomplete message that might be rejected either by your SMTP server or recipient's SMTP server. On the other hand it allows you to add all kinds of data yourself afterwards.

## Gimmicks
*mailbuilder* has a `enableAutoPlainFromHtml()` method which sets an internal magic flag: if you set it and only provide an HTML body (plain text must not be set or `null`), then a plain text part is automatically created from the HTML text, trying to preserve some basic structure of the HTML document. If you are not satisfied with the result, you can provide your own implementation instance of `HtmlToPlainConverter` to this method and do the HTML-to-plain conversion yourself.

# Contribute

Feel free to fork *mailbuilder* and add more useful functions to it.

Found a bug? Please hunt it down by debugging first (if possible) and then create an issue on GitHub. I'll try my best to fix it then.

It may sound unkind, but please do not open issues about problems in actually sending, receiving or displaying emails. I know that sending emails programatically can be hard. But *mailbuilder* is only intended to prepare emails, not to actually send them, so I will hardly be able to assist in getting the email out towards your SMTP server, sorry.

# Copyright / License

*mailbuilder* is licensed under the MIT License

## The MIT License (MIT)

Copyright (c) 2015 NilsCoding

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.