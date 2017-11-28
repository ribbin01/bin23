package com.example.nan.h_app;

import java.util.ArrayList;

/**
 * Created by Ribbin on 13-Nov-17.
 */


public class Student {
    float score;
    String name;

    public Student(String name, float score) {
        this.score = score;
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Student> getSampleStudentData(int size) {
        ArrayList<Student> student = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                student.add(new Student("รับ", (float) Math.random() * 100));
            } else {
                student.add(new Student("จ่าย", (float) Math.random() * 100));
            }

        }
        return student;
    }
}
