package com.example.henrylee.hackdavis2;

/**
 * Created by Henry Lee on 1/21/2018.
 */

public class StudentData {

    private String first;
    private String last;
    private String sid;
    private String email;
    private String classCode;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }


    public StudentData(String first, String last, String sid, String email, String classCode){
        this.first = first;
        this.last = last;
        this.sid = sid;
        this.email = email;
        this.classCode = classCode;
    }
}
