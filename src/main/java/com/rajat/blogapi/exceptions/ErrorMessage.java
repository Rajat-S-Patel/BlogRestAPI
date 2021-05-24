package com.rajat.blogapi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private String message;
    private Date timestamp;
    private HttpStatus status;

    public ErrorMessage(String message, Date timestamp,HttpStatus status) {
        this.message = message;
        this.timestamp = timestamp;
        this.setStatus(status);
    }
    
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
