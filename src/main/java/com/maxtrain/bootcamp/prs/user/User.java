package com.maxtrain.bootcamp.prs.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UIDX_username", columnNames = { "username" }))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 20, nullable = false)
	private String username;
	@Column(length = 10, nullable = false)
	private String password;
	@Column(length = 20, nullable = false)
	private String firstname;
	@Column(length = 20, nullable = false)
	private String lastname;
	@Column(length = 12, nullable = false)
	private String phoneNumber;
	@Column(length = 75, nullable = false)
	private String email;
	private boolean isReviewer;
	private boolean isAdmin;
	@Column(name = "isActive")
	private boolean active;

	// generate Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phoneNumber;
	}

	public void setPhone(String phone) {
		this.phoneNumber = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isReviewer() {
		return isReviewer;
	}

	public void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", phone=" + phoneNumber + ", email=" + email + ", isReviewer=" + isReviewer
				+ ", isAdmin=" + isAdmin + ", active=" + active + "]";
	}

	public User() {
	}

	public User(String username, String password, String firstname, String lastname, String phone, String email,
			boolean isReviewer, boolean isAdmin, boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phone;
		this.email = email;
		this.isReviewer = isReviewer;
		this.isAdmin = isAdmin;
		this.active = active;
	}

}
