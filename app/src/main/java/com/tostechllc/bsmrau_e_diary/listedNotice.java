package com.tostechllc.bsmrau_e_diary;

public class listedNotice {

    String image,heading,details,date;
    int id,status;

    public listedNotice(String image, String heading, String details, String date, int id, int status) {
        this.image = image;
        this.heading = heading;
        this.details = details;
        this.date = date;
        this.id = id;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
