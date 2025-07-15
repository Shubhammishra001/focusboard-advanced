package com.shubham.focusboard.enties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USERID")
	private Long id;
	
	@Column(name="USERNAME",nullable = false)
	private String username;
	
	@Column(name="PASSWORD",nullable = false)
	private String password;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="TENANTID",nullable = false)
	private String tenantId;
	
	@Column(name="ISACTIVE")
	private String isActive;
	
	@Column(name = "login_id", unique = true, nullable = false)
    private String loginId;
	
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", tenantId="
				+ tenantId + ", isActive=" + isActive + "]";
	}

	public User(Long id, String username, String password, String role, String tenantId, String isActive,
			String loginId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.tenantId = tenantId;
		this.isActive = isActive;
		this.loginId = loginId;
	}

	public User() {
		
	}
	

}
