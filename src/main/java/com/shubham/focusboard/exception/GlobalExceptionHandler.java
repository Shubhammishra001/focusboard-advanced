package com.shubham.focusboard.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ReqProcessingException.class)
    public ResponseEntity<String> handleReqProcessingException(ReqProcessingException ex) {
        ((org.slf4j.Logger) logger).error("ReqProcessingException: {}", ex.getMessage(),ex);

        // Return stack trace as String (like e.printStackTrace but to String)
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();

        return ResponseEntity.badRequest().body(stackTrace);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        ((org.slf4j.Logger) logger).error("Exception: {}", ex.getMessage(), ex);

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();

        return ResponseEntity.internalServerError().body(stackTrace);
    }
}