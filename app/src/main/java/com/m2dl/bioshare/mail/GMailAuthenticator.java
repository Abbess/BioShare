package com.m2dl.bioshare.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by mehdi on 17/01/2015.
 */
public class GMailAuthenticator
        extends Authenticator {
    String user;
    String pw;

    public GMailAuthenticator(String username, String password) {
        super();
        this.user = username;
        this.pw = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pw);
    }
}
