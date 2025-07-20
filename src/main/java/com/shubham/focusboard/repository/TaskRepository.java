package com.shubham.focusboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.enties.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task t where t.tenantId = :tenantId AND t.isActive = :isActive  ")
	public Page<Task> getTaskListWithPagination(
	       @Param("tenantId")String tenantId,@Param("isActive")String  isActive,Pageable pageable);
	
	@Query("SELECT t FROM Task t " +
		       "WHERE t.isActive = isActive AND t.tenantId = :tenantId " )
		       //"AND (LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       //"     OR LOWER(t.status) LIKE LOWER(CONCAT('%', :keyword, '%')))")	
	public Page<Task> searchAllTasks(@Param("isActive")String isActive, Pageable pageable,@Param("tenantId") String tenantId);

}
