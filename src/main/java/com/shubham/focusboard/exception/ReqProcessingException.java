package com.shubham.focusboard.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.System.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ReqProcessingException extends RuntimeException {
    public ReqProcessingException(String message) {
        super(message);
    }

    public ReqProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
