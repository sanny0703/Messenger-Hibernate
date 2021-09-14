package com.sanjay.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Message {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)	
	private Integer id;
	private String message;
	private String name;
	@Column(name="u_id")
	private Integer uId;
	@Column(name="date_created")
	private  LocalDateTime dateCreated ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime  dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Message(Integer id, String message, String name, Integer uId, LocalDateTime dateCreated) {
		super();
		this.id = id;
		this.message = message;
		this.name = name;
		this.uId = uId;
		this.dateCreated = dateCreated;
	}
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
}
