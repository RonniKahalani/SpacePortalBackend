package com.example.demojpa1.advice;

import java.util.Date;

public class ErrorMessageFactory {

    public static ErrorMessage createErrorMessage(int statusCode, Date timestamp, String message, String description) {
     return new ErrorMessage(statusCode, timestamp, message, description);
    }
}
