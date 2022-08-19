package com.tostechllc.bsmrau_e_diary;

public class listedEvent {
    int id,status;
    String headingimage,heading,content,created_on;

    public listedEvent(int id, int status, String headingimage, String heading, String content, String created_on) {
        this.id = id;
        this.status = status;
        this.headingimage = headingimage;
        this.heading = heading;
        this.content = content;
        this.created_on = created_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeadingimage() {
        return headingimage;
    }

    public void setHeadingimage(String headingimage) {
        this.headingimage = headingimage;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }
}
