package com.tcet.student_management.response;

public class StudentResponse {

    private String message;
    private Object data;

    public StudentResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}