package com.tostechllc.bsmrau_e_diary;

public class postedDirectory {
    int id;
    String catagory, department;

    public postedDirectory(int id ,String catagory ,String department) {
        this.id = id;
        this.catagory = catagory;
        this.department = department;

        System.out.println("postedDirectory: "+id);

    }
    public postedDirectory(){}

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
