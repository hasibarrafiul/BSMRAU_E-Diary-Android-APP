package com.tostechllc.bsmrau_e_diary;

public class holidayListView {
    String name, desc, day;

    public holidayListView(String name, String desc,String day) {
        this.name = name;
        this.desc = desc;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
