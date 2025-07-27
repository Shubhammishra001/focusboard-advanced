package com.shubham.focusboard.enties;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "task")
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASKID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DUEDATE")
    private LocalDate dueDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TENANTID")
    private String tenantId;

    @Column(name = "ISACTIVE")
    private String isActive;

    @ManyToOne
    @JoinColumn(name = "USERID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;  // assigned employee
    
    @Column(name = "EMAILREMINDERS")
    private boolean emailReminders = true;  // default value is true
    
    public boolean isEmailReminders() {
		return emailReminders;
	}
	public void setEmailReminders(boolean emailReminders) {
		this.emailReminders = emailReminders;
	}
	public Task() {
    	
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
				+ ", status=" + status + ", tenantId=" + tenantId + ", isActive=" + isActive + ", user=" + user + "]";
	}

	public Task(Long id, String title, String description, LocalDate dueDate, String status, String tenantId,
			String isActive, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
		this.tenantId = tenantId;
		this.isActive = isActive;
		this.user = user;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Task(Long id, String title, String description, LocalDate dueDate, String status, String tenantId,
			String isActive, User user, Employee employee, boolean emailReminders) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
		this.tenantId = tenantId;
		this.isActive = isActive;
		this.user = user;
		this.employee = employee;
		this.emailReminders = emailReminders;
	}
    
    
    
    
}
