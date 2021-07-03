package com.example.petstore.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "_user")
public class User extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(nullable = false, unique=true)
	private String username;
	private String firstName;
	private String lastName;
	@Column(nullable = false, unique=true)
	private String email;
	@Column(nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String phone;
	private int userStatus;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date creationTime;
	@Column(columnDefinition = "boolean default false")
	private boolean isAdmin = false;
	@Column(columnDefinition = "boolean default true")
	private boolean isEnabled = true;

	protected User() {
	}

	public User(String username, String firstName, String lastName, String email, String password, String phone,
			int userStatus, Date creationTime, boolean isAdmin, boolean isEnabled) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userStatus = userStatus;
		this.creationTime = creationTime;
		this.isAdmin = isAdmin;
		this.isEnabled = isEnabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	
	public void setValues(User user) {
		this.setEmail(user.getEmail());
		this.setPhone(user.getPhone());
		this.setUsername(user.getUsername());
		this.setUserStatus(user.getUserStatus());
		this.setAdmin(user.isAdmin());
		this.setEnabled(user.isEnabled());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
	}
	

}