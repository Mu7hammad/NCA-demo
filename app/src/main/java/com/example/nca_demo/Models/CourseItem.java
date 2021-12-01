package com.example.nca_demo.Models;

public class CourseItem {

    private String title;
    private int attendance;

    public CourseItem(String title, int attendance) {
        this.title = title;
        this.attendance = attendance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
