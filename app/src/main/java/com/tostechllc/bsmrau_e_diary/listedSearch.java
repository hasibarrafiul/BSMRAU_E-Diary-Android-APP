package com.tostechllc.bsmrau_e_diary;

public class listedSearch {
    int id, mobilenumber, officenumber, status;
    String name, designation, department, email, image;

    public listedSearch(int id , String name , String department) {
        this.id = id;
        this.name = name;
        this.department = department;

    }

    public listedSearch(int id, int mobilenumber, int officenumber, int status, String name, String designation, String department, String email, String image) {
        this.id = id;
        this.mobilenumber = mobilenumber;
        this.officenumber = officenumber;
        this.status = status;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.email = email;
        this.image = image;
    }

    public int getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(int mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public int getOfficenumber() {
        return officenumber;
    }

    public void setOfficenumber(int officenumber) {
        this.officenumber = officenumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public listedSearch(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
