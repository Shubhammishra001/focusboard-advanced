package com.shubham.focusboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.focusboard.scheduler.TaskReminderScheduler;

@RestController
@RequestMapping("/api/test")
public class TestEmailController {

    @Autowired
    private TaskReminderScheduler scheduler;

    @GetMapping("/send-reminders")
    public ResponseEntity<String> sendTestReminder() {
        scheduler.sendReminders();
        return ResponseEntity.ok("Reminder method triggered.");
    }
}

