package com.tostechllc.bsmrau_e_diary;

public class listedOfficials {
    int id;
    String name, department;

    public listedOfficials(int id , String name , String department) {
        this.id = id;
        this.name = name;
        this.department = department;

    }
    public listedOfficials(){}

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
