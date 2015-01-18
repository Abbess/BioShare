package com.m2dl.bioshare.globalPseudo;

import android.app.Application;

/**
 * Created by mehdi on 19/01/2015.
 */
public class GlobalClass extends Application {

    private String pseudo="";

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}