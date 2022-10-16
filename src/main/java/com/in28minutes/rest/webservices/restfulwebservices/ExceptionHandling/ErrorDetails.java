package com.in28minutes.rest.webservices.restfulwebservices.ExceptionHandling;

import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime timestamps;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timestamps, String message, String details) {
        this.timestamps = timestamps;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(LocalDateTime timestamps) {
        this.timestamps = timestamps;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
