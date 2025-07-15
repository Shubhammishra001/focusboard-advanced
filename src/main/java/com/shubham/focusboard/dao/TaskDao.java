package com.shubham.focusboard.dao;

import java.util.List;
import java.util.Optional;

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
;

}
