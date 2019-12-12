package com.maxtrain.bootcamp.prs.vendor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UIDX_code", columnNames = { "name" }))
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 10, nullable = false)
	private String code;
	@Column(length = 200, nullable = false)
	private String name;
	@Column(length = 200, nullable = false)
	private String address;
	@Column(length = 50, nullable = false)
	private String city;
	@Column(length = 10, nullable = false)
	private String state;
	@Column(length = 5, nullable = false)
	private String zip;
	@Column(length = 10, nullable = false)
	private String phoneNumber;
	@Column(length = 75, nullable = false)
	private String email;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Vendor() {
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", code=" + code + ", name=" + name + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", phoneNumber=" + phoneNumber + ", email=" + email + ", isReviewer="
				+ ", isAdmin=" + ", active=" + ", preapproved=" + "]";
	}

}
