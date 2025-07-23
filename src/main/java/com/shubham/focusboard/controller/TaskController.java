package com.shubham.focusboard.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.focusboard.dto.TaskDto;
import com.shubham.focusboard.enties.AuditLog;
import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.service.AuditLogService;
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

    	@Autowired private AuditLogService auditLogService;
    	
    	//@PreAuthorize("hasRole('USER','ADMIN')")
	   @PostMapping("/create")
	    public ResponseEntity<?> createTask(@RequestBody TaskDto taskReq)throws ReqProcessingException {
		   try {
	    	User loggedInUser = securityUtil.getLoggedInUser();
	    	String loginId = loggedInUser.getLoginId();
	    	String tenantId = loggedInUser.getTenantId();
	    	logger.info("API: createTask with loginId "+loginId);
	    	if(tenantId!=null) {
			    			 Task savedTask=taskService.createTask(loginId,taskReq,tenantId,ProdConts.TRUE);
			    			 if(savedTask!=null && savedTask.getId()!=null) {
			    				 logger.info("API: createTask");
			    				 auditLogService.logAction(savedTask.getId(), "CREATED",loginId);
			    				 return ResponseEntity.ok(savedTask);
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
	  //get Task By Id
    	//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    	@PreAuthorize("hasRole('ADMIN') or authentication.principal.loginId == #loginId")
	   @GetMapping("/{id}")
	   public ResponseEntity<?> getTaskById(@PathVariable Long id)throws ReqProcessingException{
		   try {
		    	User loggedInUser = securityUtil.getLoggedInUser();
		    	String loginId = loggedInUser.getLoginId();
		    	String tenantId = loggedInUser.getTenantId();

			   logger.info("API: getTaskById with loginId ",loginId);
			     if(loginId!=null) {
			    	 User user=userService.findUserByLoginId(loginId);
			    	 if(Objects.nonNull(user)) {
			    		  tenantId=user.getTenantId();
			    		 if(tenantId!=null) {
						   Task task=taskService.getTaskById(id);
						   if(Objects.nonNull(task)) {
							   auditLogService.logAction(task.getId(), "get By Id Task", user.getLoginId());
						       return ResponseEntity.ok(task);
						   }
			    		 }
			    	 }
			     }
			   return ResponseEntity.badRequest().body(new Task());
		   }catch(Exception e) {
			   logger.error(" Error fetching task ",e);
			   return ResponseEntity.badRequest().body(e.getStackTrace());
		   }
	   }
    	
    	@PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/all")
	    public ResponseEntity<?> getAllTasks() throws ReqProcessingException {
	        try { 
	        	User loggedInUser = securityUtil.getLoggedInUser();
	        	String loginId = loggedInUser.getLoginId();
	        	String tenantId = loggedInUser.getTenantId();

				   logger.info("API: get all tasks with loginId ",loginId);
			   if(loginId!=null) {
				    	 User user=userService.findUserByLoginId(loginId);
				    	 if(Objects.nonNull(user)) {
				    		  tenantId=user.getTenantId();
				    		 if(tenantId!=null) {
					            List<Task> tasks = taskService.getAllTasks();
					            if(Objects.nonNull(tasks)) {
					            	return ResponseEntity.ok(tasks);
					            }
				    		 }
				    	 }
				     }
	            return ResponseEntity.badRequest().body(new Task());
	        } catch (Exception e) {
	            logger.error("Error fetching tasks", e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	   
    	@PreAuthorize("hasRole('USER')")
	    @PutMapping("/updateTask")
	    public ResponseEntity<?> updateTask(@RequestBody TaskDto updatedTask)throws ReqProcessingException {
	        try {
	        	User loggedInUser = securityUtil.getLoggedInUser();
	        	String loginId = loggedInUser.getLoginId();
	        	String tenantId = loggedInUser.getTenantId();

				   logger.info("API: update task with loginId ",loginId);

				   if(loginId!=null) {
				    	 User user=userService.findUserByLoginId(loginId);
				    	 if(Objects.nonNull(user)) {
				    		  tenantId=user.getTenantId();
				    		 if(tenantId!=null) {
					            Task task = taskService.updateTask(updatedTask);
						            if(Objects.nonNull(task) && task.getId()!=null) {
										   auditLogService.logAction(task.getId(), "update Task", user.getLoginId());
						            	   return ResponseEntity.ok(task);
					            }
				            }
				    	 }
				     }
	            return ResponseEntity.badRequest().body(new Task());
	        } catch (Exception e) {
	            logger.error("Error updating task", e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }

    	@PreAuthorize("hasRole('USER')")
	    @DeleteMapping("/deactivate/{id}")
	    public ResponseEntity<?> deleteTask(@PathVariable Long id)throws ReqProcessingException {
	        try {
	        	User loggedInUser = securityUtil.getLoggedInUser();
	        	String loginId = loggedInUser.getLoginId();
	        	String tenantId = loggedInUser.getTenantId();

				   logger.info("API: deactivate with loginId ",loginId);
				     if(loginId!=null) {
				    	 User user=userService.findUserByLoginId(loginId);
				    	 if(Objects.nonNull(user)) {
				    		  tenantId=user.getTenantId();
				    		 if(tenantId!=null) {
				    			 if(Boolean.TRUE.equals(taskService.deleteTaskOrActivateTask(id,ProdConts.FALSE))){
				  				   auditLogService.logAction(id, "deactivate Task", user.getLoginId());
				    				 return ResponseEntity.ok("Task deleted successfully");
				    				 }
				    			 }
				    		 }
				    	  }
				     return ResponseEntity.ok("Task deleted failed");
	        } catch (Exception e) {
	            logger.error("Error deleting task", e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }

    	@PreAuthorize("hasRole('USER')")
	    @DeleteMapping("/activate/{id}")
	    public ResponseEntity<?> activateTask(@PathVariable Long id)throws ReqProcessingException {
	        try {
	        	User loggedInUser = securityUtil.getLoggedInUser();
	        	String loginId = loggedInUser.getLoginId();
	        	String tenantId = loggedInUser.getTenantId();

				   logger.info("API: activate task with loginId ",loginId);
				     if(loginId!=null) {
				    	 User user=userService.findUserByLoginId(loginId);
				    	 if(Objects.nonNull(user)) {
				    		  tenantId=user.getTenantId();
				    		 if(tenantId!=null) {
					            if(Boolean.TRUE.equals(taskService.deleteTaskOrActivateTask(id,ProdConts.TRUE))){
					            	  auditLogService.logAction(id, "activate Task", user.getLoginId());
					                	return ResponseEntity.ok("Task deleted successfully");
				            	}
					            }
				    		 }
				    	 }
				     return ResponseEntity.ok("Task activate  Failed");
				     } catch (Exception e) {
				    	 logger.error("Error activate task", e);
				    	 return ResponseEntity.badRequest().body(e.getMessage());
				    	 }
	        }
    	
    	//pagen ation with search keyword
    	
    	@PreAuthorize("hasAnyRole('USER','ADMIN')")
    	@GetMapping("/search")
    	public ResponseEntity<?> searchTasks(@RequestParam(required = false)String keyword,
    			@RequestParam(defaultValue = "0") int page ,@RequestParam(defaultValue = "5")int size)throws ReqProcessingException{
    		try {
    	    	User loggedInUser = securityUtil.getLoggedInUser();
    	    	String loginId = loggedInUser.getLoginId();
    	    	String tenantId = loggedInUser.getTenantId();
    	    	logger.info("API: searchTasks with loginId ",loginId);
    	    	Pageable pageable = PageRequest.of(page, size);
    	    	Page<TaskDto> taskList=taskService.getTaskListWithPagenation(loginId,pageable,keyword,tenantId ,ProdConts.TRUE);
    	    	if(!taskList.isEmpty()) {
    	    		
    	    		return ResponseEntity.ok(taskList);
    	    	}
    	    	return ResponseEntity.noContent().build();
    			
    		}catch(Exception e) {
    	        logger.error("Error in searchTasks", e);
    	        return ResponseEntity.internalServerError().body("Search failed");
    			
    		}
    	}
 
}





















