package com.m2dl.bioshare.mail;


/**
 * Created by oussama on 18/01/2015.
 */
public class DataToSend {

    private String comment;
    private double latitude;
    private double longitude;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DataToSend(){

    }

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
