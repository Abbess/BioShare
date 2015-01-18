package com.m2dl.bioshare.mail;

import android.util.Log;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

public class Mail {

    private Properties props = null;
    private Session session = null;
    private String compte;
    private String password;
    private Message message;

    public Mail() {

        init();

        // infos pour l'authentification
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

        this.props.put("mail.smtp.host", "smtp.gmail.com");
        //this.props.put("mail.smtp.socketFactory.port", "465");
        //this.props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        this.props.put("mail.smtp.auth", "false");
        this.props.put("mail.smtp.port", "25");// ou 465
    }

    public void sendMailTo(String mailDestinataire, String subject, String body) {
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
            message.setText(body);

            Log.e("msg", "avantSend");
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Transport.send(Mail.this.message, Mail.this.message.getRecipients(Message.RecipientType.TO));

                        Log.e("send", "SEND");
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


    // NON UTILISE ( laissée en cas de besoin )
    public void doAll() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("biodiversityshare", "masterdl01");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("zabour@maghrebUnited.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("biodiversityshare@gmail.com"));
            message.setSubject("Maghreb United ;) ");
            message.setText("Zabour Khouyaa Abbes :D " +
                    "\n\n Mkawed khouya Mehdi !");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
