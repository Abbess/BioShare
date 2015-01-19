package com.m2dl.bioshare.server;

import android.util.Log;



import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

public class Mail implements IServer {

    private Properties props = null;
    private Session session = null;
    private String compte;
    private String password;
    private Message message;
    private String fileName = "";
    public Mail(String file) {
        fileName = file;
        init();
        this.compte = "biodiversityshare";
        this.password = "masterdl01";
        authentification();
    }

    public void authentification() {

        this.session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        Log.e("user", Mail.this.compte);
                        Log.e("pass", Mail.this.password);
                        return new PasswordAuthentication(Mail.this.compte.toString().trim(), Mail.this.password.toString().trim());
                    }
                });
    }

    public void init() {
        this.props = new Properties();
        this.props.put("server.smtp.host", "smtp.gmail.com");
        this.props.put("server.smtp.auth", "false");
        this.props.put("server.smtp.port", "25");// ou 465
    }

    @Override
    public void send(String mailDestinataire, String subject, String body) {
        try {

            Log.e("init", "init");
            this.session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(Mail.this.compte, Mail.this.password);
                        }
                    });

            message = new MimeMessage(this.session);

            message.setFrom(new InternetAddress("biodiversityshare@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailDestinataire));
            message.setSubject(subject);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(this.fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(this.fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Log.e("msg", "avantSend");
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Transport.send(Mail.this.message, Mail.this.message.getRecipients(Message.RecipientType.TO));
                    } catch (MessagingException e) {
                        e.printStackTrace();


                    }
                }
            };
            t.start();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
