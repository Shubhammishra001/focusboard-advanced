package com.shubham.focusboard.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dao.TaskDao;
import com.shubham.focusboard.dto.TaskDto;
import com.shubham.focusboard.enties.Role;
import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.TaskRepository;
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
	public Task createTask(String loginId,TaskDto taskReq, String tenantId, String isActive) throws ReqProcessingException {
		try {
			if(Stream.of(taskReq,tenantId,isActive).noneMatch(Objects::isNull)) {
				User user=userService.findUserByLoginId(loginId);
				System.err.println(" user "+user);
				Task task=new Task();
				task.setDescription(taskReq.getDescription());
				task.setDueDate(taskReq.getDueDate());
				task.setIsActive(isActive);
				task.setTenantId(tenantId);
				task.setTitle(taskReq.getTitle());
				task.setUser(user);
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
			if(Stream.of(updatedTask.getTaskId()).noneMatch(Objects::isNull)) {
				Task task=taskDao.findById(updatedTask.getTaskId()).get();
				User user=userService.findUserByUserId(updatedTask.getUserId());
				if(Objects.nonNull(task)&& task.getId()!=null && Objects.nonNull(user) && user.getId()!=null) {
					task.setIsActive(ProdConts.TRUE);
					task.setDescription(updatedTask.getDescription());
					task.setDueDate(updatedTask.getDueDate());
					task.setStatus(updatedTask.getStatus());
					task.setTenantId(user.getTenantId());
					task.setTitle(updatedTask.getTitle());
					task.setUser(user);
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

	@Override
	public Page<TaskDto> getTaskListWithPagenation(String loginId,Pageable pageable, String keyword, String tenantId, String isActive)
			throws ReqProcessingException {
		try {
			if(Stream.of(tenantId,isActive).noneMatch(Objects::isNull)) {
				User user=userService.findUserByLoginId(loginId);
				Page<Task> taskList=null;
				if(user.getRole()==Role.ADMIN) {
					taskList=taskDao.searchAllTasks(pageable,tenantId,isActive);
				}
				else if(user.getRole()==Role.USER) {
					taskList=taskDao.getTaskListWithPagenation(loginId,pageable,keyword,tenantId,isActive);
				}
		//	Page<Task> taskList=taskDao.getTaskListWithPagenation(loginId,pageable,keyword,tenantId,isActive);
			if(Objects.nonNull(taskList)) {
				List<TaskDto> dtoList=taskList.getContent().stream().map(this::toDTO).toList();
				return new PageImpl<>(dtoList,pageable,taskList.getTotalElements());
			}
			}
		}
	catch(Exception e) {
		logger.error("Error get all tasks ", e);
	}
	return Page.empty();
   }
	
	
	public  TaskDto toDTO(Task entity) {
		System.err.print(" test "+entity);
	    TaskDto dto = new TaskDto();
	    dto.setTaskId(entity.getId());
	    dto.setTitle(entity.getTitle());
	    dto.setDescription(entity.getDescription());
	    dto.setDueDate(entity.getDueDate() != null ? entity.getDueDate().toString() : null);
	    dto.setStatus(entity.getStatus());
	    dto.setUserId(entity.getUser().getId());
	    dto.setUserName(entity.getUser().getUsername()); // <-- Correct usage
	    return dto;
	}

}
