package com.shubham.focusboard.scheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.repository.TaskRepository;
import com.shubham.focusboard.service.EmailService;

@Component
public class TaskReminderScheduler {
	
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    // Runs every day at 8 AM
   // @Scheduled(cron = "0 0 8 * * *")
    //30sec      
    
    @Scheduled(cron = "0 0 8 * * *")
    public void sendReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Task> allTasks = taskRepository.findAll();  // not filtering by date in DB

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Task task : allTasks) {
            try {
                LocalDate dueDate = task.getDueDate();
                System.err.println(" isemail reminder "+task.isEmailReminders());
           	   System.err.println(" tomorrow  "+dueDate+" " +tomorrow+" "+ task.isEmailReminders());
                //if (dueDate.equals("2025-07-22") && Boolean.TRUE.equals("TRUE")) {
                	   System.err.println(" tomorrow  "+tomorrow);
                	   
                	  // System.err.println(" tomorrow  "+task.);
                	   
                	String subject = "Task Reminder: " + task.getTitle();
                    String body = "Reminder: Your task \"" + task.getTitle() + "\" is due tomorrow (" + task.getDueDate() + ").";
                    
                    System.out.println("Sending reminder to: " + task.getUser().getLoginId());
                    System.out.println("Subject: " + subject);
                    
                    emailService.sendReminderEmail("write task email  email", subject, body);
                //}
            } catch (Exception e) {
            	System.err.println("Failed to process task ID " + task.getId() + ": " + e.getMessage());
            	e.printStackTrace();

            }
        }
    }
}