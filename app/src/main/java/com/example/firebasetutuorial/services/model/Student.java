package com.example.firebasetutuorial.services.model;

public class Student {
    private String name;
    private String roll;


    public Student() {

    }

    public Student( String name, String roll) {

        this.name = name;
        this.roll = roll;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }


}
