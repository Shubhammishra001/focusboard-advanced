package com.shubham.focusboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dto.TaskDto;
import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.exception.ReqProcessingException;

@Service
public interface TaskService {

  public Task createTask(String loginId,TaskDto taskReq, String tenantId, String isActive)throws ReqProcessingException;

  public Boolean deleteTaskOrActivateTask(Long id, String isActive)throws ReqProcessingException;

  public Task updateTask(TaskDto updatedTask)throws ReqProcessingException;

  public List<Task> getAllTasks()throws ReqProcessingException;

  public Task getTaskById(Long id)throws ReqProcessingException;

  public Page<TaskDto> getTaskListWithPagenation(String loginId,Pageable pageable, String keyword, String tenantId, String isActive)throws ReqProcessingException;

}
