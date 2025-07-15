package com.shubham.focusboard.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.service.TaskService;
import com.shubham.focusboard.service.UserService;
import com.shubham.focusboard.util.ProdConts;
import com.shubham.focusboard.util.SecurityUtil;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	 private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    	@Autowired
	    private TaskService taskService;
  
    	@Autowired
    	private UserService userService;
    	
    	@Autowired
  	    private SecurityUtil securityUtil;

    	
    	
	   @PostMapping("/create")
	    public ResponseEntity<?> createTask(@RequestParam String loginId,@RequestBody Task taskReq)throws ReqProcessingException {
		   try {logger.info("API: createTask with loginId ",loginId);
			     if(loginId!=null) {
			    	 User user=userService.findUserByLoginId(loginId);
			    	 if(Objects.nonNull(user)) {
			    		 taskReq.setUser(user);
			    		 String tenantId=user.getTenantId();
			    		 if(tenantId!=null) {
			    			 Task savedTask=taskService.createTask(taskReq,tenantId,ProdConts.TRUE);
			    			 if(savedTask!=null && savedTask.getId()!=null) {
			    				 logger.info("API: createTask");
			    				 return ResponseEntity.ok(savedTask);
			    			 }
			    		 }
			    	 }
			     }
		       // return taskService.createTask(task);
		        return ResponseEntity.badRequest().body(new Task());
		   }
		   catch(Exception e) {
			   logger.error("Error fetching tasks", e);
		   }
		   return ResponseEntity.internalServerError().build();
	    }
	
	   @GetMapping("/{id}")
	   public ResponseEntity<?> getTaskById(@PathVariable Long id)throws ReqProcessingException{
		   try {
			   Task task=taskService.getTaskById(id);
			   if(Objects.nonNull(task)) {
				   return ResponseEntity.ok(task);
			   }
			   return ResponseEntity.badRequest().body(new Task());
		   }catch(Exception e) {
			   logger.error(" Error fetching task ",e);
			   return ResponseEntity.badRequest().body(e.getStackTrace());
		   }
	   }
	   
	    @GetMapping("/all")
	    public ResponseEntity<?> getAllTasks() throws ReqProcessingException {
	        try {
	            List<Task> tasks = taskService.getAllTasks();
	            if(Objects.nonNull(tasks)) {
	            	return ResponseEntity.ok(Collections.emptyList());
	            }
	            return ResponseEntity.badRequest().body(new Task());
	        } catch (Exception e) {
	            logger.error("Error fetching tasks", e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	   
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateTask(@PathVariable Long id,@RequestBody Task updatedTask)throws ReqProcessingException {
	        try {
	            Task task = taskService.updateTask(id, updatedTask);
	            if(Objects.nonNull(task) && task.getId()!=null) {
	            	return ResponseEntity.ok(task);
	            }
	            return ResponseEntity.badRequest().body(new Task());
	        } catch (Exception e) {
	            logger.error("Error updating task", e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }

	    @DeleteMapping("/deactivate/{id}")
	    public ResponseEntity<?> deleteTask(@PathVariable Long id)throws ReqProcessingException {
	        try {
	            if(Boolean.TRUE.equals(taskService.deleteTaskOrActivateTask(id,ProdConts.FALSE))){
	            	return ResponseEntity.ok("Task deleted successfully");
	            }
	            return ResponseEntity.ok("Task deleted failed");
	        } catch (Exception e) {
	            logger.error("Error deleting task", e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }

	    @DeleteMapping("/activate/{id}")
	    public ResponseEntity<?> activateTask(@PathVariable Long id)throws ReqProcessingException {
	        try {
	            if(Boolean.TRUE.equals(taskService.deleteTaskOrActivateTask(id,ProdConts.TRUE))){
	            	return ResponseEntity.ok("Task deleted successfully");
	            }
	            return ResponseEntity.ok("Task activate  Failed");
	        } catch (Exception e) {
	            logger.error("Error activate task", e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
}


















