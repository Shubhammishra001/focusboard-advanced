package com.shubham.focusboard.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dao.TaskDao;
import com.shubham.focusboard.dto.TaskDto;
import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.service.TaskService;
import com.shubham.focusboard.service.UserService;
import com.shubham.focusboard.util.ProdConts;

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
		try {
			if(Stream.of(id,isActive).noneMatch(Objects::isNull)) {
				Task task=taskDao.findById(id).get();
				if(Objects.nonNull(task)&& task.getId()!=null) {
					task.setIsActive(isActive);
					Task savedTask=taskDao.Save(task);
					if(Objects.nonNull(savedTask)&& savedTask.getId()!=null){
						return true;
					}
				}
			}
		}catch(Exception e) {
			logger.error("Error delete Task Or ActivateTask", e);
			
		}
		return false;
	}

	@Override
	public Task updateTask(TaskDto updatedTask) throws ReqProcessingException {
		// TODO Auto-generated method stub
		try {
			if(Stream.of(updatedTask.getId()).noneMatch(Objects::isNull)) {
				Task task=taskDao.findById(updatedTask.getId()).get();
				User user=userService.findUserByUserId(updatedTask.getUserId());
				if(Objects.nonNull(task)&& task.getId()!=null && Objects.nonNull(user) && user.getId()!=null) {
					task.setIsActive(ProdConts.TRUE);
					task.setDescription(updatedTask.getDescription());
					task.setDueDate(updatedTask.getDueDate());
					task.setStatus(updatedTask.getStatus());
					task.setTenantId(user.getTenantId());
					task.setTitle(updatedTask.getTitle());
					task.setUser(null);
					Task savedTask=taskDao.Save(task);
					if(Objects.nonNull(savedTask)&& savedTask.getId()!=null){
						return savedTask;
					}
				}
			}
		}catch(Exception e) {
			logger.error("Error delete Task Or ActivateTask", e);
		}
		return new Task();
	}

	@Override
	public List<Task> getAllTasks() throws ReqProcessingException {
		try {
				List<Task> taskList=taskDao.findAll();
				logger.warn(" taskList ",taskList);
				System.err.println(" taskList data "+taskList);
				if(Objects.nonNull(taskList)) {
					return taskList;
				}
				}
		catch(Exception e) {
			logger.error("Error get all tasks ", e);
		}
		return Collections.emptyList();
	}

	@Override
	public Task getTaskById(Long id) throws ReqProcessingException {
		try {
			if(Stream.of(id).noneMatch(Objects::isNull)) {
				Task task=taskDao.findById(id).get();
				if(Objects.nonNull(task)) {
					return task;
					}
				}
		}catch(Exception e) {
			logger.error("Error delete Task Or ActivateTask", e);
		}
		return new Task();
	}
}
