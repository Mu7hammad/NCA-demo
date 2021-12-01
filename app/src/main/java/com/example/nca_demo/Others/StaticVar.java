package com.example.nca_demo.Others;

import com.example.nca_demo.Models.EventItem;
import com.example.nca_demo.Models.Student;
import com.example.nca_demo.Models.TimeTableItem;

import java.util.ArrayList;
import java.util.List;

public class StaticVar {
    public static boolean Arabic = true;
    public static Student student = new Student("","",0,"","","",0);
    public static List<TimeTableItem> time_table_items = new ArrayList<>();
    public static List<EventItem> events_items = new ArrayList<>();
    public static int delay = 2000;
}

