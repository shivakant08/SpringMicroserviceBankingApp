package com.shivakant.userservice.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private Object message;

    public ErrorResponse(int status, String error, Object message) {
        this.timeStamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public Object getMessage() {
        return message;
    }
}
