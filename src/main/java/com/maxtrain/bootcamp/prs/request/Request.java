package com.maxtrain.bootcamp.prs.request;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.maxtrain.bootcamp.prs.user.User;

@Entity
public class Request {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional=false)
	@JoinColumn(name="userId")
	private User user;
	@Column(length=100, nullable=false)
	private String description;
	@Column(length=200, nullable=false)
	private String justification;
	@Column(nullable=false)
	private Date dateNeeded;
	@Column(length=30, nullable=false)
	private String deliveryMode;
	@Column(columnDefinition = "varchar(20) not null default 'NEW'")
	private String status;
	@Column(columnDefinition="decimal(10,2) NOT NULL DEFAULT 0.0")
	private double total;
	@Column(columnDefinition= "datetime default current_timestamp not null")
	private LocalDateTime submittedDate;
	@Column(length=20, nullable=false)
	private String reasonForRejection;
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getJustification() {
		return justification;
	}


	public void setJustification(String justification) {
		this.justification = justification;
	}


	public Date getDateNeeded() {
		return dateNeeded;
	}


	public void setDateNeeded(Date dateNeeded) {
		this.dateNeeded = dateNeeded;
	}


	public String getDeliveryMode() {
		return deliveryMode;
	}


	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getTotal() {
		return total;
	}
	

	public void setTotal(double total) {
		this.total = total;
	}


	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}
	
	public void setSubmittedDate(LocalDateTime submittedDate ) {
		this.submittedDate = submittedDate;
	}


	public String getReasonForRejection() {
		return reasonForRejection;
	}


	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}


	public Request() {
		
	}


	@Override
	public String toString() {
		return "Request [id=" + id + ", user=" + user + ", description=" + description + ", justification="
				+ justification + ", dateNeeded=" + dateNeeded + ", deliveryMode=" + deliveryMode + ", status=" + status
				+ ", total=" + total + ", submittedDate=" + submittedDate + ", reasonForRejection=" + reasonForRejection
				+ "]";
	}



	
	public Request(int id, User user, String description, String justification, Date dateNeeded, String deliveryMode,
						String status, double total, LocalDateTime submittedDate, String reasonForRejection) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
	
	}


	public void setSubmittedDate(java.sql.Date date) {
		
	}

	
}
