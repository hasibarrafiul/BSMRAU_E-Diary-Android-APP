package com.tostechllc.bsmrau_e_diary;

public class listedCatagory {
    int id;
    String catagory, image;

    public listedCatagory(int id, String catagory, String image) {
        this.id = id;
        this.catagory = catagory;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
