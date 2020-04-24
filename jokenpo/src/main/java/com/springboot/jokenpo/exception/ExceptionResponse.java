package com.springboot.jokenpo.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private static final long serialversionUID = 1L;

    private Date timestamp;
    private String message;
    private String detail;

    public ExceptionResponse(Date timestamp, String message, String detail) {
        super();
        this.setTimestamp(timestamp);
        this.setMessage(message);
        this.setDetail(detail);
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
