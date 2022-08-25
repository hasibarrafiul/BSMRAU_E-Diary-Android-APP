package com.tostechllc.bsmrau_e_diary;

public class listedSubcatagory {
    int id;
    String catagory, subcatagory;

    public listedSubcatagory(int id, String catagory, String subcatagory) {
        this.id = id;
        this.catagory = catagory;
        this.subcatagory = subcatagory;

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

    public String getSubcatagory() {
        return subcatagory;
    }

    public void setSubcatagory(String subcatagory) {
        this.subcatagory = subcatagory;
    }
}
