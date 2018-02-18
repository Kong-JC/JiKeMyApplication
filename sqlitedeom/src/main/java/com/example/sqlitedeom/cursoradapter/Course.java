package com.example.sqlitedeom.cursoradapter;

/**
 * Created by Kong on 2018/2/17.
 */

public class Course {

    private String name;
    private String teacher;
    private String price;

    public Course(String name, String teacher, String price) {
        this.name = name;
        this.teacher = teacher;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
