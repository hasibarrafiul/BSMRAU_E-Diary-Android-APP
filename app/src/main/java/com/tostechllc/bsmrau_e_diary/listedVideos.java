package com.tostechllc.bsmrau_e_diary;

public class listedVideos {
    int id;
    String videolink,date,videoname;

    public listedVideos(int id, String videolink, String date,String videoname) {
        this.id = id;
        this.videolink = videolink;
        this.date = date;
        this.videoname=videoname;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
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
