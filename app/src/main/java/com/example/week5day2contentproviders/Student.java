package com.example.week5day2contentproviders;

public class Student {
    private String studentName;
    private String studentGPA;
    private String studentPhone;

    public Student(String studentName, String studentGPA, String studentPhone) {
        this.studentName = studentName;
        this.studentGPA = studentGPA;
        this.studentPhone = studentPhone;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGPA() {
        return studentGPA;
    }

    public void setStudentGPA(String studentGPA) {
        this.studentGPA = studentGPA;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }
}
