package com.example.nca_demo.Models;

public class ResultItem {

    private String subject;
    private String result;

    public ResultItem(String subject, String result) {
        this.subject = subject;
        this.result = result;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
