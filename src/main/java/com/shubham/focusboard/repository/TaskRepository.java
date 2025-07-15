package com.shubham.focusboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.enties.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
