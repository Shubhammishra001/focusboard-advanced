package com.shubham.focusboard.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dao.TaskDao;
import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.service.TaskService;
import com.shubham.focusboard.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
	 private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);


    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserService userService;

    
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public Task createTask(Task taskReq, String tenantId, String isActive) throws ReqProcessingException {
		try {
			if(Stream.of(taskReq,tenantId,isActive).noneMatch(Objects::isNull)) {
				Task task=new Task();
				task.setDescription(taskReq.getDescription());
				task.setDueDate(taskReq.getDueDate());
				task.setIsActive(isActive);
				task.setTenantId(tenantId);
				task.setTitle(taskReq.getTitle());
				task.setUser(taskReq.getUser());
				task.setStatus(taskReq.getStatus());
				Task savedData=taskDao.Save(task);
				if(Objects.nonNull(savedData) && savedData.getId()!=null) {
					return savedData;
				}
			}
		}catch(Exception e) {
			logger.error("Error fetching tasks", e);
		}
		return new Task();
	}

	@Override
	public Boolean deleteTaskOrActivateTask(Long id, String isActive) throws ReqProcessingException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Task updateTask(Long id, Task updatedTask) throws ReqProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getAllTasks() throws ReqProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getTaskById(Long id) throws ReqProcessingException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

	
	
	
}
