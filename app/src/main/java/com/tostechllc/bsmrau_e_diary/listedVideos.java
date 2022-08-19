package com.tostechllc.bsmrau_e_diary;

public class listedVideos {
    int id;
    String videolink,date;

    public listedVideos(int id, String videolink, String date) {
        this.id = id;
        this.videolink = videolink;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
