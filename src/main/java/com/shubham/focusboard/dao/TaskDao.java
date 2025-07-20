package com.shubham.focusboard.dao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedTransferQueue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.exception.ReqProcessingException;
@Service
public interface TaskDao {
	
	public Task Save(Task task) throws ReqProcessingException;
	public List<Task> findAll();
	public Optional<Task> findById(Long id);
	public List<Task> findbyUserId(Long userId);
	public void deleteById(Long id);
	public Page<Task> getTaskListWithPagenation(String loginId,Pageable pageable, String keyword, String tenantId, String isActive)throws ReqProcessingException;
	public Page<Task> searchAllTasks(Pageable pageable, String keyword, String isActive)throws ReqProcessingException;
}















