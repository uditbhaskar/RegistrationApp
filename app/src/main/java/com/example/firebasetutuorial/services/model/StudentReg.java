package com.example.firebasetutuorial.services.model;

public class StudentReg {
    private String timeStamp;
    private String id;
    private Student student;

    public StudentReg() {

    }

    public StudentReg(String timeStamp, String id, Student student) {
        this.timeStamp = timeStamp;
        this.id = id;
        this.student = student;
    }




    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
