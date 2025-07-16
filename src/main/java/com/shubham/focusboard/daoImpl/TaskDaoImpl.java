package com.shubham.focusboard.daoImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.dao.TaskDao;
import com.shubham.focusboard.enties.Task;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.TaskRepository;
@Repository
public class TaskDaoImpl implements TaskDao{
	 private static final Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);

    @Autowired private TaskRepository taskRepository;
	
    @Override
	public Task Save(Task task) throws ReqProcessingException{
		try {
			if(Objects.nonNull(task)) {
				Task savedData=taskRepository.save(task);
				if(Objects.nonNull(savedData) && savedData!=null) {
					return savedData;
				}
			}
		}catch(Exception e) {
			logger.error("Error fetching tasks", e);
		}
		return new Task();
	}

	@Override
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}

	@Override
	public Optional<Task> findById(Long id) {
		// TODO Auto-generated method stub
		return taskRepository.findById(id);
	}

	@Override
	public List<Task> findbyUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
}
