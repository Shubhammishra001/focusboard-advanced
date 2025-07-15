package com.shubham.focusboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.exception.ReqProcessingException;

@Service
public interface TaskService {

  public Task createTask(Task taskReq, String tenantId, String isActive)throws ReqProcessingException;

  public Boolean deleteTaskOrActivateTask(Long id, String isActive)throws ReqProcessingException;

  public Task updateTask(Long id, Task updatedTask)throws ReqProcessingException;

  public List<Task> getAllTasks()throws ReqProcessingException;

  public Task getTaskById(Long id)throws ReqProcessingException;

}
