package com.shubham.focusboard.service;

public interface EmailService {
    void sendReminderEmail(String to, String subject, String body);

}
