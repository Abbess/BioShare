package com.m2dl.bioshare.mail;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by oussama on 18/01/2015.
 */
public class DataToSend {

    private String comment = "";
    private double latitude;
    private double longitude;
    private String fileName = "";
    private String pointInteret="";
    private String pseudo = "";


    public String getPointInteret() {
        return pointInteret;
    }

    public void setPointInteret(String pointInteret) {
        this.pointInteret = pointInteret;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DataToSend() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


/*
    public String getPointInterretAsString() {
        String formatedListString = "";
        for (int i = 0; i < pointInteret.size(); i++) {
            formatedListString += "Point d\'intérêt " + i + " : " + pointInteret.get(i) + "\n";
        }
        return formatedListString;
    }
*/
    public double getLatitude() {
        return latitude;
    }

    public String getComment() {
        return comment;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
